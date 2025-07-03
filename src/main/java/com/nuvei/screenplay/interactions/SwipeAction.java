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
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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
    private final Target target;

    private SwipeAction(Target target, SwipeDirection direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        var driver = actor.usingAbilityTo(BrowseTheApp.class).driver();

        int left = 0;
        int top = 0;
        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        Dimension targetSize;

        if (target == null) {
            targetSize = driver.manage().window().getSize();
        } else {
            WebElementFacade webElementFacade = target.resolveFor(actor);
            targetSize = webElementFacade.getSize();

            Point targetLocation = webElementFacade.getLocation();
            left = targetLocation.getX();
            top = targetLocation.getY();
        }

        switch (direction) {
            case DOWN -> {
                startX = left + targetSize.width / 2; // Start horizontally in the center
                startY = top + (int) (targetSize.height * 0.3); // Start near the top
                endY = top + (int) (targetSize.height * 0.8); // End near the bottom
            }
            case UP -> {
                startX = left + targetSize.width / 2; // Start horizontally in the center
                startY = top + (int) (targetSize.height * 0.8); // Start near the bottom
                endY = top + (int) (targetSize.height * 0.3); // End near the top
            }
            case LEFT -> {
                startY = top + targetSize.height / 2; // Start vertically in the center
                startX = left + (int) (targetSize.width * 0.8); // Start near the right
                endX = left + (int) (targetSize.width * 0.2); // End near the left
            }
            case RIGHT -> {
                startY = top + targetSize.height / 2; // Start vertically in the center
                startX = left + (int) (targetSize.width * 0.2); // Start near the left
                endX = left + (int) (targetSize.width * 0.8); // End near the right
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

        driver.perform(Collections.singletonList(tapSequence));
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
        return new SwipeAction(null, SwipeDirection.DOWN);
    }

    public static SwipeAction toUp() {
        return new SwipeAction(null, SwipeDirection.UP);
    }

    public static SwipeAction overTargetToUp(Target target) {
        return new SwipeAction(target, SwipeDirection.UP);
    }

    public static SwipeAction toLeft() {
        return new SwipeAction(null, SwipeDirection.LEFT);
    }

    public static SwipeAction overTargetToLeft(Target target) {
        return new SwipeAction(target, SwipeDirection.LEFT);
    }

    public static SwipeAction toRight() {
        return new SwipeAction(null, SwipeDirection.RIGHT);
    }

    public static SwipeAction overTargetToRight(Target target) {
        return new SwipeAction(target, SwipeDirection.RIGHT);
    }
}
