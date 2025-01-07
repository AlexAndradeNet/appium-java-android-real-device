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
package net.alexandrade.mobile.screenplay.interactions;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;

/** The Nuvei app does not support the click action, so we need to use the tap action instead. */
public class ClickAction extends TapAction {

    protected ClickAction(Target target, WebElement webElement, int duration) {
        super(target, webElement, duration);
    }
}
