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
import com.msd.gin.common.sonar.language.RQualityProfile;
import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;

import static java.util.Arrays.asList;

public class SonarRPlugin implements Plugin {

    public static final String PROPERTY_FILE_SUFFIXES = "sonar.r.file.suffixes";

    public static final String PROPERTY_LINTR_OUTPUT_FILE = "sonar.r.lintr.output";

    @Override
    public void define(Context context) {
        context.addExtension(RLanguage.class)
                .addExtension(LintRSensor.class)
                .addExtension(RQualityProfile.class)
                .addExtension(LintRRulesDefinition.class);

        context.addExtensions(asList(
                PropertyDefinition.builder(PROPERTY_FILE_SUFFIXES)
                        .name("Suffixes R")
                        .description("Comma-separated list of suffixes for R language")
                        .category("R")
                        .defaultValue(".R")
                        .multiValues(true)
                        .build(),
                PropertyDefinition.builder(PROPERTY_LINTR_OUTPUT_FILE)
                        .name("LintR Output Filename")
                        .description("Path and filename to LintR output in JSON format")
                        .category("R")
                        .defaultValue("lintr_out.json")
                        .build()
        ));

    }

}
