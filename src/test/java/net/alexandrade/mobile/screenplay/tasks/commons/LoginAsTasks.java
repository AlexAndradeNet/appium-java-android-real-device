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
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.platform.commons.util.StringUtils;

public class LoginAsTasks {

    private LoginAsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable fillClerkID(Actor actor, String screenTitle, String clerkId) {
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

        return Task.where("{0} fills the clerk ID and password");
    }

    public static Performable fillClerkID(Actor actor, String clerkId) {
        return fillClerkID(actor, null, clerkId);
    }

    public static Performable fillPassword(Actor actor, String screenTitle, String clerkPassword) {
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

        actor.attemptsTo(NumpadAction.digit(clerkPassword));

        if (withValidation) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the value of the Password field",
                                    TextQuestion.of(NumericScreen.TEXTBOX_VALUE))
                            .isEqualTo("******"));
        }

        actor.attemptsTo(ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));

        return Task.where("{0} fills the clerk ID and password {1}");
    }

    public static Performable fillPassword(Actor actor, String clerkPassword) {
        return fillPassword(actor, null, clerkPassword);
    }

    public static Performable as(Actor actor, String clerkId, String clerkPassword) {
        return Task.where(
                "{0} fills the clerk ID and password",
                fillClerkID(actor, clerkId), fillPassword(actor, clerkPassword));
    }
}
