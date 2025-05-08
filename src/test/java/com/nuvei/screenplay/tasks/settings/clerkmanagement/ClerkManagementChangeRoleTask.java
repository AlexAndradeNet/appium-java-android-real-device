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
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.settings.clerkmanagement.ChangeClerkRoleScreen;
import com.nuvei.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ClerkManagementChangeRoleTask implements Task {

    private final String newRole;

    public ClerkManagementChangeRoleTask(String newRole) {
        this.newRole = newRole;
    }

    @Override
    @Step("{0} changes the role to #newRole")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE),
                WaitAction.forSpecificTime(2),
                Ensure.that(
                                "Role title visible",
                                VisibilityQuestion.isPresent(ChangeClerkRoleScreen.TITLE))
                        .isTrue(),
                ClickAction.on(ClerkManagementUtils.getTargetForRole(newRole)));
    }

    public static ClerkManagementChangeRoleTask to(String newRole) {
        return new ClerkManagementChangeRoleTask(newRole);
    }
}
