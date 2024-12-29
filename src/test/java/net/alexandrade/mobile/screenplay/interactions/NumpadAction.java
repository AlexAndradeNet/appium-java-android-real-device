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
package net.alexandrade.mobile.screenplay.interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.alexandrade.mobile.screenplay.driver.AppiumDriver;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import org.openqa.selenium.WebElement;

public class NumpadAction implements Interaction {

    private enum NumpadType {
        NUVEI,
        ANDROID
    }

    // Cache for numeric button Targets
    private final Map<String, WebElement> cachedTargets = new HashMap<>();
    private final String text;
    private final NumpadType numpadType;

    protected NumpadAction(NumpadType numpadType, String text) {
        this.text = text;
        this.numpadType = numpadType;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (numpadType == NumpadType.ANDROID) {
            performAndroidNumpadAction();
        } else {
            performNuveisNumpadAction(actor);
        }
    }

    private void performAndroidNumpadAction() {
        AppiumDriver.getDriver().navigate().back();
    }

    private <T extends Actor> void performNuveisNumpadAction(T actor) {
        List<TapAction> tapActions =
                text.chars()
                        .mapToObj(c -> String.valueOf((char) c)) // Convert each character to String
                        .map(
                                digit ->
                                        TapAction.on(
                                                getOrCreateButtonForDigit(
                                                        actor, digit))) // Resolve Target
                        .toList();

        actor.attemptsTo(tapActions.toArray(new Performable[0]));
    }

    public static NumpadAction onNuveisNumpad(String text) {
        return instrumented(NumpadAction.class, NumpadType.NUVEI, text);
    }

    public static NumpadAction dismissAndroidsNumpad() {
        return instrumented(NumpadAction.class, NumpadType.ANDROID, "");
    }

    private WebElement getOrCreateButtonForDigit(Actor actor, String digit) {
        // Cache the Target only if it doesn't exist
        return cachedTargets.computeIfAbsent(
                digit, d -> NumericScreen.BUTTON_NUMBER.of(digit).resolveFor(actor));
    }
}
