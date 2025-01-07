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
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.NoSuchElementException;

public class MainTileScreenTasks {

    private MainTileScreenTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable waitTheAppIsFullyLoaded() {
        return Task.where(
                "{0} waits the app is fully loaded",
                WaitUntil.the(SPINNER, isNotPresent()).forNoMoreThan(100).seconds());
    }

    public static Performable openSale() {
        return Task.where("{0} opens the Sale main tile", ClickAction.on(BUTTON_SALE_TRANSACTION));
    }

    public static Performable openRefund() {
        return Task.where(
                "{0} opens the Refund main tile", ClickAction.on(BUTTON_REFUND_TRANSACTION));
    }

    public static Performable openMoto() {
        return Task.where("{0} opens the Moto main tile", ClickAction.on(BUTTON_MOTO_TRANSACTION));
    }

    public static Performable openBatchOrSettle() {
        return Task.where(
                "{0} opens the Batch or Settle main tile",
                ClickAction.on(BUTTON_BATCH_OR_SETTLE_TRANSACTION));
    }

    public static Performable openVoid() {
        return Task.where(
                "{0} opens the Void main tile",
                actor -> navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_VOID_TRANSACTION));
    }

    public static Performable openHelpDeskMenu() {
        return Task.where(
                "{0} opens the Help main tile",
                actor -> navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_HELP_DESK));
    }

    public static Performable openTIDInfo() {
        return Task.where(
                "{0} opens the TID info tile",
                actor -> navigateUntilElementIsVisibleAndTapOnIt(actor, BUTTON_TID));
    }

    private static void navigateUntilElementIsVisibleAndTapOnIt(Actor actor, Target target) {
        do {
            // The element could be present since the second screen
            try {
                actor.attemptsTo(SwipeAction.toLeft());

                if (VisibilityQuestion.isPresent(target).answeredBy(actor)) {
                    actor.attemptsTo(ClickAction.on(target));
                    break;
                }
            } catch (NoSuchElementException ignored) {
                // Do nothing
            }
        } while (true);
    }
}
