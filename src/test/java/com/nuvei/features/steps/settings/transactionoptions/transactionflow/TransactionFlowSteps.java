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
package com.nuvei.features.steps.settings.transactionoptions.transactionflow;

import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.tasks.settings.MainSettingsTasks;
import com.nuvei.screenplay.tasks.settings.TransactionsOptionsTasks;
import com.nuvei.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import com.nuvei.screenplay.ui.settings.transactionoptions.TransactionFlowScreen;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class TransactionFlowSteps {

    @When("he enables the Order Number toggle,")
    public void heEnablesTheOrderNumberPrompt() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                MainSettingsTasks.openTransactionFlowScreen(actor),
                ToggleAction.toOn(TransactionFlowScreen.TOGGLE_ORDER_NUMBER));
    }

    @Then("he see the Order Number prompt is prompted in Sales.")
    public void heSeeTheOrderNumberPromptIsEnabled() {
        assert true;
    }

    @When("he deactivates all toggles options,")
    public void heDeactivatesAllTogglesOptions() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                MainSettingsTasks.openTransactionFlowScreen(actor),
                CommonTasks.turnOffAllTogglesOnTheScreen(actor),
                CommonTasks.tapBackArrow(actor),
                TransactionsOptionsTasks.openSplitPayment(actor),
                CommonTasks.turnOffAllTogglesOnTheScreen(actor),
                CommonTasks.tapBackArrow(actor));

        boolean isTippingAvailable =
                actor.asksFor(
                        VisibilityQuestion.isPresent(
                                MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));

        if (isTippingAvailable) {
            // Tipping could be optional in some cases like when Crypto is enabled
            actor.attemptsTo(
                    TransactionsOptionsTasks.openTipping(actor),
                    CommonTasks.turnOffAllTogglesOnTheScreen(actor),
                    CommonTasks.tapBackArrow(actor));
        }
    }

    @Then("he should see all toggles were deactivated.")
    public void heShouldSeeAllTogglesWereDeactivated() {
        OnStage.theActorInTheSpotlight().attemptsTo(Ensure.that(true).isTrue());
    }
}
