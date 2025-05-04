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

import com.nuvei.screenplay.ability.BrowseTheApp;
import java.time.Duration;
import java.util.Collections;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

public class SwipeAction implements Interaction {

    private enum SwipeDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private static final int SWIPE_SPEED = 101;
    private final SwipeDirection direction;

    private SwipeAction(SwipeDirection direction) {
        this.direction = direction;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriverFacade appiumDriver = BrowseTheApp.driverFor(actor);

        Dimension windowSize = appiumDriver.manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction) {
            case DOWN -> {
                startX = windowSize.width / 2; // Start horizontally in the center
                startY = (int) (windowSize.height * 0.45); // Start near the top
                endY = (int) (windowSize.height * 0.65); // End near the bottom
            }
            case UP -> {
                startX = windowSize.width / 2; // Start horizontally in the center
                startY = (int) (windowSize.height * 0.65); // Start near the bottom
                endY = (int) (windowSize.height * 0.45); // End near the top
            }
            case LEFT -> {
                startY = windowSize.height / 2; // Start vertically in the center
                startX = (int) (windowSize.width * 0.8); // Start near the right
                endX = (int) (windowSize.width * 0.2); // End near the left
            }
            case RIGHT -> {
                startY = windowSize.height / 2; // Start vertically in the center
                startX = (int) (windowSize.width * 0.2); // Start near the left
                endX = (int) (windowSize.width * 0.8); // End near the right
            }
        }

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tapSequence = null;

        if (direction == SwipeDirection.UP || direction == SwipeDirection.DOWN) {
            tapSequence = getVerticalMovement(finger, startX, startY, endY);
        }

        if (direction == SwipeDirection.LEFT || direction == SwipeDirection.RIGHT) {
            tapSequence = getHorizontalMovement(finger, startY, startX, endX);
        }

        appiumDriver.perform(Collections.singletonList(tapSequence));
    }

    private static Sequence getVerticalMovement(
            PointerInput finger, int startX, int startY, int endY) {
        return new Sequence(finger, 1)
                .addAction(
                        finger.createPointerMove(
                                Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(
                        finger.createPointerMove(
                                Duration.ofMillis(SWIPE_SPEED), // Duration of the swipe
                                PointerInput.Origin.viewport(),
                                startX,
                                endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    }

    private static Sequence getHorizontalMovement(
            PointerInput finger, int startY, int startX, int endX) {
        return new Sequence(finger, 1)
                .addAction(
                        finger.createPointerMove(
                                Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(
                        finger.createPointerMove(
                                Duration.ofMillis(SWIPE_SPEED), // Duration of the swipe
                                PointerInput.Origin.viewport(),
                                endX,
                                startY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
    }

    public static SwipeAction toDown() {
        return new SwipeAction(SwipeDirection.DOWN);
    }

    public static SwipeAction toUp() {
        return new SwipeAction(SwipeDirection.UP);
    }

    public static SwipeAction toLeft() {
        return new SwipeAction(SwipeDirection.LEFT);
    }

    public static SwipeAction toRight() {
        return new SwipeAction(SwipeDirection.RIGHT);
    }
}
