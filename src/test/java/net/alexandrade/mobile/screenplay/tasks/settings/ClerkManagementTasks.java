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

import net.alexandrade.mobile.screenplay.interactions.EnterAction;
import net.alexandrade.mobile.screenplay.interactions.TapAction;
import net.alexandrade.mobile.screenplay.interactions.WaitSpecificTime;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.ui.ConfirmationScreen;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.*;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

public class ClerkManagementTasks {

    private ClerkManagementTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    @Step("{0} opens the Transaction Flow settings {1}")
    public static Performable openAccountDetailsForProfile(String clerkId) {
        return Task.where(
                "{0} opens the Transaction Options > Transaction Flow screen",
                TapAction.on(MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(clerkId)));
    }

    @Step("{0} changes the current user password {1} and confirmation {2}")
    public static Performable changePassword(String password, String confirmationPassword) {
        return Task.where(
                "{0} changes the current user password",
                TapAction.on(ViewClerkScreen.BUTTON_CHANGE_PASSWORD),
                EnterAction.theValue(password).into(ChangePasswordScreen.TEXTBOX_PASSWORD),
                EnterAction.theValue(confirmationPassword)
                        .into(ChangePasswordScreen.TEXTBOX_CONFIRM),
                TapAction.on(ChangePasswordScreen.BUTTON_CONFIRM));
    }

    @Step("{0} adds a new user {1}, {2}, and {3}")
    public static Performable addANewUser(
            boolean withTextValidation, String clerkId, String role, String password) {
        return Task.where(
                "{0} adds a new user",
                actor -> {
                    actor.attemptsTo(TapAction.on(MainClerkManagementScreen.BUTTON_ADD_NEW_USER));

                    if (withTextValidation) {
                        actor.attemptsTo(
                                Ensure.that(TextQuestion.of(AddUserScreen.TITLE))
                                        .isEqualTo("ADD NEW CLERK"));
                    }

                    actor.attemptsTo(
                            TapAction.on(getTargetForRole(role)),
                            EnterAction.theValue(clerkId).into(AddUserScreen.TEXTBOX_USER_ID),
                            EnterAction.theValue(password).into(AddUserScreen.TEXTBOX_PASSWORD),
                            EnterAction.theValue(password).into(AddUserScreen.TEXTBOX_CONFIRM));

                    if (withTextValidation) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Verify that the role in the screen is \"%s\""
                                                        .formatted(role),
                                                TextQuestion.of(AddUserScreen.TITLE))
                                        .isEqualTo("NEW " + role.toUpperCase()));
                    }

