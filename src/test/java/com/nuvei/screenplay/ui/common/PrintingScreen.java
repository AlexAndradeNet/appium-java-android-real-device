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
package com.nuvei.screenplay.ui.common;

import io.appium.java_client.AppiumBy;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PrintingScreen {
    private PrintingScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_XPATH = "(//android.widget.TextView)[%s]";

    public static final Target TITLE =
            Target.the("Title").located(By.xpath(BASE_XPATH.formatted("1")));

    public static final Target LABEL_MESSAGE_TITLE =
            Target.the("Label Success").located(By.xpath(BASE_XPATH.formatted("2")));

    public static final Target LABEL_MESSAGE_DETAIL =
            Target.the("Label Clerk Deleted").located(By.xpath(BASE_XPATH.formatted("3")));

    private static final String BASE_SELECTOR = "new UiSelector().text(\"%s\")";

    public static final Target BUTTON_RECEIPT_OPTIONS =
            Target.the("Button Receipt Options")
                    .located(
                            AppiumBy.androidUIAutomator(
                                    BASE_SELECTOR.formatted("RECEIPT OPTIONS")));

    public static final Target BUTTON_PAPER_RECEIPT =
            Target.the("Button Paper Receipt")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("PAPER RECEIPT")));

    public static final Target DONE =
            Target.the("Button Done")
                    .located(AppiumBy.androidUIAutomator(BASE_SELECTOR.formatted("DONE")));
}
