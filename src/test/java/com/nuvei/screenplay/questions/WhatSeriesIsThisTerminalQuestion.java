/*
nuvei.com CONFIDENTIAL

Copyright (c) 2024. All Rights Reserved.

NOTICE: The source code contained or described herein and all documents
related to the source code ("Material") are owned by Nuvei Inc.
or its companies, suppliers or licensors.

Dissemination of this information or reproduction of this material
is strictly forbidden unless prior written permission is obtained
from Nuvei Inc.
*/
package com.nuvei.screenplay.questions;

import static com.nuvei.screenplay.questions.EnvironmentQuestion.getVariable;

import net.serenitybdd.screenplay.Question;

public class WhatSeriesIsThisTerminalQuestion {
    private static final String ENVIRONMENT_VARIABLE = "environment";

    private WhatSeriesIsThisTerminalQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Question<Boolean> isTSeries() {
        // T series has 2 letters like "us", "de", "fr", etc.
        return actor -> actor.asksFor(getVariable(ENVIRONMENT_VARIABLE)).length() == 2;
    }

    public static Question<Boolean> isMSeries() {
        return actor -> {
            String environmentVariable = actor.asksFor(getVariable(ENVIRONMENT_VARIABLE));
            return environmentVariable.startsWith("m") && environmentVariable.length() == 3;
        };
    }

    public static Question<Boolean> isPSeries() {
        return actor -> {
            String environmentVariable = actor.asksFor(getVariable(ENVIRONMENT_VARIABLE));
            return environmentVariable.startsWith("p") && environmentVariable.length() == 3;
        };
    }
}
