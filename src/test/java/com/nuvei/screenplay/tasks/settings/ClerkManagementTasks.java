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
package com.nuvei.screenplay.tasks.settings;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.interactions.EnterAction;
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.ui.common.ConfirmationScreen;
import com.nuvei.screenplay.ui.settings.clerkmanagement.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

public class ClerkManagementTasks {

    private ClerkManagementTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable openAccountDetailsForProfile(Actor actor, String clerkId) {
        Target target = MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(clerkId);
        actor.attemptsTo(
                CommonTasks.navigateMenuUntilElementIsVisible(actor, target),
                ClickAction.on(target));

        return Task.where("{0} opens the Transaction Options > Transaction Flow screen");
    }

    public static Performable changePassword(
            Actor actor, String password, String confirmationPassword) {
        actor.attemptsTo(
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_PASSWORD),
                EnterAction.theValue(password).into(ChangePasswordScreen.TEXTBOX_PASSWORD),
                EnterAction.theValue(confirmationPassword)
                        .into(ChangePasswordScreen.TEXTBOX_CONFIRM),
                ClickAction.on(ChangePasswordScreen.BUTTON_CONFIRM));

        return Task.where("{0} changes the current user password");
    }

    public static Performable addANewUser(
            Actor actor, boolean withTextValidation, String clerkId, String role, String password) {
        actor.attemptsTo(ClickAction.on(MainClerkManagementScreen.BUTTON_ADD_NEW_USER));

        if (withTextValidation) {
            actor.attemptsTo(
                    Ensure.that("Should see the title", TextQuestion.of(AddUserScreen.TITLE))
                            .isEqualToIgnoringCase("ADD NEW CLERK"));
        }

        actor.attemptsTo(
                ClickAction.on(getTargetForRole(role)),
                EnterAction.theValue(clerkId).into(AddUserScreen.TEXTBOX_USER_ID),
                EnterAction.theValue(password).into(AddUserScreen.TEXTBOX_PASSWORD),
                EnterAction.theValue(password).into(AddUserScreen.TEXTBOX_CONFIRM));

        if (withTextValidation) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Verify that the role in the screen is \"%s\"".formatted(role),
                                    TextQuestion.of(AddUserScreen.TITLE))
                            .isEqualToIgnoringCase("NEW " + role.toUpperCase()));
        }

        actor.attemptsTo(
                ClickAction.on(AddUserScreen.BUTTON_CONFIRM), WaitAction.forSpecificTime(1));

        return Task.where("{0} adds a new user");
    }

    public static Performable dismissSuccessConfirmationAfterAddingANewUser(Actor actor) {
        actor.attemptsTo(
                CommonTasks.validateAndDismissConfirmationScreenWithDoneButton(
                        actor, "ADD NEW CLERK", "SUCCESS", "Clerk Created"));

        return Task.where("{0} dismisses the confirmation after adding a new user");
    }

    private static Target getTargetForRole(String role) {
        return switch (role) {
            case "Admin" -> ChangeClerkRoleScreen.BUTTON_ROLE_ADMIN;
            case "Manager" -> ChangeClerkRoleScreen.BUTTON_ROLE_MANAGER;
            case "Employee" -> ChangeClerkRoleScreen.BUTTON_ROLE_EMPLOYEE;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

    public static Performable searchForClerkId(Actor actor, String clerkId) {
        actor.attemptsTo(
                EnterAction.theValue(clerkId).into(MainClerkManagementScreen.TEXTBOX_SEARCH));

        return Task.where("{0} searches for the clerk ID");
    }

    public static Performable changeClerkId(Actor actor, String newClerkId) {
        actor.attemptsTo(
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ID),
                EnterAction.theValue(newClerkId).into(ChangeClerkIDScreen.TEXTBOX_NEW_CLERK_ID),
                Ensure.that(
                                "The title should be correct",
                                VisibilityQuestion.isPresent(ChangeClerkIDScreen.TITLE))
                        .isTrue(),
                ClickAction.on(ChangeClerkIDScreen.BUTTON_CONFIRM));

        return Task.where("{0} changes the clerk ID");
    }

    public static Performable changeRole(Actor actor, String newRole) {
        actor.attemptsTo(
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE),
                WaitAction.forSpecificTime(2),
                Ensure.that(
                                "The title should be correct",
                                VisibilityQuestion.isPresent(ChangeClerkRoleScreen.TITLE))
                        .isTrue(),
                ClickAction.on(getTargetForRole(newRole)));

        return Task.where("{0} changes the role");
    }

    public static Performable deleteClerk(
            Actor actor, boolean withValidationOfTexts, boolean confirmDeletion) {
        actor.attemptsTo(ClickAction.on(ViewClerkScreen.BUTTON_DELETE_USER));

        if (withValidationOfTexts) {
            actor.attemptsTo(
                    CommonTasks.validateConfirmationScreen(
                            actor,
                            "DELETE CLERK",
                            "Are you sure you want to delete this clerk?",
                            "",
                            true));
        }

        if (confirmDeletion) {
            actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_YES));
        } else {
            actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_CANCEL));
        }

        return Task.where("{0} deletes the clerk");
    }

    public static Performable dismissSuccessConfirmationAfterDeletingClerk(
            Actor actor, boolean withValidationOfTexts) {

        if (withValidationOfTexts) {
            actor.attemptsTo(
                    CommonTasks.validateConfirmationScreen(
                            actor, "DELETE CLERK", "SUCCESS", "Clerk Deleted"));
        }
        actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_DONE));

        return Task.where("{0} dismisses the confirmation after deleting a clerk");
    }

    public static Performable removeClerksDifferentThan(Actor actor, String clerkId) {
        while (actor.asksFor(
                VisibilityQuestion.isPresent(
                        MainClerkManagementScreen.BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN.of(
                                clerkId)))) {
            actor.attemptsTo(
                    ClickAction.on(
                            MainClerkManagementScreen.BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN
                                    .of(clerkId)));
            actor.attemptsTo(
                    deleteClerk(actor, false, true),
                    dismissSuccessConfirmationAfterDeletingClerk(actor, false));
        }

        return Task.where("{0} cleans all clerks");
    }
}
