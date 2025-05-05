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
package com.nuvei.screenplay.tasks.dashboard;

import static com.nuvei.screenplay.ui.DashboardScreen.BUTTON_HELP_DESK;
import static com.nuvei.screenplay.ui.common.NumericScreen.LABEL_REASON;
import static com.nuvei.screenplay.ui.common.NumericScreen.TITLE;

import com.nuvei.screenplay.interactions.NavigateUntilVisibleAndClick;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class DashboardHelpDeskTask implements Task {

    private final boolean withVerification;

    private DashboardHelpDeskTask(boolean withVerification) {
        this.withVerification = withVerification;
    }

    @Override
    @Step("Open the Help Desk screen")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(NavigateUntilVisibleAndClick.on(BUTTON_HELP_DESK));

        if (withVerification) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the Help Desk title",
                                    VisibilityQuestion.isPresent(TITLE.of("HELP")))
                            .isTrue(),
                    Ensure.that(
                                    "Should see the Help Desk subtitle",
                                    VisibilityQuestion.isPresent(
                                            LABEL_REASON.of("Enter Super Password")))
                            .isTrue());
        }
    }

    public static DashboardHelpDeskTask withVerification() {
        return new DashboardHelpDeskTask(true);
    }

    public static DashboardHelpDeskTask withoutVerification() {
        return new DashboardHelpDeskTask(false);
    }
}
