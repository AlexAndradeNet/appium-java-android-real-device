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
package com.nuvei.features.steps.settings;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.tasks.commons.LoginFillClerkIDTasks;
import com.nuvei.screenplay.tasks.commons.LoginFillPasswordTasks;
import com.nuvei.screenplay.tasks.dashboard.DashboardTIDTask;
import com.nuvei.screenplay.tasks.dashboard.ReturnToInitialScreenTask;
import com.nuvei.screenplay.tasks.settings.MainSettingsTasks;
import com.nuvei.screenplay.ui.CommonObjects;
import com.nuvei.screenplay.ui.DashboardScreen;
import com.nuvei.screenplay.ui.common.NumericScreen;
import com.nuvei.screenplay.ui.settings.SemiIntegrationOptionsScreen;
import com.nuvei.screenplay.ui.tid.MainTIDScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class SemiIntegrationSettingsSteps {
    private static final String SEMI_INTEGRATION_PASSWORD_TITLE = "CLERK";

    @Given("{actor} is managing the Semi-Integration settings")
    public void aurelianoIsManagingTheSemiIntegrationSettings(Actor actor) {
        actor.attemptsTo(MainSettingsTasks.openSemiIntegrationOptionsScreen(actor));
    }

    @When("he attempts to deactivate the Semi-Integration feature")
    public void heDeactivatesTheSemiIntegrationFeature() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOff(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION));
    }

    @Then("he should see in the terminal’s information that Semi-Integration is not active")
    public void heShouldSeeInTheTerminalSInformationThatSemiIntegrationIsNotActive() {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToTheDashboardScreen(actor),
                DashboardTIDTask.open(),
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

    @When("he attempts to activate the Semi-Integration feature leaving the password prompt off")
    public void heActivatesTheSemiIntegrationFeatureButLeavesThePasswordPromptOff() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOn(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION),
                        ToggleAction.toOff(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_STANDALONE_PASSWORD),
                        ClickAction.on(SemiIntegrationOptionsScreen.RADIO_RETAIL));
    }

    @Then("he should see that the terminal indicates Semi-Integration is active")
    public void heShouldSeeThatTheTerminalIndicatesSemiIntegrationIsActive() {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToTheDashboardScreen(actor),
                DashboardTIDTask.open(),
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
                        .isEqualTo("18080"));
    }

    @And(
            "he should see that NO Idle screen appears after waiting {int} seconds on the Main"
                    + " Screen")
    public void heShouldSeeThatNoPasswordPromptIsRequiredAfterSeconds(int seconds) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.returnToTheDashboardScreen(actor), ReturnToInitialScreenTask.now());

        actor.attemptsTo(
                WaitAction.forSpecificTime(seconds + 2),
                Ensure.that(
                                "Should see the Main Screen is still there",
                                VisibilityQuestion.isPresent(
                                        DashboardScreen.BUTTON_SALE_TRANSACTION))
                        .isTrue());
    }

    @When("he attempts to activate the Semi-Integration feature and the password prompt")
    public void heActivatesTheSemiIntegrationFeatureAndThePasswordPrompt() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOn(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION),
                        ToggleAction.toOn(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_STANDALONE_PASSWORD),
                        ClickAction.on(SemiIntegrationOptionsScreen.RADIO_RETAIL));
    }

    @And(
            "he should see that the Idle screen appears after waiting {int} seconds on the Main"
                    + " Screen(.|,)")
    public void heShouldSeeThatTheIdleScreenAfterWaitingSecondsOnTheMainScreen(
            int secondsForPasswordScreenToAppear) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                WaitUntil.the(CommonObjects.BUTTON_GO_TO_STANDALONE, isEnabled())
                        .forNoMoreThan(secondsForPasswordScreenToAppear + 2)
                        .seconds());

        actor.attemptsTo(
                Ensure.that(
                                "Should see the Idle Screen is there",
                                VisibilityQuestion.isPresent(CommonObjects.BUTTON_GO_TO_STANDALONE))
                        .isTrue());
    }

    @Given(
            "{actor} defined the minimum access level as {word} and is in the Semi-Integration Idle"
                    + " Screen")
    public void aurelianoIsInTheSemiIntegrationIdleScreen(Actor actor, String role) {
        final int secondsForPasswordScreenToAppear = 0;
        heShouldSeeThatTheIdleScreenAfterWaitingSecondsOnTheMainScreen(
                secondsForPasswordScreenToAppear);
    }

    @When("he attempts to log-in to Standalone Mode as {word} with ID {word}")
    public void heLogsInToStandaloneModeAsRole(String role, String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(ClickAction.on(CommonObjects.BUTTON_GO_TO_STANDALONE));

        actor.attemptsTo(LoginFillClerkIDTasks.withDetails(clerkId));
    }

    @When("he attempts to log-in to Standalone Mode with ID {word} and password {word}")
    public void heLogsInToStandaloneMode(String clerkId, String password) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(ClickAction.on(CommonObjects.BUTTON_GO_TO_STANDALONE));

        actor.attemptsTo(
                LoginFillClerkIDTasks.withDetails(SEMI_INTEGRATION_PASSWORD_TITLE, clerkId),
                LoginFillPasswordTasks.withDetails(SEMI_INTEGRATION_PASSWORD_TITLE, password));
    }

    @Then("he should see the Main screen")
    public void heShouldSeeTheMainScreen() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see the Main Screen is there",
                                        VisibilityQuestion.isPresent(
                                                DashboardScreen.BUTTON_SALE_TRANSACTION))
                                .isTrue());
    }

    @Given("{actor} is logged in Standalone Mode with ID {word} and password {word}")
    public void aurelianoIsLoggedInStandaloneModeWithIDAndPassword(
            Actor actor, String clerkId, String password) {
        heLogsInToStandaloneMode(clerkId, password);
    }

    @Then("he should see be REJECTED to access the Standalone Mode as {word}")
    public void heShouldSeeBeREJECTEDToAccessTheStandaloneModeAsRole(String role) {
        Actor actor = OnStage.theActorInTheSpotlight();

        String expectedTitle = "Semi-Integration";

        actor.attemptsTo(
                CommonTasks.validateAndDismissPopupAlertWithOkButton(
                        actor, "Alert Message", "Access Not Granted!"),
                Ensure.that(
                                "Should see the main screen title: '%s'".formatted(expectedTitle),
                                VisibilityQuestion.isPresent(NumericScreen.TITLE.of(expectedTitle)))
                        .isTrue(),
                Ensure.that(
                                "Should see the reason label: 'Enter your Clerk ID'",
                                VisibilityQuestion.isPresent(
                                        NumericScreen.LABEL_REASON.of("Enter your Clerk ID")))
                        .isTrue());
    }

    @When(
            "he attempts to deactivate the Semi-Integration feature leaving the password prompt"
                    + " active")
    public void heDeactivatesTheSemiIntegrationFeatureButLeavesThePasswordPromptActive() {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                MainSettingsTasks.openSemiIntegrationOptionsScreen(actor),
                ToggleAction.toOff(SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION),
                ToggleAction.toOn(SemiIntegrationOptionsScreen.TOGGLE_ENABLE_STANDALONE_PASSWORD),
                ClickAction.on(SemiIntegrationOptionsScreen.RADIO_RETAIL));
    }

    @When("he attempts to deactivate the Semi-Integration feature and the password prompt")
    public void heDeactivatesTheSemiIntegrationFeatureAndThePasswordPrompt() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ToggleAction.toOff(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION),
                        ToggleAction.toOff(
                                SemiIntegrationOptionsScreen.TOGGLE_ENABLE_STANDALONE_PASSWORD),
                        ClickAction.on(SemiIntegrationOptionsScreen.RADIO_RETAIL));
    }

    @Then("he should immediately see the Idle screen after returning to the Main Screen")
    public void heShouldSeeImmediatelyTheIdleScreenAfterReturningToTheMainScreen() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(CommonTasks.returnToTheDashboardScreen(actor));
        heShouldSeeThatTheIdleScreenAfterWaitingSecondsOnTheMainScreen(0);
    }
}
