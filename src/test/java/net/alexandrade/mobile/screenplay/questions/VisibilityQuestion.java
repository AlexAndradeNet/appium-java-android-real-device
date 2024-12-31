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
package net.alexandrade.mobile.screenplay.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class VisibilityQuestion {

    private VisibilityQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Question<Boolean> isPresent(Target target) {
        return actor -> isPresent(target.resolveFor(actor)).answeredBy(actor);
    }

    public static Question<Boolean> isPresent(WebElement element) {
        return actor -> {
            try {
                return element.isEnabled();
            } catch (NoSuchElementException e) {
                return false; // Treat NoSuchElementException as "not present"
            }
        };
    }
}
