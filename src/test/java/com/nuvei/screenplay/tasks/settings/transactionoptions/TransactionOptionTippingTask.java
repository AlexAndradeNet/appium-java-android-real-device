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
package com.nuvei.screenplay.tasks.settings.transactionoptions;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class TransactionOptionTippingTask implements Task {

    @Override
    @Step("{0} opens the Tipping Options screen")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickAction.on(MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));
    }

    public static TransactionOptionTippingTask open() {
        return new TransactionOptionTippingTask();
    }
}
