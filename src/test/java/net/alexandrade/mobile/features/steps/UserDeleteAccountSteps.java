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
package net.alexandrade.mobile.features.steps;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.questions.SelectedAccountQuestion;
import net.alexandrade.mobile.screenplay.tasks.DeleteAccountTask;

public class UserDeleteAccountSteps {

    @When("wants to delete its account")
    public void wantsToDeleteItsAccount() {
        theActorInTheSpotlight().attemptsTo(DeleteAccountTask.currently());
    }

    @Then("see the accounts disappears")
    public void seeTheNumbersOfItemsIs() {
        theActorInTheSpotlight().should(seeThat(SelectedAccountQuestion.disappear(), is(true)));
    }
}
