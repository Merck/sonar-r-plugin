package com.msd.gin.common.sonar.language;

import com.msd.gin.common.sonar.LintRRulesDefinition;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;

import java.util.List;

public class RQualityProfile implements BuiltInQualityProfilesDefinition {

    @Override
    public void define(Context context) {
        NewBuiltInQualityProfile profile = context.createBuiltInQualityProfile("Sonar way", RLanguage.KEY);
        List<String> allKeys = LintRRulesDefinition.RULE_KEYS;
        allKeys.forEach((String key) -> profile.activateRule(LintRRulesDefinition.REPO_KEY, key));
        profile.done();
    }
}
