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

import com.nuvei.screenplay.tasks.commons.AllTogglesTurnOffTask;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class TippingOptionsTask implements Task {

    @Override
    @Step("{0} turns off all toggles on the screen")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(AllTogglesTurnOffTask.now());
    }

    public static Performable turnOffAllTogglesOnTheScreen() {
        return new TippingOptionsTask();
    }
}
