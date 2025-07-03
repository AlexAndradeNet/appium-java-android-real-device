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

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CommonObjects {
    private CommonObjects() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR =
            "//android.widget.TextView[contains(@resource-id, \"%s\")]";

    private static final String BUTTON_NAME_CONSTRUCTOR = "Button {0}";

    public static final Target BUTTON_ARROW_BACK =
            Target.the("Arrow Back")
                    .located(
                            By.xpath(
                                    "//android.widget.RelativeLayout[contains(@resource-id,\"rlBackButton\")]"));

    public static final Target POPUP_MESSAGE_TITLE =
            Target.the("Popup Message Title")
                    .located(By.xpath(BASE_SELECTOR.formatted("txtHeader")));

    public static final Target POPUP_MESSAGE_CONTENT =
            Target.the("Popup Message Content")
                    .located(By.xpath(BASE_SELECTOR.formatted("txtMessage")));

    public static final Target POPUP_MESSAGE_BUTTON_OK =
            Target.the(BUTTON_NAME_CONSTRUCTOR)
                    .located(By.xpath(BASE_SELECTOR.formatted("btnPossitive")));

    public static final Target POPUP_MESSAGE_BUTTON_CANCEL =
            Target.the(BUTTON_NAME_CONSTRUCTOR)
                    .located(By.xpath(BASE_SELECTOR.formatted("btnNegative")));

    public static final Target POPUP_MESSAGE_BUTTON_PRINT_MERCHANT = POPUP_MESSAGE_BUTTON_CANCEL;

    public static final Target BUTTON_GO_TO_STANDALONE =
            Target.the("Button Go To Stand Alone")
                    .located(By.xpath("//android.widget.Button[@text='GO TO STAND ALONE']"));

    public static final Target POPUP_MESSAGE_SECOND_BUTTON =
            Target.the(BUTTON_NAME_CONSTRUCTOR).located(By.xpath(BASE_SELECTOR.formatted(3)));

    public static final Target POPUP_MESSAGE_BUTTON_PRINT_CUSTOMER = POPUP_MESSAGE_BUTTON_OK;

    public static final Target ALL_TOGGLE_LABEL_LIST =
            Target.the("List of labels for toggle")
                    .located(
                            By.xpath(
                                    "(//android.widget.TextView[following-sibling::android.widget.Switch])"));

    public static final Target TOGGLE =
            Target.the("Toggle {0}")
                    .locatedBy(
                            "//android.widget.TextView[@text=\"{0}\"]/following-sibling::android.widget.Switch");
}
