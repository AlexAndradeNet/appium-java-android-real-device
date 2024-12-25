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
package net.alexandrade.mobile.features.steps;

import io.cucumber.java.ParameterType;
import net.alexandrade.mobile.screenplay.tasks.MainTileScreenTasks;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;

public class ParameterDefinitions {

    @ParameterType("Aureliano|Melquiades|Eusebia|Sebastian")
    public Actor actor(String actor) {
        Actor theActor = OnStage.theActorCalled(actor);
        theActor.can(BrowseTheWeb.with(Serenity.getDriver()));
        theActor.attemptsTo(MainTileScreenTasks.waitTheAppIsFullyLoaded());
        return OnStage.theActorCalled(actor);
    }
}
