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
import com.nuvei.screenplay.questions.EnvironmentQuestion;
import com.nuvei.screenplay.ui.common.NumericScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.junit.platform.commons.util.StringUtils;

public class NumpadTasks {

    private NumpadTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable digit(Actor actor, String numberSequence) {

        if (StringUtils.isBlank(numberSequence)) {
            return Task.where("{0} do nothing because the number sequence is empty");
        }

        if (actor.asksFor(EnvironmentQuestion.isTSeries())) {
            return useOnScreenNumpad(actor, numberSequence);
        }

        if (actor.asksFor(EnvironmentQuestion.isMSeries())) {
            return usePhysicalNumpad(actor, numberSequence);
        }

        if (actor.asksFor(EnvironmentQuestion.isPSeries())) {
            return useTextField(actor, numberSequence);
        }

        throw new UnsupportedOperationException("Unknown environment for numpad interaction.");
    }

    public static Performable digitAndConfirmValueOrPrompt(Actor actor, String value) {
        actor.attemptsTo(
                NumpadTasks.digit(actor, value),
                ClickAction.on(NumericScreen.BUTTON_CONTINUE_OR_CONFIRM));
        return Task.where("{0} confirms the value '%s'".formatted(value));
    }

    private static Performable useOnScreenNumpad(Actor actor, String numberSequence) {
        Object recalledValue = actor.recall("numpadUsageCount");
        int numpadUsageCount = recalledValue == null ? 0 : (int) recalledValue;

        boolean isNotLongerNeededTestTheNumpad = numpadUsageCount > 2;
        boolean isPlainText = !numberSequence.contains(".");

        if (isPlainText && isNotLongerNeededTestTheNumpad) {
            return useTextField(actor, numberSequence);
        }

        Target onScreenNumpadNumberButton = NumericScreen.BUTTON_NUMPAD_NUMBER;

        actor.attemptsTo(NumpadAction.digit(onScreenNumpadNumberButton, numberSequence));

        actor.remember("numpadUsageCount", numpadUsageCount + 1);
        return Task.where("{0} uses the on screen numpad to digit: '%s'".formatted(numberSequence));
    }

    private static Performable usePhysicalNumpad(Actor actor, String numberSequence) {
        actor.attemptsTo(NumpadAction.digit(null, numberSequence));
        return Task.where("{0} uses the physical numpad to digit: '%s'".formatted(numberSequence));
    }

    private static Performable useTextField(Actor actor, String numberSequence) {
        Target onScreenTextField = NumericScreen.TEXTBOX_VALUE;
        actor.attemptsTo(EnterAction.into(onScreenTextField, numberSequence));
        return Task.where("{0} uses the text field to digit: '%s'".formatted(numberSequence));
    }
}
