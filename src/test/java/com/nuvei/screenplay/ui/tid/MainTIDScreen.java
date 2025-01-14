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
package com.nuvei.screenplay.ui.tid;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class MainTIDScreen {
    private MainTIDScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().text(\"%s\")".formatted("TID")));

    private static final String BASE_XPATH =
            "//android.widget.TextView[contains(@resource-id, \"%s\")]";

    public static final Target LABEL_TID =
            Target.the("TID").located(AppiumBy.xpath(BASE_XPATH.formatted("tv_tid")));

    public static final Target LABEL_SEMI_INTEGRATION_URL =
            Target.the("Semi-Integration URL label")
                    .located(AppiumBy.xpath(BASE_XPATH.formatted("title_value")));

    public static final Target LABEL_SEMI_INTEGRATION_PORT =
            Target.the("Semi-Integration PORT label")
                    .located(AppiumBy.xpath(BASE_XPATH.formatted("port_value")));
}
