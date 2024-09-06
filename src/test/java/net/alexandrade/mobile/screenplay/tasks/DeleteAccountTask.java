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

import net.alexandrade.mobile.screenplay.tasks.factory.GoTo;
import net.alexandrade.mobile.screenplay.ui.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.annotations.Step;

public class DeleteAccountTask implements Task {

    @Step("{0} delete the slected account")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                GoTo.accountProfile(),
                WaitUntil.the(AccountPage.OPTIONS_BUTTON, isCurrentlyEnabled())
                        .forNoMoreThan(30)
                        .seconds(),
                Click.on(AccountPage.OPTIONS_BUTTON),
                WaitUntil.the(AccountPage.DELETE_BUTTON, isCurrentlyEnabled())
                        .forNoMoreThan(30)
                        .seconds(),
                Click.on(AccountPage.DELETE_BUTTON),
                WaitUntil.the(AccountPage.DELETE_ACCOUNT_POPUP, isCurrentlyEnabled())
                        .forNoMoreThan(30)
                        .seconds(),
                Click.on(AccountPage.YES_BUTTON));
    }

    public static DeleteAccountTask currently() {
        return instrumented(DeleteAccountTask.class);
    }
}
