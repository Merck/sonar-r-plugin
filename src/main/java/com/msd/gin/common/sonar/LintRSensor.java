/*
 * Copyright 2018 Merck Sharp & Dohme Corp. a subsidiary of Merck & Co.,
 * Inc., Kenilworth, NJ, USA.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.msd.gin.common.sonar;

import com.msd.gin.common.sonar.language.RLanguage;
import com.msd.gin.common.sonar.lintr.JsonOutputParser;
import com.msd.gin.common.sonar.model.LintRIssue;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.TextRange;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;
import org.sonar.api.internal.apachecommons.lang.StringUtils;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LintRSensor implements Sensor {

    private static final Logger LOGGER = Loggers.get(LintRSensor.class);

    private final FileSystem fileSystem;

    public LintRSensor(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("LintR Sensor for R files").onlyOnLanguage(RLanguage.KEY);
    }

    @Override
    public void execute(SensorContext context) {
        LOGGER.info("Running sensor for LintR");
        Optional<String> lintrOutputFileProperty = context.config().get(SonarRPlugin.PROPERTY_LINTR_OUTPUT_FILE);
        if (!lintrOutputFileProperty.isPresent()) {
            LOGGER.warn("LintR output file property is not configured properly, skipping");
        } else {
            List<LintRIssue> issues = readLintrOutputFile(lintrOutputFileProperty.get());
            issues.forEach(x -> processIssue(context, x));

            // add LinesOfCode value to get "overview tab" working in SonarQube
            fileSystem.inputFiles(
                fileSystem.predicates().and(
                    fileSystem.predicates().hasType(InputFile.Type.MAIN),
                    fileSystem.predicates().hasLanguage(RLanguage.KEY)))
                .forEach(inputFile -> countLinesForFile(context, inputFile));
        }
    }

    public List<LintRIssue> readLintrOutputFile(String lintrOutputFile) {
        byte[] content;
        try {
            content = Files.readAllBytes(Paths.get(lintrOutputFile));
        } catch (IOException e) {
            LOGGER.warn("Cannot read " + lintrOutputFile + " file, skipping", e);
            return Collections.emptyList();
        }
        String jsonContent = new String(content, StandardCharsets.UTF_8);
        if (StringUtils.isBlank(jsonContent)) {
            LOGGER.warn("LintR output file " + lintrOutputFile + " is empty, no issues will be reported.");
            return Collections.emptyList();
        }
        JsonOutputParser parser = new JsonOutputParser();
        List<LintRIssue> issues = parser.parse(jsonContent);
        LOGGER.info("Issues found by LintR: " + issues.size());
        return issues;
    }


    public void countLinesForFile(SensorContext context, InputFile inputFile) {
        int linesCount = inputFile.lines();
        LOGGER.info("Adding Lines Of Code " + linesCount + " for " + inputFile.filename());
        context.<Integer>newMeasure().withValue(linesCount).forMetric(CoreMetrics.NCLOC).on(inputFile).save();
    }

    protected void processIssue(final SensorContext context, final LintRIssue issue) {
        LOGGER.debug("Processing LintR issue: " + issue);
        InputFile inputFile = fileSystem.inputFile(
                fileSystem.predicates().and(
                        fileSystem.predicates().hasRelativePath(issue.getFilename()),
                        fileSystem.predicates().hasType(InputFile.Type.MAIN)));

        if (inputFile != null) {
            try {
                saveIssue(context, inputFile, issue.getLineNumber(), issue.getColumnNumber(), issue.getLinter(), issue.getMessage());
            } catch (Exception e) {
                LOGGER.error("Cannot save issue " + issue, e);
            }
        } else {
            LOGGER.error("Not able to find a InputFile with " + issue.getFilename());
        }
    }

    private void saveIssue(final SensorContext context, final InputFile inputFile, int line, int column, final String externalRuleKey, final String message) {
        RuleKey ruleKey = RuleKey.of(LintRRulesDefinition.REPO_KEY, externalRuleKey);

        NewIssue newIssue = context.newIssue().forRule(ruleKey);

        NewIssueLocation primaryLocation = newIssue.newLocation()
                .on(inputFile)
                .message(message);
        if (line > 0) {
            TextRange lineRange = inputFile.selectLine(line);
            int startIndex = Math.max(column - 1, lineRange.start().lineOffset());
            int endIndex = Math.min(column, lineRange.end().lineOffset());
            TextRange tr;
            if (startIndex < endIndex) {
                tr = inputFile.newRange(line, startIndex, line, endIndex);
            } else {
                LOGGER.warn("Start and end index cannot be properly computed for column {} on line {}, putting issue on line", column, line);
                tr = inputFile.selectLine(line);
            }
            primaryLocation.at(tr);
        }
        newIssue.at(primaryLocation);

        newIssue.save();
    }
}
