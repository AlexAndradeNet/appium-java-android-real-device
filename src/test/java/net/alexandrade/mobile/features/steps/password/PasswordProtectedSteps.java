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
package net.alexandrade.mobile.features.steps.password;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.MainTileScreenTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.alexandrade.mobile.screenplay.ui.settle.MainSettleScreen;
import net.alexandrade.mobile.screenplay.ui.voidtile.VoidMainScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class PasswordProtectedSteps {
    @Given("{actor} defined the minimum access level as {word} and opened the {word} option,")
    public void theCompanyDefinedThatTheMinimumAccessLevelForFunctionIsAdmin(
            Actor actor, String role, String functionality) {
        // Pending VHQ implementation

        switch (functionality) {
            case "Refund" -> actor.attemptsTo(MainTileScreenTasks.openRefund(actor));
            case "Moto" -> actor.attemptsTo(MainTileScreenTasks.openMoto(actor));
            case "Settle" -> actor.attemptsTo(MainTileScreenTasks.openBatchOrSettle(actor));
            case "Void" -> actor.attemptsTo(MainTileScreenTasks.openVoid(actor));
            default ->
                    throw new IllegalArgumentException("Invalid functionality: " + functionality);
        }
    }

    @When("he attempts to login in {word} using the {word}, with ID {word} and Password {word},")
    public void heAttemptsToDoAFunctionalityUsingTheRoleWithIDClerkIDAndPasswordPassword(
            String functionality, String role, String clerkID, String password) {

        Actor actor = OnStage.theActorInTheSpotlight();
        // Ensure the title matches the expected functionality
        String expectedTitle = functionality.toUpperCase();

        actor.attemptsTo(LoginAsTasks.fillClerkID(actor, expectedTitle, clerkID));

        boolean wasTheUserAllowedToContinue =
                actor.asksFor(
                        VisibilityQuestion.isPresent(
                                NumericScreen.LABEL_REASON.of("Enter your Password")));

        // Check if the "Enter your Password" label is visible and fill the password
        if (wasTheUserAllowedToContinue) {
            actor.attemptsTo(LoginAsTasks.fillPassword(actor, expectedTitle, password));
        }
    }

    @Then("he should {word} access the {word} option as {word}.")
    public void heShouldAccessAccessTheRefundOption(
            String access, String functionality, String role) {

        Actor actor = OnStage.theActorInTheSpotlight();

        String expectedTitle = functionality.toUpperCase();

        if (access.equals("have")) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the title: '%s' meaning have access"
                                            .formatted(expectedTitle),
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.TITLE.of(expectedTitle)))
                            .isTrue());

            if (functionality.equals("Refund") || functionality.equals("Moto")) {
                actor.attemptsTo(
                        Ensure.that(
                                        "Should see the reason label: '%s'"
                                                .formatted(
                                                        "Enter %s Amount".formatted(functionality)),
                                        VisibilityQuestion.isPresent(
                                                NumericScreen.LABEL_REASON.of(
                                                        "Enter %s Amount"
                                                                .formatted(functionality))))
                                .isTrue());
            }

            if (functionality.equals("Settle")) {
                actor.attemptsTo(
                        Ensure.that(
                                        "Should see the label Summary",
                                        VisibilityQuestion.isPresent(
                                                MainSettleScreen.LABEL_SUMMARY))
                                .isTrue());
            }

            if (functionality.equals("Void")) {
                actor.attemptsTo(
                        Ensure.that(
                                        "Should see the 'Select transaction to void' label",
                                        VisibilityQuestion.isPresent(
                                                VoidMainScreen.LABEL_SELECT_TRANSACTION_TO_VOID))
                                .isTrue());
            }
        }

        if (access.equals("haven't")) {
            actor.attemptsTo(
                    CommonTasks.validateAndDismissPopupAlertWithOkButton(
                            actor, "Alert Message", "Access Not Granted!"),
                    Ensure.that(
                                    "Should see the main screen title: '%s'"
                                            .formatted(expectedTitle),
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.TITLE.of(expectedTitle)))
                            .isTrue(),
                    Ensure.that(
                                    "Should see the reason label: 'Enter your Clerk ID'",
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.LABEL_REASON.of("Enter your Clerk ID")))
                            .isTrue());
        }
    }
}
