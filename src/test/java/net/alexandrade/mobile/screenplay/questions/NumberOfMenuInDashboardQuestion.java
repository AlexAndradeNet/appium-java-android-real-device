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

import net.alexandrade.mobile.screenplay.ui.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class NumberOfMenuInDashboardQuestion implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return DashboardPage.MENU_ITEMS.resolveAllFor(actor).size();
    }
}
