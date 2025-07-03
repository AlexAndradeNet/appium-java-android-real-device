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
package com.nuvei.screenplay.tasks.commons;

import com.nuvei.screenplay.questions.IsSafAvailableInThisTerminal;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import org.junit.jupiter.api.Assumptions;

public class CheckSafAvailability implements Task {

    public static CheckSafAvailability orSkip() {
        return new CheckSafAvailability();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        boolean isSafAvailable =
                Boolean.TRUE.equals(actor.asksFor(IsSafAvailableInThisTerminal.now()));

        Assumptions.assumeTrue(isSafAvailable, "SAF is not available in P and M-Series");
    }
}
