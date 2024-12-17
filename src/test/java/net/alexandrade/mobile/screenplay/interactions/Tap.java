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
package net.alexandrade.mobile.screenplay.interactions;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import java.time.Duration;
import java.util.Collections;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

/**
 * An Interaction that performs a tap action on a given target element using the W3C Pointer Input
 * API.
 */
public class Tap implements Interaction {

    private final Target target;
    private final int duration;

    protected Tap(Target target, int duration) {
        this.target = target;
        this.duration = duration;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriverFacade appiumDriver = (WebDriverFacade) Serenity.getDriver();

        WebElement element = target.resolveFor(actor);

        // Calculate the element's center coordinates
        final int centerX = element.getRect().x + (element.getRect().width / 2);
        final int centerY = element.getRect().y + (element.getRect().height / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tapSequence =
                new Sequence(finger, 1)
                        .addAction(
                                finger.createPointerMove(
                                        Duration.ZERO,
                                        PointerInput.Origin.viewport(),
                                        centerX,
                                        centerY))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(new Pause(finger, Duration.ofMillis(duration)))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        appiumDriver.perform(Collections.singletonList(tapSequence));
    }

    public static Tap on(Target target) {
        return instrumented(Tap.class, target, 0);
    }

    public static Tap withLongPressOn(Target target) {
        return instrumented(Tap.class, target, 100);
    }
}
