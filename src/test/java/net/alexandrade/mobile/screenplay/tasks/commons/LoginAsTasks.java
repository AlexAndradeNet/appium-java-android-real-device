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
package net.alexandrade.mobile.screenplay.tasks.commons;

import net.alexandrade.mobile.screenplay.interactions.NumpadAction;
import net.alexandrade.mobile.screenplay.interactions.TapAction;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class LoginAsTasks {

    private LoginAsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    @Step("{0} fills the clerk ID '{1}'")
    public static Performable fillClerkID(boolean withValidation, String clerkId) {
        return Task.where(
                "{0} fills the clerk ID and password",
                actor -> {
                    if (withValidation) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the title",
                                                VisibilityQuestion.isPresent(
                                                        NumericScreen.TITLE.of("CLERK MANAGEMENT")))
                                        .isTrue(),
                                Ensure.that(
                                                "Should see the label",
                                                VisibilityQuestion.isPresent(
                                                        NumericScreen.LABEL_REASON.of(
                                                                "Enter your Clerk ID")))
                                        .isTrue());
                    }
                    actor.attemptsTo(
                            NumpadAction.digit(clerkId),
                            TapAction.on(NumericScreen.BUTTON_CONTINUE));
                });
    }

    @Step("{0} fills the clerk ID '{1}'")
    public static Performable fillClerkID(String clerkId) {
        return fillClerkID(false, clerkId);
    }

    @Step("{0} fills the password '{1}'")
    public static Performable fillPassword(boolean withValidation, String clerkPassword) {
        return Task.where(
                "{0} fills the clerk ID and password",
                actor -> {
                    if (withValidation) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the title",
                                                VisibilityQuestion.isPresent(
                                                        NumericScreen.TITLE.of("CLERK MANAGEMENT")))
                                        .isTrue(),
                                Ensure.that(
                                                "Should see the label",
                                                VisibilityQuestion.isPresent(
                                                        NumericScreen.LABEL_REASON.of(
                                                                "Enter your Password")))
                                        .isTrue());
                    }
                    actor.attemptsTo(
                            NumpadAction.digit(clerkPassword),
                            TapAction.on(NumericScreen.BUTTON_CONTINUE));
                });
    }

    @Step("{0} fills the Password '{1}'")
    public static Performable fillPassword(String clerkPassword) {
        return fillPassword(false, clerkPassword);
    }
}
