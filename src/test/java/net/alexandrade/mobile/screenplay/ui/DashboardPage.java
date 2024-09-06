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
package net.alexandrade.mobile.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public class DashboardPage {

    public static final Target MAIN_TITLE =
            Target.the("Open Demo Account")
                    .located(By.id("net.metaquotes.metatrader5:id/main_title"));
    public static final Target MAIN_NAVIGATE_BUTTON =
            Target.the("hamburger button")
                    .located(By.id("net.metaquotes.metatrader5:id/actionbar_back_icon"));
    public static final Target MENU_ITEMS =
            Target.the("sub menu").located(By.id("net.metaquotes.metatrader5:id/drawer_name"));

    public static final String MENU_LABEL = "net.metaquotes.metatrader5:id/drawer_name";

    public static Target getMenuByLabel(String label) {
        return Target.the("menu item: " + label)
                .located(By.xpath(String.format("//android.widget.TextView[@text='%s']", label)));
    }
}
