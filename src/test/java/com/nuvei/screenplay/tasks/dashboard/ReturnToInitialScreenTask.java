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
package com.nuvei.screenplay.tasks.dashboard;

import com.nuvei.screenplay.interactions.SwipeAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.DashboardScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ReturnToInitialScreenTask implements Task {

    @Override
    @Step("Return to the initial screen")
    public <T extends Actor> void performAs(T actor) {
        while (Boolean.TRUE.equals(
                actor.asksFor(VisibilityQuestion.isPresent(DashboardScreen.BUTTON_PREVIOUS)))) {
            actor.attemptsTo(SwipeAction.overTargetToRight(DashboardScreen.FRAME_DASHBOARD));
        }
    }

    public static ReturnToInitialScreenTask now() {
        return new ReturnToInitialScreenTask();
    }
}
