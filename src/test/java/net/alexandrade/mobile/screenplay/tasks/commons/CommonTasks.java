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

import static net.alexandrade.mobile.screenplay.ui.CommonObjects.BUTTON_ARROW_BACK;

import java.util.LinkedHashSet;
import java.util.Set;
import net.alexandrade.mobile.screenplay.driver.AppiumDriver;
import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.interactions.SwipeAction;
import net.alexandrade.mobile.screenplay.interactions.ToggleAction;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.ui.CommonObjects;
import net.alexandrade.mobile.screenplay.ui.ConfirmationScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CommonTasks {
    private CommonTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable pressPhysicalBackKey() {
        return Task.where(
                "{0} press the physical back key",
                actor -> AppiumDriver.getDriver().navigate().back());
    }

    public static Performable tapBackArrow(Actor actor) {
        actor.attemptsTo(ClickAction.on(CommonObjects.BUTTON_ARROW_BACK));
        return Task.where("{0} tap the Back Arrow on Screen");
    }

    public static Performable validateAndDismissPopupAlertWithOkButton(
            Actor actor, String title, String message) {

        actor.attemptsTo(
                Ensure.that(
                                "Should see the alert title '%s'".formatted(title),
                                VisibilityQuestion.isPresent(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isTrue(),
                Ensure.that(
                                "Should see the alert title: '%s'".formatted(title),
                                TextQuestion.of(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isEqualToIgnoringCase(title),
                Ensure.that(
                                "Should see the alert detail: '%s'".formatted(message),
                                TextQuestion.of(CommonObjects.POPUP_MESSAGE_CONTENT))
                        .isEqualToIgnoringCase(message),
                ClickAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_OK));

        return Task.where("{0} validate the popup alert on the screen");
    }

    public static Performable validateConfirmationScreen(
            Actor actor,
            String screenTitle,
            String messageTitle,
            String messageDetail,
            boolean validateYesCancelButtons) {

        actor.attemptsTo(
                Ensure.that(
                                "Should see the confirmation screen title",
                                VisibilityQuestion.isPresent(ConfirmationScreen.TITLE))
                        .isTrue(),
                Ensure.that(
                                "Should see the title: '%s'".formatted(screenTitle),
                                TextQuestion.of(ConfirmationScreen.TITLE))
                        .isEqualToIgnoringCase(screenTitle),
                Ensure.that(
                                "Should see the message title: '%s'".formatted(messageTitle),
                                TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                        .isEqualToIgnoringCase(messageTitle));

        if (StringUtils.isNotBlank(messageDetail)) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the message detail: '%s'".formatted(messageDetail),
                                    TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_DETAIL))
                            .isEqualToIgnoringCase(messageDetail));
        }

        if (validateYesCancelButtons) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the cancel button",
                                    VisibilityQuestion.isPresent(ConfirmationScreen.BUTTON_CANCEL))
                            .isTrue(),
                    Ensure.that(
                                    "Should see the yes button",
                                    VisibilityQuestion.isPresent(ConfirmationScreen.BUTTON_YES))
                            .isTrue());
        }

        return Task.where("{0} validates the confirmation screen");
    }

    public static Performable validateConfirmationScreen(
            Actor actor, String screenTitle, String messageTitle, String messageDetail) {
        return validateConfirmationScreen(actor, screenTitle, messageTitle, messageDetail, false);
    }

    public static Performable validateAndDismissConfirmationScreenWithDoneButton(
            Actor actor, String title, String messageTitle, String messageDetail) {
        actor.attemptsTo(
                validateConfirmationScreen(actor, title, messageTitle, messageDetail, false),
                ClickAction.on(ConfirmationScreen.BUTTON_DONE));
        return Task.where("{0} validates and dismisses the confirmation screen");
    }

    private static Performable modifyAllTogglesOnTheScreen(Actor actor, boolean toggleOn) {
        int previousSize = 0;
        Set<String> uniqueTogglesLabels = new LinkedHashSet<>();

        do {
            uniqueTogglesLabels.addAll(
                    CommonObjects.TOGGLE_LABEL_LIST.resolveAllFor(actor).texts());

            int currentSize = uniqueTogglesLabels.size();
            boolean isQuantityOfElementsGrowing = currentSize > previousSize;

            if (isQuantityOfElementsGrowing) {
                // Process only the newly discovered toggles
                uniqueTogglesLabels.stream()
                        .skip(previousSize) // Skip already processed toggles
                        .forEach(
                                toggleLabel -> {
                                    Target toggleTarget = CommonObjects.TOGGLE.of(toggleLabel);

                                    if (toggleOn) {
                                        actor.attemptsTo(ToggleAction.toOn(toggleTarget));
                                    } else {
                                        actor.attemptsTo(ToggleAction.toOff(toggleTarget));
                                    }
                                });

                // Update the previous size to reflect the processed toggles
                previousSize = currentSize;
                actor.attemptsTo(SwipeAction.toUp());
            } else {
                break;
            }
        } while (true);

        return Task.where("{0} deactivates all toggles in the screen");
    }

    public static Performable turnOffAllTogglesOnTheScreen(Actor actor) {
        return modifyAllTogglesOnTheScreen(actor, false);
    }

    public static Performable turnOnAllTogglesOnTheScreen(Actor actor) {
        return modifyAllTogglesOnTheScreen(actor, true);
    }

    public static Performable navigateMenuUntilElementIsVisible(Actor actor, Target target) {
        WebElement element = target.resolveFor(actor);
        actor.attemptsTo(navigateMenuUntilElementIsVisible(actor, element));

        return Task.where("{0} navigates the menu until the element is visible and taps on it");
    }

    public static Performable navigateMenuUntilElementIsVisible(Actor actor, WebElement element) {
        final int MAX_PAGES_OR_SCROLLS = 3;

        int currentPage = 1;

        while (actor.asksFor(VisibilityQuestion.notPresent(element))
                && currentPage < MAX_PAGES_OR_SCROLLS) {
            actor.attemptsTo(SwipeAction.toUp());
            currentPage++;
        }

        return Task.where("{0} navigates the menu until the element is visible and taps on it");
    }

    public static Performable returnToMainScreen(Actor actor) {
        try {
            while (actor.asksFor(VisibilityQuestion.isPresent(BUTTON_ARROW_BACK))) {
                actor.attemptsTo(ClickAction.on(BUTTON_ARROW_BACK));
            }
        } catch (NoSuchElementException ignored) {
            // Do nothing
        }

        return Task.where("{0} navigates back to the main screen");
    }
}
