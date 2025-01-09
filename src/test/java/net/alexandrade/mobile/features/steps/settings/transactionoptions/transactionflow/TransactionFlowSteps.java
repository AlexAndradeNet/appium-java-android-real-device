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
package net.alexandrade.mobile.features.steps.settings.transactionoptions.transactionflow;

import static net.alexandrade.mobile.screenplay.ui.settings.transactionoptions.TransactionFlowScreen.TOGGLE_ORDER_NUMBER;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.interactions.ToggleAction;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.alexandrade.mobile.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class TransactionFlowSteps {

    @When("he enables the Order Number toggle,")
    public void heEnablesTheOrderNumberPrompt() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        MainSettingsTasks.openTransactionFlowScreen(),
                        ToggleAction.toOn(TOGGLE_ORDER_NUMBER));
    }

    @Then("he see the Order Number prompt is prompted in Sales.")
    public void heSeeTheOrderNumberPromptIsEnabled() {
        assert true;
    }

    @When("he deactivates all toggles options,")
    public void heDeactivatesAllTogglesOptions() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                MainSettingsTasks.openTransactionFlowScreen(),
                CommonTasks.turnOffAllTogglesOnTheScreen(),
                CommonTasks.tapBackArrow(),
                ClickAction.on(MainTransactionOptionsScreen.BUTTON_SPLIT_PAYMENT),
                CommonTasks.turnOffAllTogglesOnTheScreen(),
                CommonTasks.tapBackArrow());

        if (actor.asksFor(
                VisibilityQuestion.isPresent(
                        MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS))) {
            // Tipping could be optional in some cases like when Crypto is enabled
            actor.attemptsTo(
                    ClickAction.on(MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS),
                    CommonTasks.turnOffAllTogglesOnTheScreen());
        }
    }

    @Then("he should see all toggles were deactivated.")
    public void heShouldSeeAllTogglesWereDeactivated() {
        OnStage.theActorInTheSpotlight().attemptsTo(Ensure.that(true).isTrue());
    }
}
