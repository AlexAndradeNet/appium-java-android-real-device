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

import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.interactions.NumpadAction;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.platform.commons.util.StringUtils;

public class LoginAsTasks {

    private LoginAsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable fillClerkID(String screenTitle, String clerkId) {
        return Task.where(
                "{0} fills the clerk ID and password",
                actor -> {
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
                                                        NumericScreen.LABEL_REASON.of(
                                                                "Enter your Clerk ID")))
                                        .isTrue());
                    }
                    actor.attemptsTo(NumpadAction.digit(clerkId));

                    if (withValidation) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the value of the Clerk ID field: '%s'"
                                                        .formatted(clerkId),
                                                TextQuestion.of(NumericScreen.TEXTBOX_VALUE))
                                        .isEqualTo(clerkId));
                    }

                    actor.attemptsTo(ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));
                });
    }

    public static Performable fillClerkID(String clerkId) {
        return fillClerkID(null, clerkId);
    }

    public static Performable fillPassword(String screenTitle, String clerkPassword) {
        return Task.where(
                "{0} fills the clerk ID and password {1}",
                actor -> {
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
                                                        NumericScreen.LABEL_REASON.of(
                                                                "Enter your Password")))
                                        .isTrue());
                    }

                    actor.attemptsTo(NumpadAction.digit(clerkPassword));

                    if (withValidation) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the value of the Password field",
                                                TextQuestion.of(NumericScreen.TEXTBOX_VALUE))
                                        .isEqualTo("******"));
                    }

                    actor.attemptsTo(ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));
                });
    }

    public static Performable fillPassword(String clerkPassword) {
        return fillPassword(null, clerkPassword);
    }

    public static Performable as(String clerkId, String clerkPassword) {
        return Task.where(
                "{0} fills the clerk ID and password",
                fillClerkID(clerkId), fillPassword(clerkPassword));
    }
}
