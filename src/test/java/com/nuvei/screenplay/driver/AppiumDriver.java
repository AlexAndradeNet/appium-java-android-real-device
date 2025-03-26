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
package com.nuvei.screenplay.driver;

import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.webdriver.WebDriverFacade;

/** Don't use singleton pattern for Appium driver */
public class AppiumDriver {
    private AppiumDriver() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    public static WebDriverFacade getDriver() {
        return (WebDriverFacade) Serenity.getDriver();
    }

    /** During Verifone screens the driver is detached from the app, so we need to re-attach it */
    public static void reAttachDriver() {
        var driver = getDriver();
        String appPackage = driver.getCapabilities().getCapability("appPackage").toString();
        String appActivity = driver.getCapabilities().getCapability("appActivity").toString();

        Map<String, Object> args = new HashMap<>();
        args.put("intent", appPackage + "/" + appActivity);
        driver.executeScript("mobile: startActivity", args);
    }
}
