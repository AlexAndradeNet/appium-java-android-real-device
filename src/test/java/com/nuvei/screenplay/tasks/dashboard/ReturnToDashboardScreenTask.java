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

import static com.nuvei.screenplay.ui.CommonObjects.BUTTON_ARROW_BACK;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.openqa.selenium.NoSuchElementException;

public class ReturnToDashboardScreenTask implements Task {

    @Override
    @Step("{0} navigates back to the main screen")
    public <T extends Actor> void performAs(T actor) {
        try {
            while (Boolean.TRUE.equals(
                    actor.asksFor(VisibilityQuestion.isPresent(BUTTON_ARROW_BACK)))) {
                actor.attemptsTo(ClickAction.on(BUTTON_ARROW_BACK));
            }
        } catch (NoSuchElementException ignored) {
            // Do nothing
        }
    }

    public static Performable now() {
        return new ReturnToDashboardScreenTask();
    }
}
