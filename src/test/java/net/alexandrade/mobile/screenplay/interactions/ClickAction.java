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
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class ClickAction implements Interaction {
    private enum ClickType {
        REGULAR_CLICK,
        SCROLL_AND_CLICK,
        WAIT_AND_CLICK,
        LEGACY_CLICK
    }

    private final Target target;
    private final ClickType clickType;

    protected ClickAction(Target target, ClickType clickType) {
        this.target = target;
        this.clickType = clickType;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        switch (clickType) {
            case LEGACY_CLICK:
                target.resolveFor(actor).click();
                break;
            case SCROLL_AND_CLICK:
                Scroll.to(target).performAs(actor);
                ClickAction.on(target).performAs(actor);
                break;
            case WAIT_AND_CLICK:
                waitUntilIsClickable(actor, target);
                ClickAction.on(target).performAs(actor);
                break;
            default:
                ClickAction.on(target).performAs(actor);
                break;
        }
    }

    private void waitUntilIsClickable(Actor actor, Target target) {
        actor.attemptsTo(WaitUntil.the(target, isClickable()).forNoMoreThan(30).seconds());
    }

    public static ClickAction on(Target target) {
        return instrumented(ClickAction.class, target, ClickType.REGULAR_CLICK);
    }

    public static ClickAction usingLegacyMethodOn(Target target) {
        return instrumented(ClickAction.class, target, ClickType.LEGACY_CLICK);
    }

    public static ClickAction afterScrollTo(Target target) {
        return instrumented(ClickAction.class, target, ClickType.SCROLL_AND_CLICK);
    }

    public static ClickAction afterWaitIsClickable(Target target) {
        return instrumented(ClickAction.class, target, ClickType.WAIT_AND_CLICK);
    }
}
