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
package com.nuvei.screenplay.interactions;

import com.nuvei.screenplay.driver.AppiumDriver;
import com.nuvei.screenplay.questions.EnvironmentQuestion;
import com.nuvei.screenplay.ui.NumericScreen;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.WebElement;

public class NumpadAction implements Interaction {
    private final String numberSequence;

    // Thread-safe cache for numeric button Targets. Don't change it to static.
    private final Map<String, WebElementFacade> cachedTargets = new ConcurrentHashMap<>();

    protected NumpadAction(String numberSequence) {
        this.numberSequence = numberSequence;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (actor.asksFor(EnvironmentQuestion.isTSeries())) {
            performOnOnScreenNuveiNumpad(actor);
        } else if (actor.asksFor(EnvironmentQuestion.isMSeries())) {
            performOnPhysicalNumpad();
        } else {
            throw new UnsupportedOperationException("Unknown environment for numpad interaction.");
        }
    }

    private void performOnOnScreenNuveiNumpad(Actor actor) {
        List<ClickAction> tapActions =
                numberSequence
                        .chars()
                        .mapToObj(c -> String.valueOf((char) c)) // Convert each character to String
                        .map(
                                digit ->
                                        ClickAction.on(
                                                getOrCreateButtonForDigit(
                                                        actor, digit))) // Resolve Target
                        .toList();

        actor.attemptsTo(tapActions.toArray(new Performable[0]));
    }

    private void performOnPhysicalNumpad() {
        // Use physical keyboard for M-Series

        // Prepare the ADB shell command
        Map<String, Object> adbCommand = new HashMap<>();
        adbCommand.put("command", "input"); // Command name (e.g., "ls", "pm", etc.)
        adbCommand.put(
                "args",
                "keyboard text \"%s\""
                        .formatted(numberSequence)); // Command arguments (e.g., "/sdcard")

        var driver = AppiumDriver.getDriver();
        driver.executeScript("mobile: shell", adbCommand);
    }

    public static NumpadAction digit(String numberSequence) {
        return Tasks.instrumented(NumpadAction.class, numberSequence);
    }

    private WebElement getOrCreateButtonForDigit(Actor actor, String digit) {
        // Cache the Target only if it doesn't exist
        return cachedTargets.computeIfAbsent(
                digit, d -> NumericScreen.BUTTON_NUMPAD_NUMBER.of(digit).resolveFor(actor));
    }
}
