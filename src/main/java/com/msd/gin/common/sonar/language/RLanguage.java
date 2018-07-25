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
