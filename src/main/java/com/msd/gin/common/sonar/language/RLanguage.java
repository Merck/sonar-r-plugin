package com.msd.gin.common.sonar.language;

import com.msd.gin.common.sonar.SonarRPlugin;
import org.apache.commons.lang3.StringUtils;
import org.sonar.api.config.Configuration;
import org.sonar.api.resources.AbstractLanguage;

import java.util.ArrayList;
import java.util.List;

public final class RLanguage extends AbstractLanguage {

    public static final String NAME = "R";
    public static final String KEY = "r";

    private final Configuration config;

    public RLanguage(Configuration config) {
        super(KEY, NAME);
        this.config = config;
    }

    @Override
    public String[] getFileSuffixes() {
        String[] propertyValue = config.getStringArray(SonarRPlugin.PROPERTY_FILE_SUFFIXES);
        return filterEmptyStrings(propertyValue);
    }

    private String[] filterEmptyStrings(String[] stringArray) {
        List<String> nonEmptyStrings = new ArrayList<>();
        for (String string : stringArray) {
            if (StringUtils.isNotBlank(string.trim())) {
                nonEmptyStrings.add(string.trim());
            }
        }
        return nonEmptyStrings.toArray(new String[0]);
    }
}
