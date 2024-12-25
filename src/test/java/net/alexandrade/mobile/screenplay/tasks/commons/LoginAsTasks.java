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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.alexandrade.mobile.screenplay.interactions.Tap;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;

public class LoginAsTasks {

    private LoginAsTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    // Cache for numeric button Targets
    private static final Map<String, Target> cachedTargets = new HashMap<>();

    @Step("{0} fills the clerk ID '{1}' and password '{2}'")
    public static Performable clerk(String clerkId, String clerkPassword) {
        return Task.where(
                "{0} fills the clerk ID and password",
                actor -> {
                    // Tap on each digit of the clerk ID
                    actor.attemptsTo(
                            Ensure.that(NumericScreen.TITLE.of("CLERK MANAGEMENT")).isEnabled(),
                            Ensure.that(NumericScreen.LABEL_REASON.of("Enter your Clerk ID"))
                                    .isEnabled());

                    digitValueInTheNumPad(actor, clerkId);

                    actor.attemptsTo(
                            Tap.on(NumericScreen.BUTTON_CONTINUE),
                            Ensure.that(NumericScreen.LABEL_REASON.of("Enter your Password"))
                                    .isEnabled());

                    digitValueInTheNumPad(actor, clerkPassword);
                    actor.attemptsTo(Tap.on(NumericScreen.BUTTON_CONTINUE));
                });
    }

    private static void digitValueInTheNumPad(Actor actor, String value) {
        List<Tap> tapActions =
                value.chars()
                        .mapToObj(c -> String.valueOf((char) c)) // Convert each character to String
                        .map(digit -> Tap.on(getOrCreateButtonForDigit(digit))) // Resolve Target
                        .toList();

        // Perform all Tap actions in a single attempt
        actor.attemptsTo(tapActions.toArray(new Tap[0]));
    }

    @Step("{0} fills the Clerk Login Form with Admin credentials")
    public static Performable admin() {
        return Task.where(
                "{0} fills the Clerk Login Form with Admin credentials", clerk("1", "111111"));
    }

    private static Target getOrCreateButtonForDigit(String digit) {
        // Cache the Target only if it doesn't exist
        return cachedTargets.computeIfAbsent(digit, d -> NumericScreen.BUTTON_NUMBER.of(digit));
    }
}
