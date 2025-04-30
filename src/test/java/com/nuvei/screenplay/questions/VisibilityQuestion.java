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
package com.nuvei.screenplay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebElement;

/*
 *  Nuvei App doesn't respond to the Displayed method, so we need to create a custom method to check if an element is present.
 */
public class VisibilityQuestion {

    private VisibilityQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Question<Boolean> isPresent(Target target) {
        return actor -> {
            WebElement element = target.resolveFor(actor);
            if (element == null) {
                return false;
            }

            return safelyCheck(element::isEnabled);
        };
    }

    public static Question<Boolean> isPresent(WebElement element) {
        return actor -> safelyCheck(element::isEnabled);
    }

    public static Question<Boolean> notPresent(Target target) {
        return actor -> !actor.asksFor(isPresent(target));
    }

    public static Question<Boolean> notPresent(WebElement element) {
        return actor -> !actor.asksFor(isPresent(element));
    }

    /**
     * Safely checks a condition and handles NoSuchElementException.
     *
     * @param condition A lambda to evaluate the presence of the element.
     * @return The result of the evaluation, or false if the element is not found.
     */
    private static boolean safelyCheck(CheckCondition condition) {
        try {
            return condition.evaluate();
        } catch (NoSuchElementException | UnsupportedCommandException e) {
            return false; // Element is not present
        }
    }

    /** Functional interface to wrap checks that may throw exceptions. */
    @FunctionalInterface
    private interface CheckCondition {
        boolean evaluate();
    }
}
