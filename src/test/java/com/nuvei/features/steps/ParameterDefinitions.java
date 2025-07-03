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

import com.nuvei.screenplay.ability.BrowseTheApp;
import com.nuvei.screenplay.tasks.dashboard.DashboardWaitUntilSpinnerDisappearsTask;
import io.cucumber.java.ParameterType;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.webdriver.WebDriverFacade;

/**
 * DO NOT DELETE THIS CLASS.
 *
 * <p>Converts the Cucumber placeholder <actor> into a Screenplay Actor and equips it with the
 * BrowseTheApp ability.
 *
 * <p>Example in a feature file: Given Aureliano is in the Main Screen
 */
public class ParameterDefinitions {

    /** Accept any of the “known” actor names written in feature files. */
    @ParameterType("Aureliano|Arcadio|Melquiades|Eusebia|Sebastian")
    public Actor actor(String actorName) {

        // 1. Obtain or create the Actor instance.
        Actor actor = OnStage.theActorCalled(actorName);

        // 2. Give the actor the ability to control the mobile app.
        WebDriverFacade driver = (WebDriverFacade) Serenity.getDriver();
        actor.can(BrowseTheApp.with(driver));

        // Wait for the app to be ready.
        actor.attemptsTo(DashboardWaitUntilSpinnerDisappearsTask.now());

        // 4. Return the fully configured actor to Cucumber.
        return actor;
    }
}
