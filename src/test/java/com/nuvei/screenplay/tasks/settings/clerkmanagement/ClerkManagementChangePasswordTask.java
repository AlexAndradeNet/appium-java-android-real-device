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
import com.nuvei.screenplay.ui.settings.clerkmanagement.ChangePasswordScreen;
import com.nuvei.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementChangePasswordTask implements Task {

    private final String password;
    private final String confirmPassword;

    public ClerkManagementChangePasswordTask(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @Override
    @Step("{0} changes the clerk's password")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickAction.on(ViewClerkScreen.BUTTON_CHANGE_PASSWORD),
                EnterAction.into(ChangePasswordScreen.TEXTBOX_PASSWORD, password),
                EnterAction.into(ChangePasswordScreen.TEXTBOX_CONFIRM, confirmPassword),
                ClickAction.on(ChangePasswordScreen.BUTTON_CONFIRM));
    }

    public static ClerkManagementChangePasswordTask fromTo(
            String password, String confirmPassword) {
        return new ClerkManagementChangePasswordTask(password, confirmPassword);
    }
}
