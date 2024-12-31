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
package net.alexandrade.mobile.screenplay.tasks.commons;

import net.alexandrade.mobile.screenplay.driver.AppiumDriver;
import net.alexandrade.mobile.screenplay.interactions.TapAction;
import net.alexandrade.mobile.screenplay.ui.CommonObjects;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

public class CommonTasks {
    private CommonTasks() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static Performable pressPhysicalBackKey() {
        return Task.where(
                "{0} press the physical back key",
                actor -> {
                    AppiumDriver.getDriver().navigate().back();
                });
    }

    public static Performable tapBackArrow() {
        return Task.where(
                "{0} tap the Back Arrow on Screeen", TapAction.on(CommonObjects.BUTTON_ARROW_BACK));
    }
}
