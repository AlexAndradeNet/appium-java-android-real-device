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

import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.interactions.EnterAction;
import net.alexandrade.mobile.screenplay.interactions.WaitSpecificTime;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.ui.ConfirmationScreen;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.*;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

public class ClerkManagementTasks {

    private ClerkManagementTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable openAccountDetailsForProfile(String clerkId) {
        return Task.where(
                "{0} opens the Transaction Options > Transaction Flow screen",
                actor -> {
                    Target target = MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(clerkId);
                    actor.attemptsTo(
                            CommonTasks.navigateMenuUntilElementIsVisible(target),
                            ClickAction.on(target));
                });
    }

    public static Performable changePassword(String password, String confirmationPassword) {
        return Task.where(
                "{0} changes the current user password",
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_PASSWORD),
                EnterAction.theValue(password).into(ChangePasswordScreen.TEXTBOX_PASSWORD),
                EnterAction.theValue(confirmationPassword)
                        .into(ChangePasswordScreen.TEXTBOX_CONFIRM),
                ClickAction.on(ChangePasswordScreen.BUTTON_CONFIRM));
    }

    public static Performable addANewUser(
            boolean withTextValidation, String clerkId, String role, String password) {
        return Task.where(
                "{0} adds a new user",
                actor -> {
                    actor.attemptsTo(ClickAction.on(MainClerkManagementScreen.BUTTON_ADD_NEW_USER));

                    if (withTextValidation) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the title",
                                                TextQuestion.of(AddUserScreen.TITLE))
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
                                                "Verify that the role in the screen is \"%s\""
                                                        .formatted(role),
                                                TextQuestion.of(AddUserScreen.TITLE))
                                        .isEqualToIgnoringCase("NEW " + role.toUpperCase()));
                    }

                    actor.attemptsTo(
                            ClickAction.on(AddUserScreen.BUTTON_CONFIRM),
                            WaitSpecificTime.forSeconds(1));
                });
    }

    public static Performable dismissSuccessConfirmationAfterAddingANewUser() {
        return Task.where(
                "{0} dismisses the confirmation after adding a new user",
                CommonTasks.validateAndDismissConfirmationScreenWithDoneButton(
                        "ADD NEW CLERK", "SUCCESS", "Clerk Created"));
    }

    private static Target getTargetForRole(String role) {
        return switch (role) {
            case "Admin" -> ChangeClerkRoleScreen.BUTTON_ROLE_ADMIN;
            case "Manager" -> ChangeClerkRoleScreen.BUTTON_ROLE_MANAGER;
            case "Employee" -> ChangeClerkRoleScreen.BUTTON_ROLE_EMPLOYEE;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

    public static Performable searchForClerkId(String clerkId) {
        return Task.where(
                "{0} searches for the clerk ID",
                EnterAction.theValue(clerkId).into(MainClerkManagementScreen.TEXTBOX_SEARCH));
    }

    public static Performable changeClerkId(String newClerkId) {
        return Task.where(
                "{0} changes the clerk ID",
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ID),
                EnterAction.theValue(newClerkId).into(ChangeClerkIDScreen.TEXTBOX_NEW_CLERK_ID),
                Ensure.that(
                                "The title should be correct",
                                VisibilityQuestion.isPresent(ChangeClerkIDScreen.TITLE))
                        .isTrue(),
                ClickAction.on(ChangeClerkIDScreen.BUTTON_CONFIRM));
    }

    public static Performable changeRole(String newRole) {
        return Task.where(
                "{0} changes the role",
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE),
                WaitSpecificTime.forSeconds(2),
                Ensure.that(
                                "The title should be correct",
                                VisibilityQuestion.isPresent(ChangeClerkRoleScreen.TITLE))
                        .isTrue(),
                ClickAction.on(getTargetForRole(newRole)));
    }

    public static Performable deleteClerk(boolean withValidationOfTexts, boolean confirmDeletion) {
        return Task.where(
                "{0} deletes the clerk",
                actor -> {
                    actor.attemptsTo(
                            ClickAction.on(ViewClerkScreen.BUTTON_DELETE_USER)
                            // WaitSpecificTime.forSeconds(2)
                            );

                    if (withValidationOfTexts) {
                        actor.attemptsTo(
                                CommonTasks.validateConfirmationScreen(
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
                });
    }

    public static Performable dismissSuccessConfirmationAfterDeletingClerk(
            boolean withValidationOfTexts) {
        return Task.where(
                "{0} dismisses the confirmation after deleting a clerk",
                actor -> {
                    if (withValidationOfTexts) {
                        actor.attemptsTo(
                                CommonTasks.validateConfirmationScreen(
                                        "DELETE CLERK", "SUCCESS", "Clerk Deleted"));
                    }
                    actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_DONE));
                });
    }

    public static Performable removeClerksDifferentThan(String clerkId) {
        return Task.where(
                "{0} cleans all clerks",
                actor -> {
                    while (actor.asksFor(
                            VisibilityQuestion.isPresent(
                                    MainClerkManagementScreen
                                            .BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN
                                            .of(clerkId)))) {
                        actor.attemptsTo(
                                ClickAction.on(
                                        MainClerkManagementScreen
                                                .BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN
                                                .of(clerkId)),
                                deleteClerk(false, true),
                                dismissSuccessConfirmationAfterDeletingClerk(false));
                    }
                });
    }
}
