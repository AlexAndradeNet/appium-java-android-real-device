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

import static com.nuvei.screenplay.ui.DashboardScreen.BUTTON_VOID_TRANSACTION;

import com.nuvei.screenplay.interactions.NavigateUntilVisibleAndClick;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class DashboardVoidTask implements Task {

    @Override
    @Step("Open the Void Transaction screen")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(NavigateUntilVisibleAndClick.on(BUTTON_VOID_TRANSACTION));
    }

    public static DashboardVoidTask open() {
        return new DashboardVoidTask();
    }
}
