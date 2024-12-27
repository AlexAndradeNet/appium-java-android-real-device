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

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import net.alexandrade.mobile.screenplay.tasks.MainTileScreenTasks;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

public class Hooks {

    @BeforeAll
    public static void beforeAll() {
        OnStage.setTheStage(new OnlineCast());
        System.out.println("####################### BEFORE ALL Cucumber");
    }

    @Before(order = 1)
    public void beforeEach() {
        System.out.println(
                "####################### Cucumber Execution Order: "
                        + System.getProperty("cucumber.execution.order"));
    }

    @After(order = 1)
    public void afterEachScenario() {
        OnStage.theActorInTheSpotlight().attemptsTo(MainTileScreenTasks.returnToMainScreen());
    }

    @AfterAll
    public static void afterAll() {
        OnStage.drawTheCurtain();
        System.out.println("####################### AFTER ALL Cucumber");
    }
}
