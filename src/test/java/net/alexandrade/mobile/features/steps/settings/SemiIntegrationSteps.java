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
package net.alexandrade.mobile.features.steps.settings;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.interactions.ToggleAction;
import net.alexandrade.mobile.screenplay.interactions.WaitSpecificTime;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.MainTileScreenTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.alexandrade.mobile.screenplay.ui.CommonObjects;
import net.alexandrade.mobile.screenplay.ui.MainTileScreen;
import net.alexandrade.mobile.screenplay.ui.settings.SemiIntegrationOptionsScreen;
import net.alexandrade.mobile.screenplay.ui.tid.MainTIDScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class SemiIntegrationSteps {
    @Given("{actor} is managing the semi-integration settings,")
    public void aurelianoIsManagingTheSemiIntegrationSettings(Actor actor) {
        actor.attemptsTo(MainSettingsTasks.openSemiIntegrationOptionsScreen(actor));
    }

    @When("he deactivates the semi-integration feature,")
    public void heDeactivatesTheSemiIntegrationFeature() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOff(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION));
    }

    @Then("he should see in the terminal’s information that semi-integration is not active.")
    public void heShouldSeeInTheTerminalSInformationThatSemiIntegrationIsNotActive() {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToMainScreen(actor),
                MainTileScreenTasks.openTIDInfo(actor),
                Ensure.that(
                                "Should not see the Semi-Integration URL",
                                VisibilityQuestion.isPresent(
                                        MainTIDScreen.LABEL_SEMI_INTEGRATION_URL))
                        .isFalse(),
                Ensure.that(
                                "Should not see the Semi-Integration PORT",
                                VisibilityQuestion.isPresent(
                                        MainTIDScreen.LABEL_SEMI_INTEGRATION_PORT))
                        .isFalse());
    }

    @When("he activates the semi-integration feature but leaves the password prompt off,")
    public void heActivatesTheSemiIntegrationFeatureButLeavesThePasswordPromptOff() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOn(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION),
                        ToggleAction.toOff(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_STANDALONE_PASSWORD),
                        ToggleAction.toOn(SemiIntegrationOptionsScreen.RADIO_RETAIL));
    }

    @Then("he should see that the terminal indicates semi-integration is active,")
    public void heShouldSeeThatTheTerminalIndicatesSemiIntegrationIsActive() {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToMainScreen(actor),
                MainTileScreenTasks.openTIDInfo(actor),
                Ensure.that(
                                "Should see the Label for Semi-Integration is present",
                                VisibilityQuestion.isPresent(
                                        MainTIDScreen.LABEL_SEMI_INTEGRATION_URL))
                        .isTrue(),
                Ensure.that(
                                "Should see the text of the Semi-Integration URL",
                                TextQuestion.of(MainTIDScreen.LABEL_SEMI_INTEGRATION_URL))
                        .isEqualToIgnoringCase("terminal-poi-sandbox.nuvei.com"),
                Ensure.that(
                                "Should see the text of the Semi-Integration PORT",
                                TextQuestion.of(MainTIDScreen.LABEL_SEMI_INTEGRATION_PORT))
                        .isNotEqualTo("18080"));
    }

    @And("he should see that no idle screen after waiting {int} seconds on the Main Screen.")
    public void heShouldSeeThatNoPasswordPromptIsRequiredAfterSeconds(int seconds) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToMainScreen(actor),
                WaitSpecificTime.forSeconds(seconds + 2),
                MainTileScreenTasks.openTIDInfo(actor),
                Ensure.that(
                                "Should not see the Main Screen is there",
                                VisibilityQuestion.isPresent(
                                        MainTileScreen.BUTTON_SALE_TRANSACTION))
                        .isTrue());
    }

    @When("he activates the semi-integration feature and the password prompt,")
    public void heActivatesTheSemiIntegrationFeatureAndThePasswordPrompt() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOn(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION),
                        ToggleAction.toOn(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_STANDALONE_PASSWORD),
                        ToggleAction.toOn(SemiIntegrationOptionsScreen.RADIO_RETAIL));
    }

    @And("he should see that the idle screen after waiting {int} seconds on the Main Screen.")
    public void heShouldSeeThatTheIdleScreenAfterWaitingSecondsOnTheMainScreen(int seconds) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToMainScreen(actor),
                WaitSpecificTime.forSeconds(seconds + 2),
                MainTileScreenTasks.openTIDInfo(actor),
                Ensure.that(
                                "Should see the Idle Screen is there",
                                VisibilityQuestion.isPresent(CommonObjects.BUTTON_GO_TO_STANDALONE))
                        .isTrue());
    }

    @Given("{actor} is in the Semi-Integration Idle Screen,")
    public void aurelianoIsInTheSemiIntegrationIdleScreen(Actor actor) {
        boolean isInSemiIntegrationIdleScreen =
                actor.asksFor(VisibilityQuestion.isPresent(CommonObjects.BUTTON_GO_TO_STANDALONE));

        if (!isInSemiIntegrationIdleScreen) {
            heActivatesTheSemiIntegrationFeatureAndThePasswordPrompt();
            heShouldSeeThatTheIdleScreenAfterWaitingSecondsOnTheMainScreen(30);
        }
    }

    @When("he logged-in to Standalone Mode with ID {word} and password {word},")
    public void heLogsInToStandaloneMode(String clerkId, String password) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                ClickAction.on(CommonObjects.BUTTON_GO_TO_STANDALONE),
                LoginAsTasks.as(actor, clerkId, password));
    }

    @Then("he should see the Main screen.")
    public void heShouldSeeTheMainScreen() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see the Main Screen is there",
                                        VisibilityQuestion.isPresent(
                                                MainTileScreen.BUTTON_SALE_TRANSACTION))
                                .isTrue());
    }
}
