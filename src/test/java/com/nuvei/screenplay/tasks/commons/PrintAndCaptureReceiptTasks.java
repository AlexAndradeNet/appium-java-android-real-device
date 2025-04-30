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
package com.nuvei.screenplay.tasks.commons;

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.tasks.settings.MainSettingsTasks;
import com.nuvei.screenplay.ui.CommonObjects;
import com.nuvei.screenplay.ui.common.PrintingScreen;
import com.nuvei.utils.LogcatUtility;
import com.nuvei.utils.ReportUtility;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class PrintAndCaptureReceiptTasks {

    enum responseType {
        APPROVED,
        DECLINED
    }

    private PrintAndCaptureReceiptTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable approved(Actor actor) {
        printAndCaptureReceipt(actor, responseType.APPROVED);
        return Task.where("{0} prints and captures an approved receipt");
    }

    public static Performable declined(Actor actor) {
        printAndCaptureReceipt(actor, responseType.DECLINED);
        return Task.where("{0} prints and captures a declined receipt");
    }

    private static void printAndCaptureReceipt(Actor actor, responseType responseType) {
        actor.attemptsTo(
                WaitAction.untilElementIsPresent(PrintingScreen.BUTTON_RECEIPT_OPTIONS),
                Ensure.that(
                                "Should get an approval",
                                TextQuestion.of(PrintingScreen.LABEL_MESSAGE_TITLE))
                        .isEqualTo(responseType.name()));

        LogcatUtility logcatUtility = new LogcatUtility();
        logcatUtility.startLogcat(actor);

        actor.attemptsTo(
                ClickAction.on(PrintingScreen.BUTTON_RECEIPT_OPTIONS),
                ClickAction.on(PrintingScreen.BUTTON_PAPER_RECEIPT),
                ClickAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_PRINT_MERCHANT),
                WaitAction.untilElementIsPresent(PrintingScreen.DONE),
                ClickAction.on(PrintingScreen.DONE));

        String logcat = logcatUtility.stopLogcat();
        ReportUtility.saveReceipt(logcat);

        String toggleName = actor.recall("toggleName");

        actor.attemptsTo(
                CommonTasks.returnToTheDashboardScreen(actor),
                MainSettingsTasks.openTransactionFlowScreen(actor),
                ToggleAction.toOff(CommonObjects.TOGGLE.of(toggleName)));
    }
}
