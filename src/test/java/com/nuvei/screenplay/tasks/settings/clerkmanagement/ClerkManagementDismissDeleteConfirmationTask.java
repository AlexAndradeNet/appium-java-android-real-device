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

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.tasks.commons.ConfirmationScreenValidateTask;
import com.nuvei.screenplay.ui.common.ConfirmationScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementDismissDeleteConfirmationTask implements Task {

    private final boolean validate;

    public ClerkManagementDismissDeleteConfirmationTask(boolean validate) {
        this.validate = validate;
    }

    @Override
    @Step("{0} dismisses the confirmation message after deleting a clerk")
    public <T extends Actor> void performAs(T actor) {
        if (validate) {
            actor.attemptsTo(
                    ConfirmationScreenValidateTask.withOutButtons(
                            "DELETE CLERK", "SUCCESS", "Clerk Deleted"));
        }

        actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_DONE));
    }

    public static ClerkManagementDismissDeleteConfirmationTask afterDelete(boolean validate) {
        return new ClerkManagementDismissDeleteConfirmationTask(validate);
    }
}
