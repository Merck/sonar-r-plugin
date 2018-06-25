package com.msd.gin.common.sonar.lintr;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.msd.gin.common.sonar.model.LintRIssue;

import java.util.Arrays;
import java.util.List;

public class JsonOutputParser {

    public List<LintRIssue> parse(String jsonOutput) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        LintRIssue[] issues = gson.fromJson(jsonOutput, LintRIssue[].class);
        List<LintRIssue> result = Arrays.asList(issues);
        return result;
    }

}
