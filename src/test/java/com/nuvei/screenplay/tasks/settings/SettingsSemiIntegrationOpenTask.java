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
import com.nuvei.screenplay.tasks.commons.ScrollUntilVisibleTask;
import com.nuvei.screenplay.tasks.dashboard.DashboardSettingsTask;
import com.nuvei.screenplay.ui.settings.MainSettingsScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class SettingsSemiIntegrationOpenTask implements Task {

    @Override
    @Step("{0} opens the Settings > Semi-Integration screen")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                DashboardSettingsTask.open(),
                ScrollUntilVisibleTask.forObject(MainSettingsScreen.BUTTON_SEMI_INTEGRATION),
                ClickAction.on(MainSettingsScreen.BUTTON_SEMI_INTEGRATION));
    }

    public static SettingsSemiIntegrationOpenTask now() {
        return new SettingsSemiIntegrationOpenTask();
    }
}
