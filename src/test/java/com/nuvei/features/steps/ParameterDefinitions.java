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
import io.cucumber.java.ParameterType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;

// Do not delete this file
public class ParameterDefinitions {

    @ParameterType("Aureliano|Arcadio|Melquiades|Eusebia|Sebastian")
    public Actor actor(String actorName) {
        Actor actor = OnStage.theActorCalled(actorName);
        actor.can(BrowseTheWeb.with(Serenity.getDriver()));
        actor.attemptsTo(MainTileScreenTasks.waitTheAppIsFullyLoaded(actor));
        return actor;
    }
}
