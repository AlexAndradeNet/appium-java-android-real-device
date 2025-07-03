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
package com.nuvei.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DashboardScreen {
    private DashboardScreen() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    private static final String BASE_SELECTOR = "//android.widget.TextView[@text='%s']";

    public static final Target SPINNER =
            Target.the("Spinner").located(By.xpath("//android.widget.ProgressBar"));

    public static final Target BUTTON_SALE_TRANSACTION =
            Target.the("Sale").located(By.xpath(BASE_SELECTOR.formatted("Sale")));

    public static final Target BUTTON_REFUND_TRANSACTION =
            Target.the("Refund").located(By.xpath(BASE_SELECTOR.formatted("Refund")));

    public static final Target BUTTON_MOTO_TRANSACTION =
            Target.the("Moto").located(By.xpath(BASE_SELECTOR.formatted("Moto")));

    public static final Target BUTTON_VOID_TRANSACTION =
            Target.the("Void").located(By.xpath(BASE_SELECTOR.formatted("Void")));

    public static final Target BUTTON_CRYPTO_TRANSACTION =
            Target.the("Crypto").located(By.xpath(BASE_SELECTOR.formatted("Crypto")));

    public static final Target BUTTON_BATCH_OR_SETTLE =
            Target.the("Batch or Settle").located(By.xpath(BASE_SELECTOR.formatted("Settle")));

    public static final Target BUTTON_SETTINGS =
            Target.the("Settings").located(By.xpath(BASE_SELECTOR.formatted("Settings")));

    public static final Target BUTTON_HELP_DESK =
            Target.the("Help Desk").located(By.xpath(BASE_SELECTOR.formatted("Help")));

    public static final Target BUTTON_TID =
            Target.the("TID info").located(By.xpath(BASE_SELECTOR.formatted("TID")));

    public static final Target FRAME_DASHBOARD =
            Target.the("Button Previous")
                    .located(
                            By.xpath(
                                    "//androidx.recyclerview.widget.RecyclerView[ends-with(@resource-id,"
                                        + " 'dashboardModulesRecyclerView')]"));

    public static final Target BUTTON_PREVIOUS =
            Target.the("Button Previous").located(By.xpath(BASE_SELECTOR.formatted("Previous")));

    public static final Target BUTTON_NEXT =
            Target.the("Button Previous").located(By.xpath(BASE_SELECTOR.formatted("Next")));

    public static final Target LABEL_SAF =
            Target.the("Button SAF Enabled")
                    .located(By.xpath(BASE_SELECTOR.formatted("SAF Enabled")));

    public static final Target BUTTON_SAF =
            Target.the("Button SAF").located(By.xpath(BASE_SELECTOR.formatted("SAF")));
}
