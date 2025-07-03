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
package com.nuvei.screenplay.tasks.settings.safsettings;

import com.nuvei.screenplay.interactions.NavigateBackAction;
import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.LoginFillClerkIDTask;
import com.nuvei.screenplay.tasks.commons.LoginFillPasswordTask;
import com.nuvei.screenplay.tasks.settings.SettingsSafOpenTask;
import com.nuvei.screenplay.tasks.settings.semiintegration.SemiIntegrationTurnOffTask;
import com.nuvei.screenplay.ui.DashboardScreen;
import com.nuvei.screenplay.ui.settings.SafSettingsScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class SafSettingsTurnOnTask implements Task {

    private final String clerkId;
    private final String password;

    public SafSettingsTurnOnTask(String clerkId, String password) {
        this.clerkId = clerkId;
        this.password = password;
    }

    @Override
    @Step("{0} turns on SAF")
    public <T extends Actor> void performAs(T actor) {
        boolean isSafAlreadyEnabled =
                Boolean.TRUE.equals(
                        actor.asksFor(VisibilityQuestion.isPresent(DashboardScreen.LABEL_SAF)));

        if (!isSafAlreadyEnabled) {
            actor.attemptsTo(
                    SemiIntegrationTurnOffTask.turnOffSemiIntegration(),
                    NavigateBackAction.clickingBackArrow(),
                    SettingsSafOpenTask.now(),
                    LoginFillClerkIDTask.withDetails(clerkId),
                    LoginFillPasswordTask.withDetails(password),
                    ToggleAction.toOn(SafSettingsScreen.TOGGLE_ENABLE_SAF),
                    NavigateBackAction.clickingBackArrow(),
                    NavigateBackAction.clickingBackArrow());
        }
    }

    public static SafSettingsTurnOnTask with(String clerkId, String password) {
        return new SafSettingsTurnOnTask(clerkId, password);
    }
}
