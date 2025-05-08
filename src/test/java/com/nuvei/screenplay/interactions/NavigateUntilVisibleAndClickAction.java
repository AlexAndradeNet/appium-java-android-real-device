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

import static net.serenitybdd.screenplay.Tasks.instrumented;

import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.DashboardScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

public class NavigateUntilVisibleAndClickAction implements Interaction {

    private final Target target;

    public NavigateUntilVisibleAndClickAction(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} navigates until the element is visible and clicks on it")
    public <T extends Actor> void performAs(T actor) {
        while (actor.asksFor(VisibilityQuestion.notPresent(target))
                && actor.asksFor(VisibilityQuestion.isPresent(DashboardScreen.BUTTON_NEXT))) {
            actor.attemptsTo(SwipeAction.toLeft());
        }
        actor.attemptsTo(ClickAction.on(target));
    }

    public static NavigateUntilVisibleAndClickAction on(Target target) {
        return instrumented(NavigateUntilVisibleAndClickAction.class, target);
    }
}
