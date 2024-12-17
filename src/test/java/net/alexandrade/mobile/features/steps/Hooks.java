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

import static net.alexandrade.mobile.screenplay.ui.MainTilePage.BUTTON_SALE_TRANSACTION;
import static net.alexandrade.mobile.screenplay.ui.MainTilePage.SPINNER;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotPresent;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.interactions.Tap;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class Hooks {

    @Before(order = 1)
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("{actor} is in the Main Screen")
    public void theActorIsInTheHomePage(Actor theActor) {
        theActor.can(BrowseTheWeb.with(Serenity.getDriver()));
    }

    @After
    public void afterAll() {
        OnStage.drawTheCurtain();
        Serenity.getDriver().quit();
    }

    @When("{actor} opens the {string} main tile")
    public void opensTheMainTile(Actor theActor, String transactionType) {
        theActor.attemptsTo(
                WaitUntil.the(SPINNER, isNotPresent()).forNoMoreThan(120).seconds(),
                Tap.on(BUTTON_SALE_TRANSACTION));
    }
}
