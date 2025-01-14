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
package com.nuvei.screenplay.tasks.settings;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.tasks.MainTileScreenTasks;
import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.screenplay.ui.settings.MainSettingsScreen;
import com.nuvei.screenplay.ui.settings.transactionoptions.MainTransactionOptionsScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class MainSettingsTasks {

    private MainSettingsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable openTransactionFlowScreen(Actor actor) {
        actor.attemptsTo(
                MainTileScreenTasks.openSettings(actor),
                ClickAction.on(MainSettingsScreen.BUTTON_TRANSACTION_OPTIONS),
                ClickAction.on(MainTransactionOptionsScreen.BUTTON_TRANSACTION_FLOW));

        return Task.where("{0} opens the Transaction Options > Transaction Flow screen");
    }

    public static Performable openTipsOptionsScreen(Actor actor) {
        actor.attemptsTo(
                MainTileScreenTasks.openSettings(actor),
                ClickAction.on(MainSettingsScreen.BUTTON_TRANSACTION_OPTIONS),
                ClickAction.on(MainTransactionOptionsScreen.BUTTON_TIPPING_OPTIONS));

        return Task.where("{0} opens the Transaction Options > Transaction Flow screen");
    }

    public static Performable openClerkManagementScreen(Actor actor) {
        actor.attemptsTo(
                MainTileScreenTasks.openSettings(actor),
                ClickAction.on(MainSettingsScreen.BUTTON_CLERK_MANAGEMENT));

        return Task.where("{0} opens the Sale main tile");
    }

    public static Performable openSemiIntegrationOptionsScreen(Actor actor) {
        actor.attemptsTo(
                MainTileScreenTasks.openSettings(actor),
                CommonTasks.navigateMenuUntilElementIsVisible(
                        actor, MainSettingsScreen.BUTTON_SEMI_INTEGRATION),
                ClickAction.on(MainSettingsScreen.BUTTON_SEMI_INTEGRATION));

        return Task.where("{0} opens the Sale main tile");
    }
}
