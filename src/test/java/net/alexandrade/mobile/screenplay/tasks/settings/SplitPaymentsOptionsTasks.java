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
package net.alexandrade.mobile.screenplay.tasks.settings;

import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;

public class SplitPaymentsOptionsTasks {
    private SplitPaymentsOptionsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable turnOffAllTogglesOnTheScreen(Actor actor) {
        return CommonTasks.turnOffAllTogglesOnTheScreen(actor);
    }
}
