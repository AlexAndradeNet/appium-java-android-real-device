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

import net.alexandrade.mobile.screenplay.tasks.AccountProfileTask;
import net.alexandrade.mobile.screenplay.tasks.DeleteAccountTask;
import net.serenitybdd.screenplay.Task;

public class GoTo {
    public static Task accountProfile() {
        return new AccountProfileTask();
    }

    public static Task guestProfile() {
        return new DeleteAccountTask();
    }
}
