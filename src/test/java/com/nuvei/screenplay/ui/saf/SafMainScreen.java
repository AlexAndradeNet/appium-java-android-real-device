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
package com.nuvei.screenplay.ui.saf;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class SafMainScreen {
    private SafMainScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("SAF")));

    public static final Target BUTTON_PENDING_TRANSACTIONS =
            Target.the("Pending Transactions button")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("Pending Transactions")));

    public static final Target BUTTON_DECLINED_TRANSACTIONS =
            Target.the("Declined Transactions button")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("Declined Transactions")));
}
