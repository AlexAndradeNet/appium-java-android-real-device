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
package com.nuvei.screenplay.ui.settings.clerkmanagement;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;

public class ChangeClerkRoleScreen {
    private ChangeClerkRoleScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("CHANGE CLERK ROLE")));

    public static final Target BUTTON_ROLE_ADMIN =
            Target.the("Button Admin Role")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Admin")));

    public static final Target BUTTON_ROLE_MANAGER =
            Target.the("Button Manager Role")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Manager")));

    public static final Target BUTTON_ROLE_EMPLOYEE =
            Target.the("Button Employee Role")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("Employee")));
}
