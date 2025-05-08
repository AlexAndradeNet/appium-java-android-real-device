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

import com.nuvei.screenplay.ability.BrowseTheApp;
import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.NumpadTask;
import com.nuvei.screenplay.tasks.commons.PrintAndCaptureReceiptTask;
import com.nuvei.screenplay.tasks.dashboard.DashboardSaleTask;
import com.nuvei.screenplay.tasks.dashboard.ReturnToDashboardScreenTask;
import com.nuvei.screenplay.tasks.dashboard.ReturnToInitialScreenTask;
import com.nuvei.screenplay.tasks.settings.SettingsTransactionFlowOpenTask;
import com.nuvei.screenplay.tasks.settings.transactionoptions.TransactionOptionsDeactivateAllTogglesTask;
import com.nuvei.screenplay.ui.CommonObjects;
import com.nuvei.screenplay.ui.common.NumericScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class TransactionFlowSteps {

    static final String SALE_AMOUNT = "1.00";

    @When("he enables the {string} toggle")
    public void heEnablesThePrompt(String toggleName) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                SettingsTransactionFlowOpenTask.now(),
                ToggleAction.toOn(CommonObjects.TOGGLE.of(toggleName)));

        actor.remember("toggleName", toggleName);
    }

    @Then("he should see the {string} is prompted in a Sale")
    public void heSeeThePromptIsEnabled(String toggleLabel) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ReturnToDashboardScreenTask.now(),
                ReturnToInitialScreenTask.now(),
                DashboardSaleTask.open());

        actor.attemptsTo(
                Ensure.that(
                                "Should see the label '%s'".formatted(toggleLabel),
                                VisibilityQuestion.isPresent(
                                        NumericScreen.LABEL_REASON.of(toggleLabel)))
                        .isTrue());
    }

    @When("he deactivates all toggles options")
    public void heDeactivatesAllTogglesOptions() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(TransactionOptionsDeactivateAllTogglesTask.now());
    }

    @Then("he should see all toggles were deactivated")
    public void heShouldSeeAllTogglesWereDeactivated() {
        OnStage.theActorInTheSpotlight().attemptsTo(Ensure.that(true).isTrue());
    }

    @And("he gets a transaction approved validating its receipt")
    public void heShouldGetAnApprovalWithTheInvoiceNumber() {
        Actor actor = OnStage.theActorInTheSpotlight();
        String toggleName = actor.recall("toggleName");

        BrowseTheApp.reAttachDriver(actor);

        actor.attemptsTo(
                PrintAndCaptureReceiptTask.approved(),
                ReturnToDashboardScreenTask.now(),
                SettingsTransactionFlowOpenTask.now(),
                ToggleAction.toOff(CommonObjects.TOGGLE.of(toggleName)));

        actor.remember("toggleName", "");
    }

    @And("he fills the prompt with {word}")
    public void heFillsThePromptWith(String promptValue) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                NumpadTask.digitAndConfirm(promptValue), NumpadTask.digitAndConfirm(SALE_AMOUNT));
    }
}
