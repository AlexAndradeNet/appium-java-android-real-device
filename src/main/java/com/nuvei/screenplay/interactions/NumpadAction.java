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

import com.nuvei.screenplay.ability.BrowseTheApp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class NumpadAction implements Interaction {

    private final String numberSequence;
    private final Target onScreenNumpadNumberButton;
    private final Map<String, WebElementFacade> cachedTargets = new ConcurrentHashMap<>();

    private NumpadAction(Target onScreenNumpadNumberButton, String numberSequence) {
        this.onScreenNumpadNumberButton = onScreenNumpadNumberButton;
        this.numberSequence = numberSequence.replace("\"", "");
    }

    public static NumpadAction digit(Target onScreenNumpadNumberButton, String numberSequence) {
        return new NumpadAction(onScreenNumpadNumberButton, numberSequence);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (onScreenNumpadNumberButton == null) {
            performOnPhysicalNumpad(actor);
        } else {
            performOnOnScreenNumpad(actor);
        }
    }

    private void performOnOnScreenNumpad(Actor actor) {
        List<ClickAction> tapActions =
                numberSequence
                        .chars()
                        .mapToObj(c -> String.valueOf((char) c)) // Convert each character to String
                        .map(digit -> ClickAction.on(getOrCreateButtonForDigit(actor, digit)))
                        .toList();

        actor.attemptsTo(tapActions.toArray(new Performable[0]));
    }

    private void performOnPhysicalNumpad(Actor actor) {
        Map<String, Object> adbCommand = new HashMap<>();
        adbCommand.put("command", "input"); // Command name (e.g., "ls", "input", etc.)
        adbCommand.put(
                "args",
                "keyboard text \"%s\""
                        .formatted(numberSequence)); // Command arguments (e.g., "/sdcard"

        var driver = actor.usingAbilityTo(BrowseTheApp.class).driver();
        driver.executeScript("mobile: shell", adbCommand);
    }

    private WebElement getOrCreateButtonForDigit(Actor actor, String digit) {
        // Cache the Target only if it doesn't exist
        return cachedTargets.computeIfAbsent(
                digit, d -> onScreenNumpadNumberButton.of(digit).resolveFor(actor));
    }
}
