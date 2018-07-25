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
