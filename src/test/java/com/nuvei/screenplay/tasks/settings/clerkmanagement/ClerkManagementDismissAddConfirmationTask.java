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
package com.nuvei.screenplay.tasks.settings.clerkmanagement;

import com.nuvei.screenplay.tasks.commons.ConfirmationScreenValidateAndDismissTask;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementDismissAddConfirmationTask implements Task {

    @Override
    @Step("{0} dismisses the confirmation message after adding a user")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ConfirmationScreenValidateAndDismissTask.withDone(
                        "ADD NEW CLERK", "SUCCESS", "Clerk Created"));
    }

    public static ClerkManagementDismissAddConfirmationTask afterAdd() {
        return new ClerkManagementDismissAddConfirmationTask();
    }
}
