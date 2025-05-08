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
import com.nuvei.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementDeleteUserTask implements Task {

    private final boolean validateTexts;
    private final boolean confirm;

    public ClerkManagementDeleteUserTask(boolean validateTexts, boolean confirm) {
        this.validateTexts = validateTexts;
        this.confirm = confirm;
    }

    @Override
    @Step("{0} deletes the clerk (confirm: #confirm, validate: #validateTexts)")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickAction.on(ViewClerkScreen.BUTTON_DELETE_USER));

        if (validateTexts) {
            actor.attemptsTo(
                    ConfirmationScreenValidateTask.withYesAndCancelButtons(
                            "DELETE CLERK", "Are you sure you want to delete this clerk?", ""));
        }

        actor.attemptsTo(
                ClickAction.on(
                        confirm
                                ? ConfirmationScreen.BUTTON_YES
                                : ConfirmationScreen.BUTTON_CANCEL));
    }

    public static ClerkManagementDeleteUserTask with(boolean validateTexts, boolean confirm) {
        return new ClerkManagementDeleteUserTask(validateTexts, confirm);
    }
}
