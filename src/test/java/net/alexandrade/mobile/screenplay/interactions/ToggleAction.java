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
import org.openqa.selenium.WebElement;

public class ToggleAction implements Interaction {

    private final Target target;
    private final WebElement webElement;
    private final boolean enabled;

    protected ToggleAction(Target target, WebElement webElement, boolean enabled) {
        this.target = target;
        this.webElement = webElement;
        this.enabled = enabled;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String translatedStatus = enabled ? "ON" : "OFF";
        WebElement element = (webElement == null) ? target.resolveFor(actor) : webElement;

        String currentToggleStatus = element.getText();
        currentToggleStatus =
                currentToggleStatus.replaceAll("\\p{C}", ""); // Removes control characters

        if (!currentToggleStatus.equals(translatedStatus)) {
            actor.attemptsTo(ClickAction.on(element));
        }
    }

    public static ToggleAction toOn(Target target) {
        return instrumented(ToggleAction.class, target, null, true);
    }

    public static ToggleAction toOn(WebElement element) {
        return instrumented(ToggleAction.class, null, element, true);
    }

    public static ToggleAction toOff(Target target) {
        return instrumented(ToggleAction.class, target, null, false);
    }

    public static ToggleAction toOff(WebElement element) {
        return instrumented(ToggleAction.class, null, element, false);
    }
}
