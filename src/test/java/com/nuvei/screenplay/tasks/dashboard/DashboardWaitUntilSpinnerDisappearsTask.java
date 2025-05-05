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

import static com.nuvei.screenplay.ui.DashboardScreen.BUTTON_SALE_TRANSACTION;
import static com.nuvei.screenplay.ui.DashboardScreen.SPINNER;

import com.nuvei.screenplay.interactions.WaitAction;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DashboardWaitUntilSpinnerDisappearsTask implements Task {

    @Override
    @Step("Wait until the spinner disappears")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitAction.untilElementIsNotPresent(SPINNER),
                WaitAction.untilElementIsPresent(BUTTON_SALE_TRANSACTION));
    }

    public static DashboardWaitUntilSpinnerDisappearsTask now() {
        return new DashboardWaitUntilSpinnerDisappearsTask();
    }
}
