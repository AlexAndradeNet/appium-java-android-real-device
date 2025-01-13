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
package net.alexandrade.mobile.screenplay.tasks;

import static net.alexandrade.mobile.screenplay.ui.MainTileScreen.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.*;

import net.alexandrade.mobile.screenplay.interactions.ClickAction;
import net.alexandrade.mobile.screenplay.interactions.SwipeAction;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.NoSuchElementException;

public class MainTileScreenTasks {

    private MainTileScreenTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable waitTheAppIsFullyLoaded(Actor actor) {
        actor.attemptsTo(WaitUntil.the(SPINNER, isNotPresent()).forNoMoreThan(100).seconds());

        return Task.where("{0} waits the app is fully loaded");
    }

    public static Performable openSale(Actor actor) {
        actor.attemptsTo(ClickAction.on(BUTTON_SALE_TRANSACTION));
        return Task.where("{0} opens the Sale main tile");
    }

    public static Performable openRefund(Actor actor) {
        // Refund is not available when Crypto is enabled
        actor.attemptsTo(
                Ensure.that(
                                "Refund is not available when Crypto is enabled",
                                VisibilityQuestion.notPresent(BUTTON_CRYPTO_TRANSACTION))
                        .isTrue(),
                ClickAction.on(BUTTON_REFUND_TRANSACTION));

        return Task.where("{0} opens the Refund main tile");
    }

    public static Performable openMoto(Actor actor) {
        actor.attemptsTo(ClickAction.on(BUTTON_MOTO_TRANSACTION));
        return Task.where("{0} opens the Moto main tile");
    }

    public static Performable openBatchOrSettle(Actor actor) {
        actor.attemptsTo(ClickAction.on(BUTTON_BATCH_OR_SETTLE));

        return Task.where("{0} opens the Batch or Settle main tile");
    }

    private static Performable navigateUntilElementIsVisibleAndTapOnIt(Actor actor, Target target) {
        while (actor.asksFor(VisibilityQuestion.notPresent(target))
                && actor.asksFor(VisibilityQuestion.isPresent(BUTTON_NEXT))) {
            actor.attemptsTo(SwipeAction.toLeft());
        }
        actor.attemptsTo(ClickAction.on(target));

        return Task.where("{0} navigates until the element is visible and taps on it");
    }

    public static Performable openVoid(Actor actor) {
        actor.attemptsTo(navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_VOID_TRANSACTION));

        return Task.where("{0} opens the Void main tile");
    }

    public static Performable openHelpDeskMenu(Actor actor, boolean withVerification) {
        actor.attemptsTo(navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_HELP_DESK));

        if (withVerification) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should see the Help Desk title",
                                    VisibilityQuestion.isPresent(NumericScreen.TITLE.of("HELP")))
                            .isTrue(),
                    Ensure.that(
                                    "Should see the Help Desk subtitle",
                                    VisibilityQuestion.isPresent(
                                            NumericScreen.LABEL_REASON.of("Enter Super Password")))
                            .isTrue());
        }

        return Task.where("{0} opens the Help main tile");
    }

    public static Performable openHelpDeskMenu(Actor actor) {
        return openHelpDeskMenu(actor, false);
    }

    public static Performable openTIDInfo(Actor actor) {
        actor.attemptsTo(navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_TID));

        return Task.where("{0} opens the TID info tile");
    }

    public static Performable openSettings(Actor actor) {
        actor.attemptsTo(navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_SETTINGS));

        return Task.where("{0} opens the TID info tile");
    }

    public static Performable returnToInitialScreen(Actor actor) {
        try {
            while (actor.asksFor(VisibilityQuestion.isPresent(BUTTON_PREVIOUS))) {
                actor.attemptsTo(SwipeAction.toRight());
            }
        } catch (NoSuchElementException ignored) {
            // Do nothing
        }

        return Task.where("{0} navigates back to the main screen");
    }
}
