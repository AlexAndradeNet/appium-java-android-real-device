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
package net.alexandrade.mobile.screenplay.tasks.settings;

import net.alexandrade.mobile.screenplay.interactions.Tap;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementTasks {

    private ClerkManagementTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    @Step("{0} opens the Transaction Flow settings {1}")
    public static Performable openAccountDetailsForProfile(String clerkId) {
        return Task.where(
                "{0} opens the Transaction Options > Transaction Flow screen",
                Tap.on(MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(clerkId)));
    }
}
