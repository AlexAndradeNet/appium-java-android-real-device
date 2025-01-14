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

import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class EnvironmentQuestion {
    private EnvironmentQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final EnvironmentVariables environmentVariables =
            SystemEnvironmentVariables.createEnvironmentVariables();

    public static boolean isTSeries() {
        // T series has 2 letters like "us", "de", "fr", etc.
        return environmentVariables.getProperty("environment").length() == 2;
    }

    public static boolean isMSeries() {
        return !isTSeries();
    }
}
