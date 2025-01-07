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
import net.alexandrade.mobile.screenplay.driver.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

/** The Nuvei app does not support the click action, so we need to use the tap action instead. */
public class ClickAction implements Interaction {

    private final Target target;
    private final WebElement webElement;
    private final int duration;

    protected ClickAction(Target target, WebElement webElement, int duration) {
        this.target = target;
        this.webElement = webElement;
        this.duration = duration;
    }

    public <T extends Actor> void performAs(T actor) {
        WebElement element = (webElement == null) ? target.resolveFor(actor) : webElement;

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

        WebDriverFacade appiumDriver = AppiumDriver.getDriver();
        appiumDriver.perform(Collections.singletonList(tapSequence));
    }

    public static ClickAction on(Target target) {
        return instrumented(ClickAction.class, target, null, 0);
    }

    protected static ClickAction on(WebElement webElement) {
        return instrumented(ClickAction.class, null, webElement, 0);
    }

    protected static ClickAction withLongPressOn(Target target) {
        return instrumented(ClickAction.class, target, null, 100);
    }

    protected static ClickAction withLongPressOn(WebElement webElement) {
        return instrumented(ClickAction.class, null, webElement, 100);
    }
}
