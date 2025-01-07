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
package net.alexandrade.mobile.screenplay.ui.settle;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class MainSettleScreen {
    private MainSettleScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("SETTLE")));

    public static final Target LABEL_SUMMARY =
            Target.the("Summary label")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Summary")));
}
