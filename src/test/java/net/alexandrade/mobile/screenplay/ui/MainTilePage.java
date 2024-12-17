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

public class MainTilePage {
    private MainTilePage() {
        // Utility class
    }

    public static final Target SPINNER =
            Target.the("Spinner").located(AppiumBy.xpath("//android.widget.ProgressBar"));

    public static final Target BUTTON_SALE_TRANSACTION =
            Target.the("Sale").located(AppiumBy.xpath("//android.widget.TextView[@text=\"Sale\"]"));
}
