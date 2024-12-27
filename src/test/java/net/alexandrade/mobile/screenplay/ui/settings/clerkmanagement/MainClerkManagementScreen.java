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

public class MainClerkManagementScreen {
    private MainClerkManagementScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";
    private static final String BASE_XPATH = "//android.widget.TextView[@text=\"#{0}\"][1]";

    public static final Target TITLE =
            Target.the("Title")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("CLERK MANAGEMENT")));

    public static final Target TEXTBOX_SEARCH =
            Target.the("Search textbox").located(AppiumBy.className("android.widget.TextView"));

    public static final Target BUTTON_USER_PROFILE_ID =
            Target.the("Button profile #{0}").locatedBy(BASE_XPATH);

    public static final Target LIST_OF_USER_PROFILE_IDS =
            Target.the("List of Profiles IDs")
                    .located(
                            AppiumBy.xpath(
                                    "(//android.widget.TextView[contains(@resource-id,"
                                            + " \"txtClerkId\")])"));

    public static final Target LABEL_USER_PROFILE_ROLE =
            Target.the("Label User Profile Role")
                    .locatedBy(BASE_XPATH + "/following-sibling::*[1]");

    public static final Target BUTTON_ADD_NEW_USER =
            Target.the("Button Add New User")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("ADD NEW USER")));
}
