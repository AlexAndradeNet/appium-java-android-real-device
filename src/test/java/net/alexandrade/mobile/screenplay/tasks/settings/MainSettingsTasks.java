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
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.ui.MainTileScreen;
import net.alexandrade.mobile.screenplay.ui.settings.MainSettingsScreen;
import net.alexandrade.mobile.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class MainSettingsTasks {

    private MainSettingsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable openTransactionFlowScreen() {
        return Task.where(
                "{0} opens the Transaction Options > Transaction Flow screen",
                ClickAction.on(MainTileScreen.BUTTON_SETTINGS_TRANSACTION),
                ClickAction.on(MainSettingsScreen.BUTTON_TRANSACTION_OPTIONS),
                ClickAction.on(MainTransactionOptionsScreen.BUTTON_TRANSACTION_FLOW));
    }

    public static Performable openTipsOptionsScreen() {
        return Task.where(
                "{0} opens the Transaction Options > Transaction Flow screen",
                ClickAction.on(MainTileScreen.BUTTON_SETTINGS_TRANSACTION),
                ClickAction.on(MainSettingsScreen.BUTTON_TRANSACTION_OPTIONS),
                ClickAction.on(MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));
    }

    public static Performable openClerkManagementScreen() {
        return Task.where(
                "{0} opens the Sale main tile",
                ClickAction.on(MainTileScreen.BUTTON_SETTINGS_TRANSACTION),
                ClickAction.on(MainSettingsScreen.BUTTON_CLERK_MANAGEMENT));
    }

    public static Performable openSemiIntegrationOptionsScreen() {
        return Task.where(
                "{0} opens the Sale main tile",
                ClickAction.on(MainTileScreen.BUTTON_SETTINGS_TRANSACTION),
                CommonTasks.navigateMenuUntilElementIsVisibleAndTapOnIt(
                        MainSettingsScreen.BUTTON_SEMI_INTEGRATION));
    }
}
