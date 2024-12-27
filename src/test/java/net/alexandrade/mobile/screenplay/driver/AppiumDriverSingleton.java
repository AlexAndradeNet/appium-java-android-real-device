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
package net.alexandrade.mobile.screenplay.driver;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.webdriver.WebDriverFacade;

public final class AppiumDriverSingleton {
    private static AppiumDriverSingleton instance;
    private static final WebDriverFacade appiumDriver = (WebDriverFacade) Serenity.getDriver();

    public static AppiumDriverSingleton getInstance() {
        if (instance == null) {
            instance = new AppiumDriverSingleton();
        }
        return instance;
    }

    public WebDriverFacade getDriver() {
        return appiumDriver;
    }
}
