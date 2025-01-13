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
import net.alexandrade.mobile.screenplay.interactions.SwipeAction;
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
            int previousSize = 0; // Track the previous size of the uniqueTexts set

            do {
                // Resolve all elements for the actor
                ListOfWebElementFacades elements = target.resolveAllFor(actor);
                List<String> texts =
                        elements.stream().toList().stream().map(WebElement::getText).toList();

                // Add all new texts to the uniqueTexts set
                uniqueTexts.addAll(texts);

                // Check if the size has grown
                int currentSize = uniqueTexts.size();
                boolean isQuantityOfElementsGrowing = currentSize > previousSize;

                if (isQuantityOfElementsGrowing) {
                    // Update the previous size and continue scrolling
                    previousSize = currentSize;
                    actor.attemptsTo(SwipeAction.toUp());
                } else {
                    // Break if no growth in size
                    break;
                }
            } while (true);

            // Convert the unique texts set to a comma-separated string
            String result = String.join(", ", uniqueTexts);
            return result.replace("#", ""); // Optional: Remove unwanted characters
        };
    }
}
