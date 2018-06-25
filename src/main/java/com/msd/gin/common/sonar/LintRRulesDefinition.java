package com.msd.gin.common.sonar;

import com.msd.gin.common.sonar.language.RLanguage;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LintRRulesDefinition implements RulesDefinition {

    public static final String REPO_KEY = RLanguage.KEY + "-" + "lintr";
    public static final String REPO_NAME = "LintR";

    private static final String PATH = "lintr-rules.xml";
    private final RulesDefinitionXmlLoader xmlLoader;

    public static List<String> RULE_KEYS = Collections.emptyList();

    public LintRRulesDefinition(RulesDefinitionXmlLoader xmlLoader) {
        this.xmlLoader = xmlLoader;
    }

    @Override
    public void define(Context context) {
        try (Reader reader = new InputStreamReader(getClass().getResourceAsStream(PATH), StandardCharsets.UTF_8)) {
            NewRepository repository = context.createRepository(REPO_KEY, RLanguage.KEY).setName(REPO_NAME);
            xmlLoader.load(repository, reader);
            List<String> allKeys = repository.rules().stream().map(NewRule::key).collect(Collectors.toList());
            RULE_KEYS = allKeys;
            repository.done();
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Fail to read file %s", PATH), e);
        }

    }

}
