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
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementRemoveAllUsersTask implements Task {

    private final String exemptedClerkId;

    public ClerkManagementRemoveAllUsersTask(String exemptedClerkId) {
        this.exemptedClerkId = exemptedClerkId;
    }

    @Override
    @Step("{0} removes all clerks except #exemptedClerkId")
    public <T extends Actor> void performAs(T actor) {
        while (actor.asksFor(
                VisibilityQuestion.isPresent(
                        MainClerkManagementScreen.BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN.of(
                                exemptedClerkId)))) {

            actor.attemptsTo(
                    ClickAction.on(
                            MainClerkManagementScreen.BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN
                                    .of(exemptedClerkId)),
                    ClerkManagementDeleteUserTask.with(false, true),
                    ClerkManagementDismissDeleteConfirmationTask.afterDelete(false));
        }
    }

    public static ClerkManagementRemoveAllUsersTask except(String clerkId) {
        return new ClerkManagementRemoveAllUsersTask(clerkId);
    }
}
