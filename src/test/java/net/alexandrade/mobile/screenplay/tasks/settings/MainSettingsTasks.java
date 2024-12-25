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

import net.alexandrade.mobile.screenplay.interactions.Tap;
import net.alexandrade.mobile.screenplay.ui.MainTileScreen;
import net.alexandrade.mobile.screenplay.ui.settings.MainSettingsScreen;
import net.alexandrade.mobile.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class MainSettingsTasks {

    private MainSettingsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    @Step("{0} opens the Transaction Flow settings {1}")
    public static Performable openTransactionFlowScreen() {
        return Task.where(
                "{0} opens the Transaction Options > Transaction Flow screen",
                Tap.on(MainTileScreen.BUTTON_SETTINGS_TRANSACTION),
                Tap.on(MainSettingsScreen.BUTTON_TRANSACTION_OPTIONS),
                Tap.on(MainTransactionOptionsScreen.BUTTON_TRANSACTION_FLOW));
    }

    @Step("{0} opens the Clerk Management Settings {1}")
    public static Performable openClerkManagementScreen() {
        return Task.where(
                "{0} opens the Sale main tile",
                Tap.on(MainTileScreen.BUTTON_SETTINGS_TRANSACTION),
                Tap.on(MainSettingsScreen.BUTTON_CLERK_MANAGEMENT));
    }
}
