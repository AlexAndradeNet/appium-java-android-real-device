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
package com.nuvei.screenplay.ui.settings;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class SemiIntegrationOptionsScreen {
    private SemiIntegrationOptionsScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR =
            "//android.widget.TextView[@text=\"%s\"]/following-sibling::*[1]";

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().text(\"SEMI INTEGRATION\")"));

    public static final Target TOGGLE_ENABLE_SEMI_INTEGRATION =
            Target.the("Enable Semi Integration Toggle")
                    .located(AppiumBy.xpath(BASE_SELECTOR.formatted("Enable Semi Integration")));

    public static final Target TOGGLE_ENABLE_STANDALONE_PASSWORD =
            Target.the("Enable Standalone Password Toggle")
                    .located(AppiumBy.xpath(BASE_SELECTOR.formatted("Enable Standalone Password")));

    public static final Target RADIO_RETAIL =
            Target.the("Retail radio button")
                    .located(AppiumBy.xpath(BASE_SELECTOR.formatted("Retail")));
}
