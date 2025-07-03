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
import com.nuvei.screenplay.interactions.EnterAction;
import com.nuvei.screenplay.interactions.NumpadAction;
import com.nuvei.screenplay.questions.WhatSeriesIsThisTerminalQuestion;
import com.nuvei.screenplay.ui.common.NumericScreen;
import com.nuvei.utils.VariablesSingleton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.platform.commons.util.StringUtils;

public class NumpadTask implements Task {

    private final String numberSequence;
    private final boolean confirmValue;

    private NumpadTask(String numberSequence, boolean confirmValue) {
        this.numberSequence = numberSequence;
        this.confirmValue = confirmValue;
    }

    public static Performable digit(String numberSequence) {
        return Task.where("{0} enters the number sequence", new NumpadTask(numberSequence, false));
    }

    public static Performable digitAndConfirm(String numberSequence) {
        return Task.where(
                "{0} enters and confirms the number sequence",
                new NumpadTask(numberSequence, true));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (StringUtils.isBlank(numberSequence)) {
            return; // Do nothing if the number sequence is empty
        }

        if (Boolean.TRUE.equals(actor.asksFor(WhatSeriesIsThisTerminalQuestion.isTSeries()))) {
            useOnScreenNumpad(actor);
        } else if (Boolean.TRUE.equals(
                actor.asksFor(WhatSeriesIsThisTerminalQuestion.isMSeries()))) {
            usePhysicalNumpad(actor);
        } else if (Boolean.TRUE.equals(
                actor.asksFor(WhatSeriesIsThisTerminalQuestion.isPSeries()))) {
            useTextField(actor);
        } else {
            throw new UnsupportedOperationException("Unknown environment for numpad interaction.");
        }

        if (confirmValue) {
            actor.attemptsTo(ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));
        }
    }

    @Step("{0} uses the numpad to digit '#numberSequence'")
    private void useOnScreenNumpad(Actor actor) {
        int numpadUsageCount = VariablesSingleton.getInstance().getNumpadUsageCount();
        boolean isNotLongerNeededTestTheNumpad = numpadUsageCount > 2;

        boolean isPlainText = !numberSequence.contains(".");

        if (isPlainText && isNotLongerNeededTestTheNumpad) {
            useTextField(actor);
            return;
        }

        Target onScreenNumpadNumberButton = NumericScreen.BUTTON_NUMPAD_NUMBER;
        actor.attemptsTo(NumpadAction.digit(onScreenNumpadNumberButton, numberSequence));
        VariablesSingleton.getInstance().setNumpadUsageCount(numpadUsageCount + 1);
    }

    @Step("{0} uses the numpad to digit '#numberSequence' using the physical numpad")
    private void usePhysicalNumpad(Actor actor) {
        actor.attemptsTo(NumpadAction.digit(null, numberSequence));
    }

    private void useTextField(Actor actor) {
        Target onScreenTextField = NumericScreen.TEXTBOX_VALUE;
        actor.attemptsTo(EnterAction.into(onScreenTextField, numberSequence));
    }
}
