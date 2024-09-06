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
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.questions.factory.MenuBarWebUI;
import net.alexandrade.mobile.screenplay.tasks.ShowMenuPanelTask;

public class TraderExploreMenuSteps {
    @When("navigates to the menu panel")
    public void navigatesToTheMenuPanel() {
        theActorInTheSpotlight().attemptsTo(ShowMenuPanelTask.openMenuPanel());
    }

    @Then("see the numbers of items is {int}")
    public void seeTheNumbersOfItemsIs(int numberOfElements) {
        theActorInTheSpotlight()
                .should(
                        seeThat(MenuBarWebUI.numberOfItems(), is(numberOfElements)),
                        seeThat(
                                MenuBarWebUI.displayedWithStrictOrder(),
                                contains(
                                        "Trade",
                                        "News",
                                        "Mailbox",
                                        "Journal",
                                        "Settings",
                                        "Economic calendar",
                                        "Traders Community",
                                        "About")));
    }
}
