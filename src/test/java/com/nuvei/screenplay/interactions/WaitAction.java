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
package com.nuvei.screenplay.interactions;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotPresent;

import com.nuvei.features.steps.Hooks;
import com.nuvei.screenplay.ability.BrowseTheApp;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.utils.SimpleLogger;
import java.time.Duration;
import java.time.Instant;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitAction implements Task {

    private static final SimpleLogger logger = new SimpleLogger(Hooks.class);
    private final long seconds;
    private final Target target;

    protected WaitAction(long seconds, Target target) {
        this.seconds = seconds;
        this.target = target;
    }

    public static Performable forSpecificTime(long seconds) {
        return Tasks.instrumented(WaitAction.class, seconds, null);
    }

    public static Performable untilElementIsPresent(Target target) {
        return Tasks.instrumented(WaitAction.class, 0, target);
    }

    public static Performable untilElementIsNotPresent(Target target) {
        return Tasks.instrumented(WaitAction.class, 1, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Instant startTime = Instant.now();

        if (target != null) {
            if (seconds == 0) {
                logger.debug(
                        "Waiting for element: %s to be present (Started at: %s)"
                                .formatted(target.getName(), startTime));
                waitUntilElementIsPresent(actor);
            } else {
                logger.debug(
                        "Waiting for element: %s to be not present (Started at: %s)"
                                .formatted(target.getName(), startTime));
                waitUntilElementIsNotPresent();
            }
        } else {
            logger.debug("Waiting for %s seconds (Started at: %s)".formatted(seconds, startTime));
            waitForTime();
        }

        logger.debug("Wait completed (Ended at: {})" + Instant.now());
    }

    private void waitForTime() {
        try {
            Thread.sleep(Duration.ofSeconds(seconds).toMillis());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread was interrupted during sleep", e);
        }
    }

    private void waitUntilElementIsPresent(Actor actor) {
        var driver = BrowseTheApp.driverFor(actor);
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(100))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .until(driverLambda -> actor.asksFor(VisibilityQuestion.isPresent(target)));
    }

    private void waitUntilElementIsNotPresent() {
        WaitUntil.the(target, isNotPresent()).forNoMoreThan(100).seconds();
    }
}
