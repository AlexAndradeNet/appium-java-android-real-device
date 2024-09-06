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

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;

public class AndroidObject {

    // Android Command Actions

    public void HideKeyboard(Actor actor) {
        androidDriver(actor).hideKeyboard();
    }

    public void SwipeToElement(Actor actor, String label) {
        androidDriver(actor)
                .findElement(
                        new AppiumBy.ByAndroidUIAutomator(
                                "new UiScrollable(new UiSelector()).scrollIntoView("
                                        + "new UiSelector().text(\""
                                        + label
                                        + "\"));"))
                .click();
    }

    public AndroidDriver getAndroidDriver(Actor actor) {
        return androidDriver(actor);
    }

    @SuppressWarnings("unchecked")
    private static AndroidDriver androidDriver(Actor actor) {
        return (AndroidDriver) ((WebDriverFacade) getDriver(actor)).getProxiedDriver();
    }

    private static WebDriverFacade getDriver(Actor actor) {
        return ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver());
    }

    public TouchAction withAction(Actor actor) {
        return new TouchAction(androidDriver(actor));
    }

    public void SwitchToFrame(Actor actor, int id) {
        androidDriver(actor).switchTo().frame(id);
    }
}
