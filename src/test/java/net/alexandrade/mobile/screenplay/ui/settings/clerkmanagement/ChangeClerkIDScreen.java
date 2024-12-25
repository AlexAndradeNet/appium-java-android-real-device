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
package net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class ChangeClerkIDScreen {
    private ChangeClerkIDScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("CHANGE CLERK ID")));

    public static final Target TEXTBOX_NEW_CLERK_ID =
            Target.the("New Clerk ID textbox")
                    .located(AppiumBy.className("android.widget.TextView"));

    public static final Target BUTTON_CONFIRM =
            Target.the("Button Confirm")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("CONFIRM")));
}
