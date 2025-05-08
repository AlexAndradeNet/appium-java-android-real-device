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

import com.nuvei.screenplay.interactions.NavigateBackAction;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.commons.AllTogglesTurnOffTask;
import com.nuvei.screenplay.tasks.settings.SettingsTransactionFlowOpenTask;
import com.nuvei.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class TransactionOptionsDeactivateAllTogglesTask implements Task {

    @Override
    @Step("{0} deactivates all toggles")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SettingsTransactionFlowOpenTask.now(),
                AllTogglesTurnOffTask.now(),
                NavigateBackAction.clickingBackArrow(),
                TransactionOptionsSplitPaymentOpenTask.open(),
                AllTogglesTurnOffTask.now(),
                NavigateBackAction.clickingBackArrow());

        boolean isTippingAvailable =
                actor.asksFor(
                        VisibilityQuestion.isPresent(
                                MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));

        if (isTippingAvailable) {
            // Tipping could be optional in some cases like when Crypto is enabled
            actor.attemptsTo(
                    TransactionOptionTippingTask.open(),
                    AllTogglesTurnOffTask.now(),
                    NavigateBackAction.clickingBackArrow());
        }
    }

    public static Performable now() {
        return new TransactionOptionsDeactivateAllTogglesTask();
    }
}
