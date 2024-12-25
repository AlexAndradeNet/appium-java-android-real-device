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
package net.alexandrade.mobile.screenplay.ui;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class MainTileScreen {
    private MainTileScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target SPINNER =
            Target.the("Spinner").located(AppiumBy.xpath("//android.widget.ProgressBar"));

    public static final Target BUTTON_SALE_TRANSACTION =
            Target.the("Sale")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Sale")));

    public static final Target BUTTON_SETTINGS_TRANSACTION =
            Target.the("Settings")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Settings")));
}
