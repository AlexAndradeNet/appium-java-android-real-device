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

public class TippingOptionsScreen {
    private TippingOptionsScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String TOGGLE_BASE_SELECTOR =
            "//android.widget.TextView[@text=\"%s\"]/following-sibling::*[1]";

    private static final String TEXTBOX_BASE_SELECTOR = "(//android.widget.EditText)[%s]";

    public static final Target TITLE =
            Target.the("Title")
                    .located(AppiumBy.androidUIAutomator("new UiSelector().text(\"TIPPING\")"));

    public static final Target TOGGLE_ACCEPT_TIPS =
            Target.the("Accept Tips Toggle")
                    .located(AppiumBy.xpath(TOGGLE_BASE_SELECTOR.formatted("Accept Tips?")));

    public static final Target TOGGLE_PERCENTAGE_TIPS_OPTION =
            Target.the("Percentage Tips Options Toggle")
                    .located(
                            AppiumBy.xpath(
                                    TOGGLE_BASE_SELECTOR.formatted("Percentage (%) Tip Options")));

    public static final Target TEXTBOX_TIP_PRESET_1 =
            Target.the("Preset Tip 1 Textbox")
                    .located(AppiumBy.xpath(TEXTBOX_BASE_SELECTOR.formatted("1")));

    public static final Target TEXTBOX_TIP_PRESET_2 =
            Target.the("Preset Tip 2 Textbox")
                    .located(AppiumBy.xpath(TEXTBOX_BASE_SELECTOR.formatted("2")));

    public static final Target TEXTBOX_TIP_PRESET_3 =
            Target.the("Preset Tip 3 Textbox")
                    .located(AppiumBy.xpath(TEXTBOX_BASE_SELECTOR.formatted("3")));

    public static final Target TOGGLE_TIP_ADJUST =
            Target.the("Tip Adjust Toggle")
                    .located(AppiumBy.xpath(TOGGLE_BASE_SELECTOR.formatted("Tip Adjust")));

    public static final Target TEXTBOX_EXCESS_TIP_ADJUST =
            Target.the("Excess Tip Adjust Textbox")
                    .located(AppiumBy.xpath(TEXTBOX_BASE_SELECTOR.formatted("4")));
}
