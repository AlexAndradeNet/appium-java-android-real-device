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
package com.nuvei.screenplay.tasks.commons;

import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.settings.MainSettingsTasks;
import com.nuvei.screenplay.tasks.settings.TransactionsOptionsTasks;
import com.nuvei.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class TogglesTasks {

    private TogglesTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable deactivateAllToggles(Actor actor) {
        actor.attemptsTo(
                MainSettingsTasks.openTransactionFlowScreen(actor),
                CommonTasks.turnOffAllTogglesOnTheScreen(actor),
                CommonTasks.tapBackArrow(actor),
                TransactionsOptionsTasks.openSplitPayment(actor),
                CommonTasks.turnOffAllTogglesOnTheScreen(actor),
                CommonTasks.tapBackArrow(actor));

        boolean isTippingAvailable =
                actor.asksFor(
                        VisibilityQuestion.isPresent(
                                MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));

        if (isTippingAvailable) {
            // Tipping could be optional in some cases like when Crypto is enabled
            actor.attemptsTo(
                    TransactionsOptionsTasks.openTipping(actor),
                    CommonTasks.turnOffAllTogglesOnTheScreen(actor),
                    CommonTasks.tapBackArrow(actor));
        }
        return Task.where("{0} fills the clerk ID and password");
    }
}
