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
import com.nuvei.screenplay.interactions.WaitAction;
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.ui.CommonObjects;
import com.nuvei.screenplay.ui.common.PrintingScreen;
import com.nuvei.utils.LogcatUtility;
import com.nuvei.utils.ReceiptUtility;
import com.nuvei.utils.SerenityReportHelper;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class PrintAndCaptureReceiptTask implements Task {

    enum ResponseType {
        APPROVED,
        DECLINED
    }

    private final ResponseType responseType;

    private PrintAndCaptureReceiptTask(ResponseType responseType) {
        this.responseType = responseType;
    }

    public static Performable approved() {
        return Task.where(
                "{0} prints and captures an approved receipt",
                new PrintAndCaptureReceiptTask(ResponseType.APPROVED));
    }

    public static Performable declined() {
        return Task.where(
                "{0} prints and captures a declined receipt",
                new PrintAndCaptureReceiptTask(ResponseType.DECLINED));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitAction.untilElementIsPresent(PrintingScreen.BUTTON_RECEIPT_OPTIONS));

        String saveLabelResponseForSoftAssertion =
                actor.asksFor(TextQuestion.of(PrintingScreen.LABEL_MESSAGE_TITLE));

        LogcatUtility logcatUtility = new LogcatUtility();
        logcatUtility.startLogcat(actor);

        actor.attemptsTo(
                ClickAction.on(PrintingScreen.BUTTON_RECEIPT_OPTIONS),
                ClickAction.on(PrintingScreen.BUTTON_PAPER_RECEIPT),
                ClickAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_PRINT_MERCHANT),
                WaitAction.untilElementIsPresent(PrintingScreen.DONE),
                ClickAction.on(PrintingScreen.DONE));

        String logcat = logcatUtility.stopLogcat();
        String receipt = ReceiptUtility.getMerchantReceipt(logcat);
        SerenityReportHelper.saveData("Merchant Receipt", receipt);

        String description = "The transaction result: '%s'";
        actor.attemptsTo(
                Ensure.that(description.formatted(saveLabelResponseForSoftAssertion))
                        .isEqualTo(description.formatted(responseType.name())));
    }
}
