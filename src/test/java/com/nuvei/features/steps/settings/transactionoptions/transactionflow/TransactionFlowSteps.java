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

import com.nuvei.screenplay.driver.AppiumDriver;
import com.nuvei.screenplay.interactions.*;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.DashboardScreenTasks;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.tasks.settings.MainSettingsTasks;
import com.nuvei.screenplay.tasks.settings.TransactionsOptionsTasks;
import com.nuvei.screenplay.ui.CommonObjects;
import com.nuvei.screenplay.ui.NumericScreen;
import com.nuvei.screenplay.ui.common.PrintingScreen;
import com.nuvei.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import com.nuvei.utils.LogcatUtility;
import com.nuvei.utils.ReportUtility;
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
                MainSettingsTasks.openTransactionFlowScreen(actor),
                ToggleAction.toOn(CommonObjects.TOGGLE.of(toggleName)));
        actor.remember("toggleName", toggleName);
    }

    @Then("he should see the {string} is prompted in a Sale")
    public void heSeeThePromptIsEnabled(String toggleLabel) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                CommonTasks.returnToTheDashboardScreen(actor),
                DashboardScreenTasks.returnToInitialScreen(actor),
                DashboardScreenTasks.openSale(actor));

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

    @Then("he should see all toggles were deactivated")
    public void heShouldSeeAllTogglesWereDeactivated() {
        OnStage.theActorInTheSpotlight().attemptsTo(Ensure.that(true).isTrue());
    }

    @And("he gets a transaction approved validating its receipt")
    public void heShouldGetAnApprovalWithTheInvoiceNumber() {
        Actor actor = OnStage.theActorInTheSpotlight();

        AppiumDriver.reAttachDriver();

        actor.attemptsTo(
                WaitAction.untilElementIsPresent(PrintingScreen.BUTTON_RECEIPT_OPTIONS),
                Ensure.that(
                                "Should get an approval",
                                TextQuestion.of(PrintingScreen.LABEL_MESSAGE_TITLE))
                        .isEqualTo("APPROVED"));

        LogcatUtility logcatUtility = new LogcatUtility();
        logcatUtility.startLogcat();

        actor.attemptsTo(
                ClickAction.on(PrintingScreen.BUTTON_RECEIPT_OPTIONS),
                ClickAction.on(PrintingScreen.BUTTON_PAPER_RECEIPT),
                ClickAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_PRINT_MERCHANT),
                WaitAction.untilElementIsPresent(PrintingScreen.DONE),
                ClickAction.on(PrintingScreen.DONE));

        String logcat = logcatUtility.stopLogcat();
        ReportUtility.saveReceipt(logcat);

        String toggleName = actor.recall("toggleName");

        actor.attemptsTo(
                CommonTasks.returnToTheDashboardScreen(actor),
                MainSettingsTasks.openTransactionFlowScreen(actor),
                ToggleAction.toOff(CommonObjects.TOGGLE.of(toggleName)));
    }

    @And("he fills the prompt with {word}")
    public void heFillsThePromptWith(String promptValue) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                NumpadAction.digit(promptValue),
                ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM),
                NumpadAction.digit(SALE_AMOUNT),
                ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));
    }
}
