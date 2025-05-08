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

import com.nuvei.screenplay.interactions.SwipeAction;
import com.nuvei.screenplay.interactions.ToggleAction;
import com.nuvei.screenplay.ui.CommonObjects;
import java.util.LinkedHashSet;
import java.util.Set;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;

public class AllTogglesTurnOffTask implements Task {

    @Override
    @Step("{0} turns off all toggle switches on the screen")
    public <T extends Actor> void performAs(T actor) {
        int previousSize = 0;
        Set<String> labels = new LinkedHashSet<>();

        do {
            labels.addAll(CommonObjects.ALL_TOGGLE_LABEL_LIST.resolveAllFor(actor).texts());
            int currentSize = labels.size();

            if (currentSize > previousSize) {
                labels.stream()
                        .skip(previousSize)
                        .forEach(
                                label -> {
                                    Target toggle = CommonObjects.TOGGLE.of(label);
                                    actor.attemptsTo(ToggleAction.toOff(toggle));
                                });

                previousSize = currentSize;
                actor.attemptsTo(SwipeAction.toUp());
            } else {
                break;
            }
        } while (true);
    }

    public static AllTogglesTurnOffTask now() {
        return new AllTogglesTurnOffTask();
    }
}
