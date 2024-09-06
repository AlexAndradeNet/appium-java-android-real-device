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

import net.serenitybdd.screenplay.targets.Target;

public class HomePage {
    public static Target saleTransactionButton =
            Target.the("Apps list Button").locatedBy("(//android.widget.TextView[1])[1]");
}
