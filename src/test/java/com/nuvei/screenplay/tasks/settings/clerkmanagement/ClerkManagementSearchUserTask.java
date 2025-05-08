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
package com.nuvei.screenplay.tasks.settings.clerkmanagement;

import com.nuvei.screenplay.interactions.EnterAction;
import com.nuvei.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ClerkManagementSearchUserTask implements Task {

    private final String clerkId;

    public ClerkManagementSearchUserTask(String clerkId) {
        this.clerkId = clerkId;
    }

    @Override
    @Step("{0} searches for clerk ID #clerkId")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(EnterAction.into(MainClerkManagementScreen.TEXTBOX_SEARCH, clerkId));
    }

    public static ClerkManagementSearchUserTask withId(String clerkId) {
        return new ClerkManagementSearchUserTask(clerkId);
    }
}
