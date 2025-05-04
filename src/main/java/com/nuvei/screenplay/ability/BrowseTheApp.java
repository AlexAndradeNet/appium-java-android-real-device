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
package com.nuvei.screenplay.ability;

import java.util.HashMap;
import java.util.Map;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;
import net.thucydides.core.webdriver.WebDriverFacade;

/** Lets an actor drive the Nuvei Android application with Appium. */
public class BrowseTheApp implements Ability {

    private final WebDriverFacade driver;

    private BrowseTheApp(WebDriverFacade driver) {
        this.driver = driver;
    }

    public static BrowseTheApp with(WebDriverFacade driver) {
        return new BrowseTheApp(driver);
    }

    public WebDriverFacade driver() {
        return driver;
    }

    /** During Verifone screens the driver is detached from the app, so we need to re-attach it */
    public static void reAttachDriver(Actor actor) {
        var driver = actor.usingAbilityTo(BrowseTheApp.class).driver();

        String appPackage = driver.getCapabilities().getCapability("appPackage").toString();
        String appActivity = driver.getCapabilities().getCapability("appActivity").toString();

        Map<String, Object> args = new HashMap<>();
        args.put("intent", appPackage + "/" + appActivity);
        driver.executeScript("mobile: startActivity", args);
    }
}
