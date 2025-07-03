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
package com.nuvei.screenplay.ui.settings.transactionoptions;

import com.nuvei.screenplay.ui.CommonObjects;
import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SplitPaymentOptionsScreen {
    private SplitPaymentOptionsScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String TEXTBOX_BASE_SELECTOR = "(//android.widget.EditText)[%s]";

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().text(\"SPLIT PAYMENT\")"));

    public static final Target TOGGLE_ENABLE_SPLIT_PAYMENT =
            CommonObjects.TOGGLE.of("Enable Split Payment");

    public static final Target TOGGLE_ACCEPT_CASH = CommonObjects.TOGGLE.of("Accept Cash");

    public static final Target TEXTBOX_MINIMUM_SPLIT_PAYMENT_AMOUNT =
            Target.the("Minimum Split Payment Amount Textbox")
                    .located(By.xpath(TEXTBOX_BASE_SELECTOR.formatted("1")));
}
