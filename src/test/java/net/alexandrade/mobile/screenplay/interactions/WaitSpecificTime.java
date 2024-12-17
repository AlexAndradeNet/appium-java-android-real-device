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
package net.alexandrade.mobile.screenplay.interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class WaitSpecificTime implements Task {

    private final long seconds;

    protected WaitSpecificTime(long milliseconds) {
        this.seconds = milliseconds;
    }

    public static Performable forSeconds(long seconds) {
        return instrumented(WaitSpecificTime.class, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        final long NUMBER_OF_MILLISECONDS_IN_A_SECOND = 1000;
        final long NUMBER_OF_MILLISECONDS_TO_WAIT = seconds * NUMBER_OF_MILLISECONDS_IN_A_SECOND;

        try {
            Thread.sleep(NUMBER_OF_MILLISECONDS_TO_WAIT);
        } catch (InterruptedException ignored) {
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }
}
