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
package com.nuvei.screenplay.ui.common;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class NumericScreen {
    private NumericScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_XPATH = "//android.widget.TextView[@text=\"{0}\"][1]";

    public static final Target TITLE = Target.the("Title {0}").locatedBy(BASE_XPATH);

    public static final Target LABEL_REASON = Target.the("Reason {0}").locatedBy(BASE_XPATH);

    public static final Target TEXTBOX_VALUE =
            Target.the("Reason {0}").located(AppiumBy.className("android.widget.EditText"));

    public static final Target BUTTON_NUMPAD_NUMBER =
            Target.the("Button number {0}").locatedBy(BASE_XPATH);

    public static final Target BUTTON_CANCEL =
            Target.the("Button Cancel {0}").locatedBy("//android.widget.Button[@text='CANCEL']");

    public static final Target BUTTON_CONTINUE_OR_CONFIRM =
            Target.the("Button Continue {0}")
                    .locatedBy("//android.widget.Button[@text='CONTINUE' or @text='CONFIRM']");
}
