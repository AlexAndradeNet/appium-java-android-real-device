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
package net.alexandrade.mobile.screenplay.questions.factory;

import java.util.List;
import net.alexandrade.mobile.screenplay.questions.NumberOfMenuInDashboardQuestion;
import net.alexandrade.mobile.screenplay.questions.PresentMenusInDashboardQuestion;
import net.serenitybdd.screenplay.Question;

public class MenuBarWebUI {
    public static Question<Integer> numberOfItems() {
        return new NumberOfMenuInDashboardQuestion();
    }

    public static Question<List<String>> displayedWithStrictOrder() {
        return new PresentMenusInDashboardQuestion();
    }
}
