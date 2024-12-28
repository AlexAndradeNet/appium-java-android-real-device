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

public class ConfirmationScreen {
    private ConfirmationScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_XPATH = "(//android.widget.TextView)[%s]";

    public static final Target TITLE =
            Target.the("Title").located(AppiumBy.xpath(BASE_XPATH.formatted("1")));

    public static final Target LABEL_MESSAGE_TITLE =
            Target.the("Label Success").located(AppiumBy.xpath(BASE_XPATH.formatted("2")));

    public static final Target LABEL_MESSAGE_DETAIL =
            Target.the("Label Clerk Deleted").located(AppiumBy.xpath(BASE_XPATH.formatted("3")));

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target BUTTON_DONE =
            Target.the("Button Done")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("DONE")));

    public static final Target BUTTON_CANCEL =
            Target.the("Button Cancel")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("CANCEL")));

    public static final Target BUTTON_YES =
            Target.the("Button Yes")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("YES")));
}
