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

import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.common.ConfirmationScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.platform.commons.util.StringUtils;

public class ConfirmationScreenValidateTask implements Task {

    private final String screenTitle;
    private final String messageTitle;
    private final String messageDetail;
    private final boolean validateYesAndCancelButtons;

    public ConfirmationScreenValidateTask(
            String screenTitle,
            String messageTitle,
            String messageDetail,
            boolean validateYesAndCancelButtons) {
        this.screenTitle = screenTitle;
        this.messageTitle = messageTitle;
        this.messageDetail = messageDetail;
        this.validateYesAndCancelButtons = validateYesAndCancelButtons;
    }

    @Override
    @Step("{0} validates and dismisses the confirmation screen with Done")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Ensure.that(VisibilityQuestion.isPresent(ConfirmationScreen.TITLE)).isTrue(),
                Ensure.that(TextQuestion.of(ConfirmationScreen.TITLE))
                        .isEqualToIgnoringCase(screenTitle),
                Ensure.that(TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_TITLE))
                        .isEqualToIgnoringCase(messageTitle));

        if (!StringUtils.isBlank(messageDetail)) {
            actor.attemptsTo(
                    Ensure.that(TextQuestion.of(ConfirmationScreen.LABEL_MESSAGE_DETAIL))
                            .isEqualToIgnoringCase(messageDetail));
        }

        if (validateYesAndCancelButtons) {
            actor.attemptsTo(
                    Ensure.that(VisibilityQuestion.isPresent(ConfirmationScreen.BUTTON_CANCEL))
                            .isTrue(),
                    Ensure.that(VisibilityQuestion.isPresent(ConfirmationScreen.BUTTON_YES))
                            .isTrue());
        }
    }

    public static ConfirmationScreenValidateTask withYesAndCancelButtons(
            String title, String msgTitle, String msgDetail) {
        return new ConfirmationScreenValidateTask(title, msgTitle, msgDetail, true);
    }

    public static ConfirmationScreenValidateTask withOutButtons(
            String title, String msgTitle, String msgDetail) {
        return new ConfirmationScreenValidateTask(title, msgTitle, msgDetail, false);
    }
}
