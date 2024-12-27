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
    public static Performable addANewUser(String clerkId, String role, String password) {
        return Task.where(
                "{0} adds a new user",
                TapAction.on(MainClerkManagementScreen.BUTTON_ADD_NEW_USER),
                TapAction.on(getTargetForRole(role)),
                Ensure.that(TextQuestion.of(AddUserScreen.TITLE))
                        .isEqualTo("NEW " + role.toUpperCase()),
                EnterAction.theValue(clerkId).into(AddUserScreen.TEXTBOX_USER_ID),
                EnterAction.theValue(password).into(AddUserScreen.TEXTBOX_PASSWORD),
                EnterAction.theValue(password).into(AddUserScreen.TEXTBOX_CONFIRM),
                TapAction.on(AddUserScreen.BUTTON_CONFIRM),
                WaitSpecificTime.forSeconds(1),
                Ensure.that(TextQuestion.of(ConfirmationScreen.TITLE)).isEqualTo("ADD NEW CLERK"),
                Ensure.that(TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                        .isEqualTo("SUCCESS"),
                Ensure.that(TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_DETAIL))
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
}
