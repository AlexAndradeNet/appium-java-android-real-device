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
import net.serenitybdd.core.pages.WebElementFacade;
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

    public static Performable tapBackArrow() {
        return Task.where(
                "{0} tap the Back Arrow on Screen",
                ClickAction.on(CommonObjects.BUTTON_ARROW_BACK));
    }

    public static Performable validateAndDismissPopupAlertWithOkButton(
            String title, String message) {
        return Task.where(
                "{0} validate the popup alert on the screen",
                Ensure.that(
                                "Should see the alert title is present",
                                VisibilityQuestion.isPresent(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isTrue(),
                Ensure.that(
                                "Should see the alert title: '%s'".formatted(title),
                                TextQuestion.of(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isEqualTo(title),
                Ensure.that(
                                "Should see the alert detail: '%s'".formatted(message),
                                TextQuestion.of(CommonObjects.POPUP_MESSAGE_CONTENT))
                        .isEqualTo(message),
                ClickAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_OK));
    }

    public static Performable validateConfirmationScreen(
            String screenTitle,
            String messageTitle,
            String messageDetail,
            boolean validateYesCancelButtons) {
        return Task.where(
                "{0} validates the confirmation screen",
                actor -> {
                    actor.attemptsTo(
                            Ensure.that(
                                            "Should see the confirmation screen title",
                                            VisibilityQuestion.isPresent(ConfirmationScreen.TITLE))
                                    .isTrue(),
                            Ensure.that(
                                            "Should see the title: '%s'".formatted(screenTitle),
                                            TextQuestion.of(ConfirmationScreen.TITLE))
                                    .isEqualTo(screenTitle),
                            Ensure.that(
                                            "Should see the message title: '%s'"
                                                    .formatted(messageTitle),
                                            TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                                    .isEqualTo(messageTitle));

                    if (StringUtils.isNotBlank(messageDetail)) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the message detail: '%s'"
                                                        .formatted(messageDetail),
                                                TextQuestion.of(
                                                        ConfirmationScreen.LABEL_MESSAGE_DETAIL))
                                        .isEqualTo(messageDetail));
                    }

                    if (validateYesCancelButtons) {
                        actor.attemptsTo(
                                Ensure.that(
                                                "Should see the cancel button",
                                                VisibilityQuestion.isPresent(
                                                        ConfirmationScreen.BUTTON_CANCEL))
                                        .isTrue(),
                                Ensure.that(
                                                "Should see the yes button",
                                                VisibilityQuestion.isPresent(
                                                        ConfirmationScreen.BUTTON_YES))
                                        .isTrue());
                    }
                });
    }

    public static Performable validateConfirmationScreen(
            String screenTitle, String messageTitle, String messageDetail) {
        return validateConfirmationScreen(screenTitle, messageTitle, messageDetail, false);
    }

    public static Performable validateAndDismissConfirmationScreenWithDoneButton(
            String title, String messageTitle, String messageDetail) {
        return Task.where(
                "{0} validates and dismisses the confirmation screen",
                validateConfirmationScreen(title, messageTitle, messageDetail, false),
                ClickAction.on(ConfirmationScreen.BUTTON_DONE));
    }

    private static Performable modifyAllTogglesOnTheScreen(boolean toggleOn) {
        return Task.where(
                "{0} deactivates all toggles in the screen",
                actor -> {
                    int previousSize = 0;
                    Set<WebElementFacade> togglesList = new LinkedHashSet<>();
                    Set<String> uniqueToglesLables = new LinkedHashSet<>();

                    do {
                        uniqueToglesLables.addAll(
                                CommonObjects.TOGGLE_LABEL_LIST.resolveAllFor(actor).texts());
                        togglesList.addAll(CommonObjects.TOGGLE_LIST.resolveAllFor(actor));

                        int currentSize = uniqueToglesLables.size();

                        if (currentSize > previousSize) {
                            // Process only the newly discovered toggles
                            togglesList.stream()
                                    .skip(previousSize) // Skip already processed toggles
                                    .forEach(
                                            toggle -> {
                                                if (toggleOn) {
                                                    actor.attemptsTo(ToggleAction.toOn(toggle));
                                                } else {
                                                    actor.attemptsTo(ToggleAction.toOff(toggle));
                                                }
                                            });

                            // Update the previous size to reflect the processed toggles
                            previousSize = currentSize;
                            actor.attemptsTo(SwipeAction.toUp());
                        } else {
                            break;
                        }
                    } while (true);
                });
    }

    public static Performable turnOffAllTogglesOnTheScreen() {
        return modifyAllTogglesOnTheScreen(false);
    }

    public static Performable turnOnAllTogglesOnTheScreen() {
        return modifyAllTogglesOnTheScreen(true);
    }

    public static Performable navigateMenuUntilElementIsVisible(Target target) {
        return Task.where(
                actor -> {
                    WebElement element = target.resolveFor(actor);
                    actor.attemptsTo(navigateMenuUntilElementIsVisible(element));
                });
    }

    public static Performable navigateMenuUntilElementIsVisible(WebElement element) {
        return Task.where(
                "{0} navigates the menu until the element is visible and taps on it",
                actor -> {
                    final int MAX_SCREENS = 3;
                    int currentScreen = 1;

                    while (actor.asksFor(VisibilityQuestion.notPresent(element))
                            && currentScreen < MAX_SCREENS) {
                        actor.attemptsTo(SwipeAction.toUp());
                        currentScreen++;
                    }
                });
    }

    public static Performable returnToMainScreen() {
        return Task.where(
                "{0} navigates back to the main screen",
                actor -> {
                    try {
                        while (actor.asksFor(VisibilityQuestion.isPresent(BUTTON_ARROW_BACK))) {
                            actor.attemptsTo(ClickAction.on(BUTTON_ARROW_BACK));
                        }
                    } catch (NoSuchElementException ignored) {
                        // Do nothing
                    }
                });
    }
}
