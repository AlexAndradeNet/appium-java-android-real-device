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

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyEnabled;

import net.alexandrade.mobile.screenplay.ui.LeftMenuBarPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class AccountProfileTask implements Task {

    @Step("{0} shows the menu panel")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ShowMenuPanelTask.openMenuPanel(),
                WaitUntil.the(LeftMenuBarPage.MANAGE_ACCOUNTS_PANEL, isCurrentlyEnabled())
                        .forNoMoreThan(30)
                        .seconds(),
                Click.on(LeftMenuBarPage.MANAGE_ACCOUNTS_PANEL));
    }
}
