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

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Tasks;
import org.junit.jupiter.api.Assumptions;

public class SkipScenarioAction implements Interaction {
    private final String cause;

    protected SkipScenarioAction(String cause) {
        this.cause = cause;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Assumptions.abort(cause);
    }

    public static Performable byCause(String cause) {
        return Tasks.instrumented(SkipScenarioAction.class, cause);
    }
}
