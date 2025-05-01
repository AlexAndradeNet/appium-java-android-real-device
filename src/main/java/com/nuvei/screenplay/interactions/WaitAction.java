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

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

import com.nuvei.screenplay.ability.BrowseTheApp;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.utils.SimpleLogger;
import java.time.Duration;
import java.time.Instant;
import net.serenitybdd.screenplay.*;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;

public class WaitAction implements Interaction {

    private static final SimpleLogger logger = new SimpleLogger(WaitAction.class);
    public static final int MAX_SECONDS = 100;
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

    /**
     * Waits until the element is present on the screen.
     *
     * <p>This method uses a FluentWait instead of Serenity's WaitUntil because the elements in the
     * app don't answer to the isVisible and isEnabled doesn't work.
     *
     * @param actor The actor performing the action.
     */
    private void waitUntilElementIsPresent(Actor actor) {
        var driver = BrowseTheApp.driverFor(actor);
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(MAX_SECONDS))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class)
                .until(driverLambda -> actor.asksFor(VisibilityQuestion.isPresent(target)));
    }

    private void waitUntilElementIsNotPresent() {
        WaitUntil.the(target, isNotPresent()).forNoMoreThan(MAX_SECONDS).seconds();
    }
}
