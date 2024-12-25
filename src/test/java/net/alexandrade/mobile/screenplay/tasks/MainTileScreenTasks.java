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
package net.alexandrade.mobile.screenplay.tasks;

import static net.alexandrade.mobile.screenplay.ui.CommonObjects.BUTTON_ARROW_BACK;
import static net.alexandrade.mobile.screenplay.ui.MainTileScreen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotPresent;

import net.alexandrade.mobile.screenplay.interactions.Tap;
import net.alexandrade.mobile.screenplay.questions.ElementVisibility;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.NoSuchElementException;

public class MainTileScreenTasks {

    private MainTileScreenTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    @Step("{0} waits until the app is fully loaded {1}")
    public static Performable waitTheAppIsFullyLoaded() {
        return Task.where(
                "{0} waits the app is fully loaded",
                WaitUntil.the(SPINNER, isNotPresent()).forNoMoreThan(100).seconds());
    }

    @Step("{0} navigates back to the main screen")
    public static Performable returnToMainScreen() {
        return Task.where(
                "{0} navigates back to the main screen",
                actor -> {
                    try {
                        while (ElementVisibility.isPresent(BUTTON_ARROW_BACK)
                                .answeredBy(actor)
                                .equals(true)) {
                            actor.attemptsTo(Tap.on(BUTTON_ARROW_BACK));
                        }
                    } catch (NoSuchElementException ignored) {
                        // Do nothing
                    }
                });
    }

    @Step("{0} makes the terminal goes back to the main screen {1}")
    public static Performable openSale() {
        return Task.where("{0} opens the Sale main tile", Tap.on(BUTTON_SALE_TRANSACTION));
    }
}
