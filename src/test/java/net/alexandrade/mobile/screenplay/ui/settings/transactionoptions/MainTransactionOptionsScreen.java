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
package net.alexandrade.mobile.screenplay.ui.settings.transactionoptions;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class MainTransactionOptionsScreen {
    private MainTransactionOptionsScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("TRANSACTION OPTIONS")));

    public static final Target BUTTON_TRANSACTION_FLOW =
            Target.the("Transaction Flow Button")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("Transaction Flow")));
}
