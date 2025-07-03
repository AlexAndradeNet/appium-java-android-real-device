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
import com.nuvei.screenplay.questions.VisibilityQuestion;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class ScrollUntilVisibleTask implements Task {

    private final Target target;
    private final WebElement webElement;

    public ScrollUntilVisibleTask(Target target, WebElement webElement) {
        this.target = target;
        this.webElement = webElement;
    }

    @Override
    @Step("{0} scrolls until the element is visible")
    public <T extends Actor> void performAs(T actor) {
        WebElement element = (webElement == null) ? target.resolveFor(actor) : webElement;
        int scrolls = 0;
        final int MAX = 3;

        while (Boolean.TRUE.equals(actor.asksFor(VisibilityQuestion.notPresent(element)))
                && scrolls < MAX) {
            actor.attemptsTo(SwipeAction.toUp());
            scrolls++;
        }
    }

    public static ScrollUntilVisibleTask forObject(Target target) {
        return new ScrollUntilVisibleTask(target, null);
    }

    public static ScrollUntilVisibleTask forObject(WebElement webElement) {
        return new ScrollUntilVisibleTask(null, webElement);
    }
}
