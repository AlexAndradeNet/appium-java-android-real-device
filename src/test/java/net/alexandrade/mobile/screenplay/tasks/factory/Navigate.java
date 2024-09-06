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
package net.alexandrade.mobile.screenplay.tasks.factory;

import net.alexandrade.mobile.screenplay.tasks.AccessTask;
import net.alexandrade.mobile.screenplay.tasks.ShowMenuPanelTask;
import net.serenitybdd.screenplay.Task;

public class Navigate {

    public static Task menuPanel() {
        return new ShowMenuPanelTask();
    }

    public static Task menuWithLabel(String label) {
        return new AccessTask(label);
    }
}
