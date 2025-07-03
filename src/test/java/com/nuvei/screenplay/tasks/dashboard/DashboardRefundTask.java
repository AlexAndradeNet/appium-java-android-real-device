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
import com.nuvei.screenplay.questions.VisibilityQuestion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.junit.jupiter.api.Assumptions;

public class DashboardRefundTask implements Task {

    @Override
    @Step("Open the Refund Transaction screen")
    public <T extends Actor> void performAs(T actor) {
        boolean isRefundAvailable =
                Boolean.TRUE.equals(
                        actor.asksFor(VisibilityQuestion.isPresent(BUTTON_REFUND_TRANSACTION)));

        Assumptions.assumeTrue(
                isRefundAvailable,
                "Refund is not available in this terminal. Crypto is enabled, so refund is not"
                        + " available.");

        // Continue with the action if refund is available
        actor.attemptsTo(NavigateUntilVisibleAndClickAction.on(BUTTON_REFUND_TRANSACTION));
    }

    public static DashboardRefundTask open() {
        return new DashboardRefundTask();
    }
}
