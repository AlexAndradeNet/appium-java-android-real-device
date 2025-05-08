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
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.ui.settings.clerkmanagement.AddUserScreen;
import com.nuvei.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ClerkManagementAddUserTask implements Task {

    private final boolean validateTexts;
    private final String clerkId;
    private final String role;
    private final String password;

    public ClerkManagementAddUserTask(
            boolean validateTexts, String clerkId, String role, String password) {
        this.validateTexts = validateTexts;
        this.clerkId = clerkId;
        this.role = role;
        this.password = password;
    }

    @Override
    @Step("{0} adds a new clerk with ID #clerkId and role #role")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickAction.on(MainClerkManagementScreen.BUTTON_ADD_NEW_USER));

        if (validateTexts) {
            actor.attemptsTo(
                    Ensure.that("Screen title", TextQuestion.of(AddUserScreen.TITLE))
                            .isEqualToIgnoringCase("ADD NEW CLERK"));
        }

        actor.attemptsTo(
                ClickAction.on(ClerkManagementUtils.getTargetForRole(role)),
                EnterAction.into(AddUserScreen.TEXTBOX_USER_ID, clerkId),
                EnterAction.into(AddUserScreen.TEXTBOX_PASSWORD, password),
                EnterAction.into(AddUserScreen.TEXTBOX_CONFIRM, password));

        if (validateTexts) {
            actor.attemptsTo(
                    Ensure.that("Role title", TextQuestion.of(AddUserScreen.TITLE))
                            .isEqualToIgnoringCase("NEW " + role.toUpperCase()));
        }

        actor.attemptsTo(
                ClickAction.on(AddUserScreen.BUTTON_CONFIRM), WaitAction.forSpecificTime(1));
    }

    public static ClerkManagementAddUserTask with(
            String clerkId, String role, String password, boolean validateTexts) {
        return new ClerkManagementAddUserTask(validateTexts, clerkId, role, password);
    }
}
