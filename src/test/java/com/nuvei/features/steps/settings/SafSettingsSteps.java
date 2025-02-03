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
package com.nuvei.features.steps.settings;

import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.tasks.settings.SafSettingsTasks;
import com.nuvei.screenplay.ui.MainTileScreen;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class SafSettingsSteps {
    @When("he enables the SAF mode with ID {word} and Password {word}")
    public void heEnablesTheSAFMode(String clerkId, String password) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(SafSettingsTasks.turnOnSaf(actor, clerkId, password));
    }

    @Then("he should see that SAF was enabled")
    public void heShouldSeeThatSAFWasEnabled() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                Ensure.that(
                                "SAF is enabled",
                                VisibilityQuestion.isPresent(MainTileScreen.LABEL_SAF))
                        .isTrue());
    }

    @When("he disables the SAF mode with ID {word} and Password {word}")
    public void heDisablesTheSAFModeWithIDAndPassword(String clerkId, String password) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(SafSettingsTasks.turnOffSaf(actor, clerkId, password));
    }

    @Then("he should see that SAF was disabled")
    public void heShouldSeeThatSAFWasDisabled() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                Ensure.that(
                                "SAF is disabled",
                                VisibilityQuestion.isPresent(MainTileScreen.LABEL_SAF))
                        .isFalse());
    }
}
