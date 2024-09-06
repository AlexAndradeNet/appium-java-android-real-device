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

import net.alexandrade.mobile.screenplay.ui.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.converters.converters.StringConverter;

public class SelectedAccountQuestion implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        new StringConverter().convert("true");
        return !AccountPage.SELECTED_ACCOUNT.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> disappear() {
        return new SelectedAccountQuestion();
    }
}
