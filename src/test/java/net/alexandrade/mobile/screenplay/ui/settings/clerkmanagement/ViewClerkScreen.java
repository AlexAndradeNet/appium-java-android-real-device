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

public class ViewClerkScreen {
    private ViewClerkScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("VIEW CLERK")));

    public static final Target LABEL_CLERK_ID =
            Target.the("Label Clerk ID")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("#{0}")));

    public static final Target LABEL_CLERK_ROLE =
            Target.the("Label Clerk Role")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("#{0}")));

    public static final Target BUTTON_CHANGE_CLERK_ID =
            Target.the("Button change Clerk ID")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("Change Clerk ID")));

    public static final Target BUTTON_CHANGE_PASSWORD =
            Target.the("Button change Password")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("Change Password")));

    public static final Target BUTTON_CHANGE_CLERK_ROLE =
            Target.the("Button change Clerk Role")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("Change Clerk Role")));

    public static final Target BUTTON_DELETE_USER =
            Target.the("Button Delete User")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("DELETE USER")));
}
