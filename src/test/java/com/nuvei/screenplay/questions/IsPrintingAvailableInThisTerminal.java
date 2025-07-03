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
package com.nuvei.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class IsPrintingAvailableInThisTerminal implements Question<Boolean> {

    public static IsPrintingAvailableInThisTerminal now() {
        return new IsPrintingAvailableInThisTerminal();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // “SAF is available” if the terminal is neither M-Series nor P-Series.
        boolean isMSeries =
                Boolean.TRUE.equals(actor.asksFor(WhatSeriesIsThisTerminalQuestion.isMSeries()));
        boolean isPSeries =
                Boolean.TRUE.equals(actor.asksFor(WhatSeriesIsThisTerminalQuestion.isPSeries()));
        return !(isMSeries || isPSeries);
    }

    @Override
    public String getSubject() {
        return "check if printing is available in this terminal";
    }
}
