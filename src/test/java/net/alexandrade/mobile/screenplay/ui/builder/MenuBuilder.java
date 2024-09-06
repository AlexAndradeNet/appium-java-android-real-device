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
package net.alexandrade.mobile.screenplay.ui.builder;

import java.util.ArrayList;
import java.util.List;
import net.alexandrade.mobile.screenplay.ui.DashboardPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;

public class MenuBuilder {
    public static List<String> getMenuListInUI(Actor actor) {
        List<String> allMenu = new ArrayList<>();
        List<WebElementFacade> menuItems = DashboardPage.MENU_ITEMS.resolveAllFor(actor);

        for (WebElementFacade menuItem : menuItems) {
            String menuLabel = menuItem.getText();
            allMenu.add(menuLabel);
        }
        return allMenu;
    }
}
