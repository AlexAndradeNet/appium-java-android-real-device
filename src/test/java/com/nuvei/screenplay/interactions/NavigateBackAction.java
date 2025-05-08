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
import com.nuvei.screenplay.ui.CommonObjects;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class NavigateBackAction implements Interaction {

    private enum Method {
        BACK_ARROW,
        PREVIOUS_BUTTON
    }

    private final Method method;

    public NavigateBackAction(Method method) {
        this.method = method;
    }

    @Override
    @Step("{0} navigates until the element is visible and clicks on it")
    public <T extends Actor> void performAs(T actor) {
        if (method == Method.BACK_ARROW) {
            actor.attemptsTo(ClickAction.on(CommonObjects.BUTTON_ARROW_BACK));
        } else if (method == Method.PREVIOUS_BUTTON) {
            actor.usingAbilityTo(BrowseTheApp.class).driver().navigate().back();
        }
    }

    public static NavigateBackAction clickingBackArrow() {
        return new NavigateBackAction(Method.BACK_ARROW);
    }

    public static NavigateBackAction pressingPhysicalBackKey() {
        return new NavigateBackAction(Method.PREVIOUS_BUTTON);
    }
}
