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

import io.cucumber.java.PendingException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;

public class SkipScenarioAction implements Interaction {
    private final String cause;

    protected SkipScenarioAction(String cause) {
        this.cause = cause;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        throw new PendingException(cause);
    }

    public static Performable withReason(String reason) {
        return Tasks.instrumented(SkipScenarioAction.class, reason);
    }
}
