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
package net.alexandrade.mobile.screenplay.ui.settings;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class SettingsPage {
    private SettingsPage() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_XPATH = "//android.widget.TextView[@text=\"%s\"]";

    public static final Target BUTTON_TRANSACTION_OPTIONS =
            Target.the("Transaction Options")
                    .located(AppiumBy.xpath(String.format(BASE_XPATH, "Transaction Options")));
}
