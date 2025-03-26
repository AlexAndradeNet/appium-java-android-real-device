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

import net.serenitybdd.screenplay.Question;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class EnvironmentQuestion {
    private EnvironmentQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final EnvironmentVariables environmentVariables =
            SystemEnvironmentVariables.createEnvironmentVariables();

    public static Question<Boolean> isTSeries() {
        // T series has 2 letters like "us", "de", "fr", etc.
        return actor -> environmentVariables.getProperty("environment").length() == 2;
    }

    public static Question<Boolean> isMSeries() {
        String environmentVariable = environmentVariables.getProperty("environment");
        return actor -> environmentVariable.startsWith("m") && environmentVariable.length() == 3;
    }

    public static Question<Boolean> isPSeries() {
        String environmentVariable = environmentVariables.getProperty("environment");
        return actor -> environmentVariable.startsWith("p") && environmentVariable.length() == 3;
    }
}