                    actor.attemptsTo(
                            TapAction.on(AddUserScreen.BUTTON_CONFIRM),
                            WaitSpecificTime.forSeconds(1));
                });
    }

    @Step("{0} dismisses the confirmation after adding a new user")
    public static Performable dismissSuccessConfirmationAfterAddingANewUser() {
        return Task.where(
                "{0} dismisses the confirmation after adding a new user",
                Ensure.that("Visibility of title", TextQuestion.of(ConfirmationScreen.TITLE))
                        .isEqualTo("ADD NEW CLERK"),
                Ensure.that(
                                "Visibility of message title",
                                TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                        .isEqualTo("SUCCESS"),
                Ensure.that(
                                "Visibility of message detail",
                                TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_DETAIL))
                        .isEqualTo("Clerk Created"),
                TapAction.on(ConfirmationScreen.BUTTON_DONE));
    }

    private static Target getTargetForRole(String role) {
        return switch (role) {
            case "Admin" -> ChangeClerkRoleScreen.BUTTON_ROLE_ADMIN;
            case "Manager" -> ChangeClerkRoleScreen.BUTTON_ROLE_MANAGER;
            case "Employee" -> ChangeClerkRoleScreen.BUTTON_ROLE_EMPLOYEE;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }

    @Step("{0} searches for the clerk ID {1}")
    public static Performable searchForClerkId(String clerkId) {
        return Task.where(
                "{0} searches for the clerk ID",
                EnterAction.theValue(clerkId).into(MainClerkManagementScreen.TEXTBOX_SEARCH));
    }

    @Step("{0} changes the clerk ID to {1}")
    public static Performable changeClerkId(String newClerkId) {
        return Task.where(
                "{0} changes the clerk ID",
                TapAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ID),
                EnterAction.theValue(newClerkId).into(ChangeClerkIDScreen.TEXTBOX_NEW_CLERK_ID),
                Ensure.that(
                                "The title should be correct",
                                VisibilityQuestion.isPresent(ChangeClerkIDScreen.TITLE))
                        .isTrue(),
                TapAction.on(ChangeClerkIDScreen.BUTTON_CONFIRM));
    }

    @Step("{0} changes the role to {1}")
    public static Performable changeRole(String newRole) {
        return Task.where(
                "{0} changes the role",
                TapAction.on(ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE),
                WaitSpecificTime.forSeconds(2),
                Ensure.that(
                                "The title should be correct",
                                VisibilityQuestion.isPresent(ChangeClerkRoleScreen.TITLE))
                        .isTrue(),
                TapAction.on(getTargetForRole(newRole)));
    }

    @Step("{0} deletes the clerk")
    public static Performable deleteClerk(boolean withValidationOfTexts, boolean confirmDeletion) {
        return Task.where(
                "{0} deletes the clerk",
                actor -> {
                    actor.attemptsTo(
                            TapAction.on(ViewClerkScreen.BUTTON_DELETE_USER)
                            // WaitSpecificTime.forSeconds(2)
                            );

                    if (withValidationOfTexts) {
                        actor.attemptsTo(
                                Ensure.that(TextQuestion.of(ConfirmationScreen.TITLE))
                                        .isEqualTo("DELETE CLERK"),
                                Ensure.that(TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                                        .isEqualTo("Are you sure you want to delete this clerk?"),
                                Ensure.that(
                                                VisibilityQuestion.isPresent(
                                                        ConfirmationScreen.BUTTON_CANCEL))
                                        .isTrue(),
                                Ensure.that(
                                                VisibilityQuestion.isPresent(
                                                        ConfirmationScreen.BUTTON_YES))
                                        .isTrue());
                    }

                    if (confirmDeletion) {
                        actor.attemptsTo(TapAction.on(ConfirmationScreen.BUTTON_YES));
                    } else {
                        actor.attemptsTo(TapAction.on(ConfirmationScreen.BUTTON_CANCEL));
                    }
                });
    }

    @Step("{0} dismisses the confirmation after deleting a clerk")
    public static Performable dismissSuccessConfirmationAfterDeletingClerk(
            boolean withValidationOfTexts) {
        return Task.where(
                "{0} dismisses the confirmation after deleting a clerk",
                actor -> {
                    if (withValidationOfTexts) {
                        actor.attemptsTo(
                                Ensure.that(TextQuestion.of(ConfirmationScreen.TITLE))
                                        .isEqualTo("DELETE CLERK"),
                                Ensure.that(TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                                        .isEqualTo("SUCCESS"),
                                Ensure.that(
                                                TextQuestion.of(
                                                        ConfirmationScreen.LABEL_MESSAGE_DETAIL))
                                        .isEqualTo("Clerk Deleted"));
                    }
                    actor.attemptsTo(TapAction.on(ConfirmationScreen.BUTTON_DONE));
                });
    }

    public static Performable removeClerksDifferentThan(String clerkId) {
        return Task.where(
                "{0} cleans all clerks",
                actor -> {
                    while (VisibilityQuestion.isPresent(
                                    MainClerkManagementScreen
                                            .BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN
                                            .of(clerkId))
                            .answeredBy(actor)
                            .equals(true)) {
                        actor.attemptsTo(
                                TapAction.on(
                                        MainClerkManagementScreen
                                                .BUTTON_USER_FIRST_PROFILE_ID_DIFFERENT_THAN
                                                .of(clerkId)),
                                deleteClerk(false, true),
                                dismissSuccessConfirmationAfterDeletingClerk(false));
                    }
                });
    }
}
