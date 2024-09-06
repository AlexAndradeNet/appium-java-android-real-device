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
package net.alexandrade.mobile.screenplay.tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyEnabled;

import net.alexandrade.mobile.screenplay.ui.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class AccessTask implements Task {
    String label = "";

    public AccessTask(String label) {
        this.label = label;
    }

    @Step("{0} navigates to #label ")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DashboardPage.getMenuByLabel(label), isCurrentlyEnabled())
                        .forNoMoreThan(80)
                        .seconds(),
                Click.on(DashboardPage.getMenuByLabel(label)));
    }

    public static AccessTask menuWithLabel(String label) {
        return instrumented(AccessTask.class, label);
    }
}
