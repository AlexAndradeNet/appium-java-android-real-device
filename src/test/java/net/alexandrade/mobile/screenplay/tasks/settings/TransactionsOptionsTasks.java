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
package net.alexandrade.mobile.screenplay.tasks.settings;

import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class TransactionsOptionsTasks {
    private TransactionsOptionsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable openSplitPayment(Actor actor) {
        actor.attemptsTo(ClickAction.on(MainTransactionOptionsScreen.BUTTON_SPLIT_PAYMENT));

        return Task.where("{0} opens the Split Payment screen");
    }

    public static Performable openTipping(Actor actor) {
        actor.attemptsTo(ClickAction.on(MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));

        return Task.where("{0} opens the Tipping Options screen");
    }
}
