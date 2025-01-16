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

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class RadioAction implements Interaction {

    private final Target target;
    private final WebElement element;
    private final boolean checked;

    protected RadioAction(Target target, WebElement element, boolean checked) {
        this.target = target;
        this.element = element;
        this.checked = checked;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        String currentToggleStatus =
                (element != null)
                        ? element.getDomProperty("checked")
                        : target.resolveFor(actor).getDomProperty("checked");

        String translatedStatus = checked ? "true" : "false";

        if (!translatedStatus.equals(currentToggleStatus)) {
            actor.attemptsTo(ClickAction.on(element));
        }
    }

    public static RadioAction toOn(Target target) {
        return Tasks.instrumented(RadioAction.class, target, null, true);
    }

    public static RadioAction toOn(WebElement element) {
        return Tasks.instrumented(RadioAction.class, null, element, true);
    }

    public static RadioAction toOff(Target target) {
        return Tasks.instrumented(RadioAction.class, target, null, false);
    }

    public static RadioAction toOff(WebElement element) {
        return Tasks.instrumented(RadioAction.class, null, element, false);
    }
}
