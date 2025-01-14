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
package com.nuvei.screenplay.ui.help;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class MainHelpScreen {
    private MainHelpScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("HELP")));

    public static final Target OPTION_CLEAR_REVERSAL =
            Target.the("Clear Reversal Option")
                    .located(
                            AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Clear Reversal")));

    public static final Target OPTION_CLEAR_BATCH =
            Target.the("Clear Batch Option")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Clear Batch")));

    public static final Target OPTION_CLEAR_PRE_AUTH =
            Target.the("Clear Pre-Auth Option")
                    .located(
                            AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Clear Pre-Auth")));

    public static final Target OPTION_CLEAR_SAF =
            Target.the("Clear SAF Option")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Clear SAF")));
}
