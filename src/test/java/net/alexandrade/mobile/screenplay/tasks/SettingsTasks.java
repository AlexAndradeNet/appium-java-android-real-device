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

import static net.alexandrade.mobile.screenplay.ui.MainTileScreen.BUTTON_SETTINGS_TRANSACTION;
import static net.alexandrade.mobile.screenplay.ui.settings.SettingsScreen.BUTTON_TRANSACTION_OPTIONS;
import static net.alexandrade.mobile.screenplay.ui.settings.transactionoptions.TransactionOptionsScreen.BUTTON_TRANSACTION_FLOW;

import net.alexandrade.mobile.screenplay.interactions.Tap;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class SettingsTasks {

    private SettingsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    @Step("{0} opens the Transaction Flow settings {1}")
    public static Performable openTransactionFlowPage() {
        return Task.where(
                "{0} opens the Sale main tile",
                Tap.on(BUTTON_SETTINGS_TRANSACTION),
                Tap.on(BUTTON_TRANSACTION_OPTIONS),
                Tap.on(BUTTON_TRANSACTION_FLOW));
    }
}
