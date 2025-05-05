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
package com.nuvei.features.steps.help;

import com.nuvei.screenplay.interactions.SwipeAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.DashboardScreenTasks;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.tasks.commons.LoginFillPasswordTasks;
import com.nuvei.screenplay.tasks.help.MainHelpTasks;
import com.nuvei.screenplay.ui.common.NumericScreen;
import com.nuvei.screenplay.ui.help.MainHelpScreen;
import com.nuvei.screenplay.ui.tid.MainTIDScreen;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class HelpDeskSteps {
    @When("he attempts to open the help menu with a calculated super-password")
    public void heAttemptsToOpenTheHelpMenuWithACalculatedSuperPassword() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(DashboardScreenTasks.openTIDInfo(actor));
        String tid = actor.asksFor(TextQuestion.of(MainTIDScreen.LABEL_TID));
        actor.attemptsTo(
                CommonTasks.tapBackArrow(actor),
                SwipeAction.toRight(), // For M-Series
                SwipeAction.toRight() // For M-Series
                );
        actor.attemptsTo(
                DashboardScreenTasks.openHelpDeskMenu(actor, true),
                MainHelpTasks.resolveSuperPassword(tid));
    }

    @Then("he should have access to the help menu")
    public void heShouldHaveAccessToTheHelpMenu() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see the Clear Reversal Option from the Help Menu",
                                        VisibilityQuestion.isPresent(
                                                MainHelpScreen.OPTION_CLEAR_REVERSAL))
                                .isTrue());
    }

    @When("he attempts to open the help menu with password {word}")
    public void heAttemptsToOpenTheHelpMenuWithPassword(String password) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                DashboardScreenTasks.openHelpDeskMenu(actor, true),
                LoginFillPasswordTasks.withDetails(password));
    }

    @Then("he should see the message {string} with description {string}")
    public void heShouldSeeTheMessage(String alertTitle, String alertDescription) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.validateAndDismissPopupAlertWithOkButton(
                        actor, alertTitle, alertDescription),
                Ensure.that(
                                "Should see the Help Desk subtitle",
                                VisibilityQuestion.isPresent(
                                        NumericScreen.LABEL_REASON.of("Enter Super Password")))
                        .isTrue());
    }
}
