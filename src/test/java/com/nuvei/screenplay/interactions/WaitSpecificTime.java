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

    private static final SimpleLogger logger = new SimpleLogger(Hooks.class);

    private final long seconds;

    protected WaitSpecificTime(long milliseconds) {
        this.seconds = milliseconds;
    }

    public static Performable forSeconds(long seconds) {
        return Tasks.instrumented(WaitSpecificTime.class, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var currentInstant = Instant.now(); // Instant is generally preferred for machine time

        logger.debug("Waiting for " + seconds + " seconds");
        logger.debug("Starting at: " + currentInstant);

        var duration = Duration.ofSeconds(seconds);
        long millis = duration.toMillis(); // Convert to milliseconds

        try {
            Thread.sleep(millis); // Use Thread.sleep() for precise waits
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        currentInstant = Instant.now();
        logger.debug("Finishing at: " + currentInstant);
    }
}
