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
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.WebElement;

public class TextQuestion {

    private TextQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Question<String> of(Target target) {
        return actor -> {
            WebElement element = target.resolveFor(actor);
            return actor.asksFor(of(element));
        };
    }

    public static Question<String> of(WebElement element) {
        return actor -> {
            String value = element.getText();

            if (StringUtils.isBlank(value)) {
                value = element.getDomProperty("value");
            }

            return StringUtils.nullSafeToString(value);
        };
    }
}
