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

public class TippingOptionsScreen {
    private TippingOptionsScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String TEXTBOX_BASE_SELECTOR = "(//android.widget.EditText)[%s]";

    public static final Target TITLE =
            Target.the("Title")
                    .located(AppiumBy.androidUIAutomator("new UiSelector().text(\"TIPPING\")"));

    public static final Target TOGGLE_ACCEPT_TIPS = CommonObjects.TOGGLE.of("Accept Tips?");

    public static final Target TOGGLE_PERCENTAGE_TIPS_OPTION =
            CommonObjects.TOGGLE.of("Percentage (%) Tip Options");

    public static final Target TEXTBOX_TIP_PRESET_1 =
            Target.the("Preset Tip 1 Textbox")
                    .located(By.xpath(TEXTBOX_BASE_SELECTOR.formatted("1")));

    public static final Target TEXTBOX_TIP_PRESET_2 =
            Target.the("Preset Tip 2 Textbox")
                    .located(By.xpath(TEXTBOX_BASE_SELECTOR.formatted("2")));

    public static final Target TEXTBOX_TIP_PRESET_3 =
            Target.the("Preset Tip 3 Textbox")
                    .located(By.xpath(TEXTBOX_BASE_SELECTOR.formatted("3")));

    public static final Target TOGGLE_TIP_ADJUST = CommonObjects.TOGGLE.of("Tip Adjust");

    public static final Target TEXTBOX_EXCESS_TIP_ADJUST =
            Target.the("Excess Tip Adjust Textbox")
                    .located(By.xpath(TEXTBOX_BASE_SELECTOR.formatted("4")));
}
