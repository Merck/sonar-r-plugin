package com.msd.gin.common.sonar.settings;

import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import java.util.List;

import static java.util.Arrays.asList;

public class RLanguageProperties {

    public static final String FILE_SUFFIXES_KEY = "sonar.r.file.suffixes";
    public static final String FILE_SUFFIXES_DEFAULT_VALUE = ".R";

    private RLanguageProperties() {
        // only statics
    }

    public static List<PropertyDefinition> getProperties() {
        return asList(PropertyDefinition.builder(FILE_SUFFIXES_KEY)
                .defaultValue(FILE_SUFFIXES_DEFAULT_VALUE)
                .category("R")
                .name("File Suffixes")
                .description("Comma-separated list of suffixes for files to analyze.")
                .onQualifiers(Qualifiers.PROJECT)
                .build());
    }

}
