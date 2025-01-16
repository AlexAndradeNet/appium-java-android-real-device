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

import com.nuvei.screenplay.tasks.commons.CommonTasks;
import com.nuvei.utils.SimpleLogger;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hooks {

    private static final SimpleLogger logger = new SimpleLogger(Hooks.class);

    @BeforeAll
    public static void beforeAll() {
        OnStage.setTheStage(new OnlineCast());
        logger.info("####################### BEFORE ALL Cucumber");
    }

    @Before(order = 1)
    public void beforeEachScenario() {
        logger.info(
                "####################### Cucumber Execution Order: "
                        + System.getProperty("cucumber.execution.order"));
        OnStage.drawTheCurtain(); // Clears all actors and contexts
        OnStage.setTheStage(new OnlineCast()); // Re-initialize actors
    }

    @After(order = 1)
    public void afterEachScenario() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(CommonTasks.returnToMainScreen(actor));
    }

    @AfterAll
    public static void afterAll() {
        logger.info("####################### AFTER ALL Cucumber");
    }
}
