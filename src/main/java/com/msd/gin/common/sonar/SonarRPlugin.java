package com.msd.gin.common.sonar;

import com.msd.gin.common.sonar.language.RLanguage;
import com.msd.gin.common.sonar.language.RQualityProfile;
import com.msd.gin.common.sonar.settings.RLanguageProperties;
import org.sonar.api.Plugin;

public class SonarRPlugin implements Plugin {

    @Override
    public void define(Context context) {
        context.addExtension(RLanguage.class)
                .addExtension(RLanguageProperties.getProperties())
                .addExtension(LintRSensor.class)
                .addExtension(RQualityProfile.class)
                .addExtension(LintRRulesDefinition.class);
    }

}
