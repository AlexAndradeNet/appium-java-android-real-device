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

import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.ui.settings.SemiIntegrationOptionsScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class SemiIntegrationTasks {
    private SemiIntegrationTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable turnOffSemiIntegration(Actor actor) {
        actor.attemptsTo(
                MainSettingsTasks.openSemiIntegrationOptionsScreen(actor),
                ToggleAction.toOff(SemiIntegrationOptionsScreen.TOGGLE_ENABLE_SEMI_INTEGRATION));
        return Task.where("{0} turns off Semi Integration");
    }
}
