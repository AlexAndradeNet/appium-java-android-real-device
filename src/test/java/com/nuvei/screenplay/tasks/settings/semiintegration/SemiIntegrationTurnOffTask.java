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
package com.nuvei.screenplay.tasks.settings.semiintegration;

import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.tasks.settings.SettingsSemiIntegrationOpenTask;
import com.nuvei.screenplay.ui.settings.SemiIntegrationOptionsScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class SemiIntegrationTurnOffTask implements Task {

    @Override
    @Step("{0} turns off Semi Integration")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SettingsSemiIntegrationOpenTask.now(),
                ToggleAction.toOff(SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION));
    }

    public static Performable turnOffSemiIntegration() {
        return new SemiIntegrationTurnOffTask();
    }
}
