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

import java.util.*;
import net.alexandrade.mobile.screenplay.interactions.ScrollAction;
import net.serenitybdd.core.pages.ListOfWebElementFacades;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

public class ElementListQuestion {
    private ElementListQuestion() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Question<Integer> quantityOf(Target target) {
        return actor -> target.resolveAllFor(actor).size();
    }

    public static Question<String> listOfValues(Target target) {
        return actor -> {
            Set<String> uniqueTexts = new LinkedHashSet<>();
            do {
                ListOfWebElementFacades elements = target.resolveAllFor(actor);
                List<String> texts =
                        elements.stream().toList().stream().map(WebElement::getText).toList();
                uniqueTexts.addAll(texts);

                if (uniqueTexts.size() < 7) {
                    ScrollAction.scrollUp().performAs(actor);
                } else {
                    break;
                }
            } while (true);

            String result = String.join(", ", uniqueTexts);
            return result.replace("#", "");
        };
    }
}
