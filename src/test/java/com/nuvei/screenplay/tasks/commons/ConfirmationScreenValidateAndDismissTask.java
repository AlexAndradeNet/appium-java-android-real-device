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
import com.nuvei.screenplay.ui.common.ConfirmationScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ConfirmationScreenValidateAndDismissTask implements Task {

    enum Button {
        YES,
        CANCEL,
        DONE
    }

    private final String screenTitle;
    private final String messageTitle;
    private final String messageDetail;
    private final Button button;

    public ConfirmationScreenValidateAndDismissTask(
            String screenTitle, String messageTitle, String messageDetail, Button button) {
        this.screenTitle = screenTitle;
        this.messageTitle = messageTitle;
        this.messageDetail = messageDetail;
        this.button = button;
    }

    @Override
    @Step("{0} validates and dismisses the confirmation screen with Done")
    public <T extends Actor> void performAs(T actor) {
        if (button == Button.YES || button == Button.CANCEL) {
            actor.attemptsTo(
                    ConfirmationScreenValidateTask.withYesAndCancelButtons(
                            screenTitle, messageTitle, messageDetail));
        }

        if (button == Button.YES) {
            actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_YES));
        }

        if (button == Button.CANCEL) {
            actor.attemptsTo(ClickAction.on(ConfirmationScreen.BUTTON_CANCEL));
        }

        if (button == Button.DONE) {
            actor.attemptsTo(
                    ConfirmationScreenValidateTask.withOutButtons(
                            screenTitle, messageTitle, messageDetail),
                    ClickAction.on(ConfirmationScreen.BUTTON_DONE));
        }
    }

    public static ConfirmationScreenValidateAndDismissTask withYes(
            String title, String msgTitle, String msgDetail) {
        return new ConfirmationScreenValidateAndDismissTask(title, msgTitle, msgDetail, Button.YES);
    }

    public static ConfirmationScreenValidateAndDismissTask withNo(
            String title, String msgTitle, String msgDetail) {
        return new ConfirmationScreenValidateAndDismissTask(
                title, msgTitle, msgDetail, Button.CANCEL);
    }

    public static ConfirmationScreenValidateAndDismissTask withDone(
            String title, String msgTitle, String msgDetail) {
        return new ConfirmationScreenValidateAndDismissTask(
                title, msgTitle, msgDetail, Button.DONE);
    }
}
