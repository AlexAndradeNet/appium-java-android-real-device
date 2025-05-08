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
import com.nuvei.screenplay.interactions.EnterAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.settings.clerkmanagement.ChangeClerkIDScreen;
import com.nuvei.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ClerkManagementChangeIdTask implements Task {

    private final String newClerkId;

    public ClerkManagementChangeIdTask(String newClerkId) {
        this.newClerkId = newClerkId;
    }

    @Override
    @Step("{0} changes the clerk ID to #newClerkId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ID),
                EnterAction.into(ChangeClerkIDScreen.TEXTBOX_NEW_CLERK_ID, newClerkId),
                Ensure.that(
                                "Clerk ID title",
                                VisibilityQuestion.isPresent(ChangeClerkIDScreen.TITLE))
                        .isTrue(),
                ClickAction.on(ChangeClerkIDScreen.BUTTON_CONFIRM));
    }

    public static ClerkManagementChangeIdTask to(String newClerkId) {
        return new ClerkManagementChangeIdTask(newClerkId);
    }
}
