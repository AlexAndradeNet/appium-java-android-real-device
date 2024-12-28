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

public class AppiumDriverSingleton {
    private AppiumDriverSingleton() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static class SingletonHelper {
        private static final WebDriverFacade INSTANCE = (WebDriverFacade) Serenity.getDriver();
    }

    public static WebDriverFacade getDriver() {
        return SingletonHelper.INSTANCE;
    }
}
