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
package com.nuvei.screenplay.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class DashboardScreen {
    private DashboardScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target SPINNER =
            Target.the("Spinner").located(AppiumBy.xpath("//android.widget.ProgressBar"));

    public static final Target BUTTON_SALE_TRANSACTION =
            Target.the("Sale")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Sale")));

    public static final Target BUTTON_REFUND_TRANSACTION =
            Target.the("Refund")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Refund")));

    public static final Target BUTTON_MOTO_TRANSACTION =
            Target.the("Moto")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Moto")));

    public static final Target BUTTON_VOID_TRANSACTION =
            Target.the("Void")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Void")));

    public static final Target BUTTON_CRYPTO_TRANSACTION =
            Target.the("Crypto")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Crypto")));

    public static final Target BUTTON_BATCH_OR_SETTLE =
            Target.the("Batch or Settle")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Settle")));

    public static final Target BUTTON_SETTINGS =
            Target.the("Settings")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Settings")));

    public static final Target BUTTON_HELP_DESK =
            Target.the("Help Desk")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Help")));

    public static final Target BUTTON_TID =
            Target.the("TID info")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("TID")));

    public static final Target BUTTON_PREVIOUS =
            Target.the("Button Previous")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Previous")));

    public static final Target BUTTON_NEXT =
            Target.the("Button Previous")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Next")));

    public static final Target LABEL_SAF =
            Target.the("Button SAF Enabled")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("SAF Enabled")));

    public static final Target BUTTON_SAF =
            Target.the("Button SAF")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("SAF")));
}
