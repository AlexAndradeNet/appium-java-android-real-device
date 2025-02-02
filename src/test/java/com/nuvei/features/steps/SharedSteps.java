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
package com.nuvei.features.steps;

import com.nuvei.screenplay.tasks.MainTileScreenTasks;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.Actor;

public class SharedSteps {

    @Given("{actor} is in the Main Screen")
    public void theActorIsInTheHomePage(Actor actor) {
        actor.attemptsTo(MainTileScreenTasks.waitTheAppIsFullyLoaded(actor));
    }
}
