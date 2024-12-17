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

import io.cucumber.java.en.Then;
import net.alexandrade.mobile.screenplay.ui.SalePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

public class TraderExploreMenuSteps {
    @Then("{actor} see the numbers of items is {int}")
    public void seeTheNumbersOfItemsIs(Actor theActor, int numberOfElements) {
        theActor.attemptsTo(
                Ensure.that(Text.of(SalePage.TITLE).answeredBy(theActor)).isEqualTo("SALE"));
    }
}
