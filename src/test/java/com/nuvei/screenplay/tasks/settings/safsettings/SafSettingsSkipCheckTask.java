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
package com.nuvei.screenplay.tasks.settings.safsettings;

import com.nuvei.screenplay.interactions.SkipScenarioAction;
import com.nuvei.screenplay.questions.EnvironmentQuestion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class SafSettingsSkipCheckTask implements Task {

    @Override
    @Step("{0} skips SAF check if not on T-Series device")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(EnvironmentQuestion.isTSeries())) {
            actor.attemptsTo(
                    SkipScenarioAction.withReason("SAF is not available in P and M-Series"));
        }
    }

    public static SafSettingsSkipCheckTask verifyTSeries() {
        return new SafSettingsSkipCheckTask();
    }
}
