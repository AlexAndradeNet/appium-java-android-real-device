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
package com.nuvei.screenplay.tasks.commons;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.common.NumericScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.platform.commons.util.StringUtils;

public class LoginFillClerkIDTask implements Task {
    private final String screenTitle;
    private final String clerkID;

    public LoginFillClerkIDTask(String screenTitle, String clerkID) {
        this.screenTitle = screenTitle;
        this.clerkID = clerkID;
    }

    @Override
    @Step("{0} fills the clerk ID and password for #functionality")
    public <T extends Actor> void performAs(T actor) {
        boolean withValidation = StringUtils.isNotBlank(screenTitle);

        if (withValidation) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the title: '%s'".formatted(screenTitle),
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.TITLE.of(screenTitle)))
                            .isTrue(),
                    Ensure.that(
                                    "Should see the label: 'Enter your Clerk ID'",
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.LABEL_REASON.of("Enter your Clerk ID")))
                            .isTrue());
        }
        actor.attemptsTo(NumpadTask.digit(clerkID));

        if (withValidation) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the value of the Clerk ID field: '%s'"
                                            .formatted(clerkID),
                                    TextQuestion.of(NumericScreen.TEXTBOX_VALUE))
                            .isEqualTo(clerkID));
        }

        actor.attemptsTo(ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));
    }

    public static Performable withDetails(String screenTitle, String clerkID) {
        return new LoginFillClerkIDTask(screenTitle, clerkID);
    }

    public static Performable withDetails(String clerkID) {
        return new LoginFillClerkIDTask(null, clerkID);
    }
}
