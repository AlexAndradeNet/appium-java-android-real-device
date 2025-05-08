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

import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.common.NumericScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.platform.commons.util.StringUtils;

public class LoginFillPasswordTask implements Task {

    private final String screenTitle;
    private final String clerkPassword;

    public LoginFillPasswordTask(String screenTitle, String clerkPassword) {
        this.screenTitle = screenTitle;
        this.clerkPassword = clerkPassword;
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
                                    "Should see the label: 'Enter your Password'",
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.LABEL_REASON.of("Enter your Password")))
                            .isTrue());
        }

        actor.attemptsTo(NumpadTask.digitAndConfirm(clerkPassword));
    }

    public static Performable withDetails(String clerkPassword) {
        return new LoginFillPasswordTask(null, clerkPassword);
    }

    public static Performable withDetails(String screenTitle, String clerkPassword) {
        return new LoginFillPasswordTask(screenTitle, clerkPassword);
    }
}
