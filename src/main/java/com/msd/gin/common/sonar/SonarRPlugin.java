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
