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

import static com.nuvei.screenplay.ui.DashboardScreen.BUTTON_REFUND_TRANSACTION;

import com.nuvei.screenplay.interactions.NavigateUntilVisibleAndClickAction;
import com.nuvei.screenplay.interactions.SkipScenarioAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DashboardRefundTask implements Task {

    @Override
    @Step("Open the Refund Transaction screen")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                VisibilityQuestion.isPresent(BUTTON_REFUND_TRANSACTION).answeredBy(actor)
                        ? NavigateUntilVisibleAndClickAction.on(BUTTON_REFUND_TRANSACTION)
                        : SkipScenarioAction.withReason(
                                "Refund is not available when Crypto is enabled"));
    }

    public static DashboardRefundTask open() {
        return new DashboardRefundTask();
    }
}
