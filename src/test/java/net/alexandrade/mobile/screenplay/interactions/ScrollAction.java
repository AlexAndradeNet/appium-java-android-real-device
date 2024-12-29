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

import java.time.Duration;
import java.util.Collections;
import net.alexandrade.mobile.screenplay.driver.AppiumDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class ScrollAction implements Interaction {

    private enum ScrollDirection {
        UP,
        DOWN
    }

    private final ScrollDirection direction;

    public ScrollAction(ScrollDirection direction) {
        this.direction = direction;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriverFacade appiumDriver = AppiumDriver.getDriver();

        Dimension size = appiumDriver.manage().window().getSize();

        int startX = size.width / 2; // Start horizontally in the center
        int startY;

        int endY =
                switch (direction) {
                    case DOWN -> {
                        startY = (int) (size.height * 0.2); // Start near the top
                        yield (int) (size.height * 0.8); // End near the bottom
                    }
                    case UP -> {
                        startY = (int) (size.height * 0.8); // Start near the bottom
                        yield (int) (size.height * 0.2); // End near the top
                    }
                };

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tapSequence =
                new Sequence(finger, 1)
                        .addAction(
                                finger.createPointerMove(
                                        Duration.ZERO,
                                        PointerInput.Origin.viewport(),
                                        startX,
                                        startY))
                        .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                        .addAction(
                                finger.createPointerMove(
                                        Duration.ofMillis(1000), // Duration of the swipe
                                        PointerInput.Origin.viewport(),
                                        startX,
                                        endY))
                        .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        appiumDriver.perform(Collections.singletonList(tapSequence));
    }

    public static ScrollAction scrollDown() {
        return new ScrollAction(ScrollDirection.DOWN);
    }

    public static ScrollAction scrollUp() {
        return new ScrollAction(ScrollDirection.UP);
    }
}
