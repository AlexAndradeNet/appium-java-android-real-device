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

import java.time.Duration;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;

public class VisibilityQuestion {

    private VisibilityQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Question<Boolean> isPresent(Target target) {
        return actor -> {
            try {
                return target.resolveFor(actor).isEnabled();
            } catch (NoSuchElementException e) {
                return false; // Treat NoSuchElementException as "not present"
            }
        };
    }

    public static Question<Boolean> isPresentWithOutWait(Target target) {
        return actor -> {
            var driver = Serenity.getDriver();
            // Create a FluentWait with zero timeout
            FluentWait<WebDriver> wait =
                    new FluentWait<>(driver)
                            .withTimeout(Duration.ZERO)
                            .pollingEvery(Duration.ZERO)
                            .ignoring(Exception.class); // Gracefully handle exceptions

            // Attempt to resolve the element
            try {
                return wait.until(driver1 -> target.resolveFor(actor).isEnabled());
            } catch (NoSuchElementException e) {
                return false; // Treat NoSuchElementException as "not present"
            }
        };
    }
}
