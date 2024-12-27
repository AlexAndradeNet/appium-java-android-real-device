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

    @Step("{0} fills the clerk ID '{1}' and password '{2}'")
    public static Performable clerk(String clerkId, String clerkPassword) {
        return Task.where(
                "{0} fills the clerk ID and password",
                actor -> {
                    // Tap on each digit of the clerk ID
                    actor.attemptsTo(
                            Ensure.that(
                                            VisibilityQuestion.isPresent(
                                                    NumericScreen.TITLE.of("CLERK MANAGEMENT")))
                                    .isTrue(),
                            Ensure.that(
                                            VisibilityQuestion.isPresent(
                                                    NumericScreen.LABEL_REASON.of(
                                                            "Enter your Clerk ID")))
                                    .isTrue(),
                            NumpadAction.onNuveisNumpad(clerkId),
                            TapAction.on(NumericScreen.BUTTON_CONTINUE),
                            Ensure.that(
                                            VisibilityQuestion.isPresent(
                                                    NumericScreen.LABEL_REASON.of(
                                                            "Enter your Password")))
                                    .isTrue(),
                            NumpadAction.onNuveisNumpad(clerkPassword),
                            TapAction.on(NumericScreen.BUTTON_CONTINUE));
                });
    }

    @Step("{0} fills the Clerk Login Form with Admin credentials")
    public static Performable defaultAdmin() {
        return Task.where(
                "{0} fills the Clerk Login Form with Admin credentials", clerk("1", "111111"));
    }
}
