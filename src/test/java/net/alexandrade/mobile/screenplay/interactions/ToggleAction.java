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

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

public class ToggleAction implements Interaction {

    private final Target target;
    private final boolean enabled;

    protected ToggleAction(Target target, boolean enabled) {
        this.target = target;
        this.enabled = enabled;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String translatedStatus = enabled ? "ON" : "OFF";
        String currentToggleStatus = target.resolveFor(actor).getText();
        currentToggleStatus =
                currentToggleStatus.replaceAll("\\p{C}", ""); // Removes control characters

        if (!currentToggleStatus.equals(translatedStatus)) {
            actor.attemptsTo(ClickAction.on(element));
        }
    }

    public static ToggleAction toOn(Target target) {
        return instrumented(ToggleAction.class, target, true);
    }

    public static ToggleAction toOff(Target target) {
        return instrumented(ToggleAction.class, target, false);
    }
}
