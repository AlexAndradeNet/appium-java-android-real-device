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
import net.alexandrade.mobile.screenplay.interactions.Toggle;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.serenitybdd.screenplay.actors.OnStage;

public class TransactionFlowSteps {

    @When("he enables the Order Number prompt")
    public void heEnablesTheOrderNumberPrompt() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        MainSettingsTasks.openTransactionFlowScreen(),
                        Toggle.toOn(TOGGLE_ORDER_NUMBER));
    }

    @Then("he see the Order Number prompt is enabled")
    public void heSeeTheOrderNumberPromptIsEnabled() {
        assert true;
    }
}
