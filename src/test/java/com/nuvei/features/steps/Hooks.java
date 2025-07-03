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

import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.tasks.dashboard.ReturnToDashboardScreenTask;
import com.nuvei.screenplay.tasks.settings.SettingsTransactionFlowOpenTask;
import com.nuvei.screenplay.ui.CommonObjects;
import com.nuvei.utils.DotenvReader;
import com.nuvei.utils.LoggerWrapper;
import com.nuvei.utils.SerenityReportHelper;
import io.cucumber.java.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.junit.platform.commons.util.StringUtils;

public class Hooks {

    private static final LoggerWrapper logger = new LoggerWrapper(Hooks.class);

    @BeforeAll
    public static void beforeAll() {
        OnStage.setTheStage(new OnlineCast());
        DotenvReader.loadEnvFile();
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
    public void afterEachScenario(Scenario scenario) {
        Actor actor = OnStage.theActorInTheSpotlight();

        if (scenario.isFailed()) {
            SerenityReportHelper.saveScreenshot(scenario);

            String toggleName = actor.recall("toggleName");
            if (StringUtils.isNotBlank(toggleName)) {
                actor.attemptsTo(
                        SettingsTransactionFlowOpenTask.now(),
                        ToggleAction.toOff(CommonObjects.TOGGLE.of(toggleName)));
            }
        }

        actor.attemptsTo(ReturnToDashboardScreenTask.now(), WaitAction.forSpecificTime(1));
    }

    @AfterAll
    public static void afterAll() {
        logger.info("####################### AFTER ALL Cucumber");
    }
}
