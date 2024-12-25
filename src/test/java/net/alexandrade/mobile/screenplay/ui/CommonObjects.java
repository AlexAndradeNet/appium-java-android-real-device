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

public class CommonObjects {
    private CommonObjects() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR =
            "//android.widget.TextView[@text=\"Alert Message\"]/following-sibling::*[%s]";

    public static final Target BUTTON_ARROW_BACK =
            Target.the("Arrow Back")
                    .located(
                            AppiumBy.xpath(
                                    "//android.widget.RelativeLayout[contains(@resource-id,\"rlBackButton\")]"));

    public static final Target POPUP_MESSAGE_TITLE =
            Target.the("Popup Message Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().text(\"Alert Message\")"));

    public static final Target POPUP_MESSAGE_CONTENT =
            Target.the("Popup Message Content").located(AppiumBy.xpath(BASE_SELECTOR.formatted(1)));

    public static final Target POPUP_MESSAGE_FIRST_OR_UNIQUE_BUTTON =
            Target.the("Button {0}").located(AppiumBy.xpath(BASE_SELECTOR.formatted(2)));

    public static final Target POPUP_MESSAGE_SECOND_BUTTON =
            Target.the("Button {0}").located(AppiumBy.xpath(BASE_SELECTOR.formatted(3)));
}
