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

import com.nuvei.screenplay.interactions.SkipScenarioAction;
import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.questions.EnvironmentQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.tasks.commons.LoginFillClerkIDTasks;
import com.nuvei.screenplay.tasks.commons.LoginFillPasswordTasks;
import com.nuvei.screenplay.tasks.dashboard.DashboardSettingsTask;
import com.nuvei.screenplay.ui.DashboardScreen;
import com.nuvei.screenplay.ui.settings.SafSettingsScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class SafSettingsTasks {
    private SafSettingsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable turnOnSaf(Actor actor, String clerkId, String password) {
        actor.attemptsTo(skipIfTheFunctionIsNotAvailable(actor));

        if (actor.asksFor(VisibilityQuestion.isPresent(DashboardScreen.LABEL_SAF))) {
            return Task.where("{0} SAF is already enabled");
        }

        actor.attemptsTo(
                SemiIntegrationTasks.turnOffSemiIntegration(actor),
                CommonTasks.tapBackArrow(actor),
                MainSettingsTasks.openSafOptionsScreen(actor),
                LoginFillClerkIDTasks.withDetails(clerkId),
                LoginFillPasswordTasks.withDetails(password));
        actor.attemptsTo(ToggleAction.toOn(SafSettingsScreen.TOGGLE_ENABLE_SAF));
        actor.attemptsTo(goToMainScreen(actor));

        return Task.where("{0} turns on SAF");
    }

    public static Performable turnOffSaf(Actor actor, String clerkId, String password) {

        actor.attemptsTo(skipIfTheFunctionIsNotAvailable(actor));

        if (actor.asksFor(VisibilityQuestion.notPresent(DashboardScreen.LABEL_SAF))) {
            return Task.where("{0} SAF is already disabled");
        }

        actor.attemptsTo(
                DashboardSettingsTask.open(),
                MainSettingsTasks.openSafOptionsScreen(actor),
                LoginFillClerkIDTasks.withDetails(clerkId),
                LoginFillPasswordTasks.withDetails(password));

        actor.attemptsTo(ToggleAction.toOff(SafSettingsScreen.TOGGLE_ENABLE_SAF));

        actor.attemptsTo(goToMainScreen(actor));

        return Task.where("{0} turns off SAF");
    }

    private static Performable goToMainScreen(Actor actor) {
        actor.attemptsTo(CommonTasks.tapBackArrow(actor), CommonTasks.tapBackArrow(actor));
        return Task.where("{0} goes to the main screen");
    }

    public static Performable skipIfTheFunctionIsNotAvailable(Actor actor) {
        if (!actor.asksFor(EnvironmentQuestion.isTSeries())) {
            actor.attemptsTo(
                    SkipScenarioAction.withReason("SAF is not available in P and M-Series"));
        }
        return Task.where("{0} is in a T-Series device");
    }
}
