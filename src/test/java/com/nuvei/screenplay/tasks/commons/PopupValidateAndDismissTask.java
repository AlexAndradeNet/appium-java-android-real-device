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
import com.nuvei.screenplay.questions.TextQuestion;
import com.nuvei.screenplay.questions.VisibilityQuestion;
import com.nuvei.screenplay.ui.CommonObjects;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;

public class PopupValidateAndDismissTask implements Task {

    private final String title;
    private final String message;

    public PopupValidateAndDismissTask(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    @Step("{0} validates and dismisses a popup alert")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Ensure.that(VisibilityQuestion.isPresent(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isTrue(),
                Ensure.that(TextQuestion.of(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isEqualToIgnoringCase(title),
                Ensure.that(TextQuestion.of(CommonObjects.POPUP_MESSAGE_CONTENT))
                        .isEqualToIgnoringCase(message),
                ClickAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_OK));
    }

    public static PopupValidateAndDismissTask with(String title, String message) {
        return new PopupValidateAndDismissTask(title, message);
    }
}
