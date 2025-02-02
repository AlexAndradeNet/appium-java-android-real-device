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
package com.nuvei.screenplay.interactions;

import com.nuvei.features.steps.Hooks;
import com.nuvei.utils.SimpleLogger;
import java.time.Duration;
import java.time.Instant;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class WaitSpecificTime implements Task {

    private final long seconds;

    protected WaitSpecificTime(long milliseconds) {
        this.seconds = milliseconds;
    }

    public static Performable forSeconds(long seconds) {
        return Tasks.instrumented(WaitSpecificTime.class, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var duration = java.time.Duration.ofSeconds(seconds);
        await().atMost(duration).until(() -> true);
    }
}
