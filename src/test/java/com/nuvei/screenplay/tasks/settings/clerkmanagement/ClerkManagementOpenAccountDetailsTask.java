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

import com.nuvei.screenplay.interactions.ClickAction;
import com.nuvei.screenplay.tasks.commons.ScrollUntilVisibleTask;
import com.nuvei.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;

public class ClerkManagementOpenAccountDetailsTask implements Task {

    private final String clerkId;

    public ClerkManagementOpenAccountDetailsTask(String clerkId) {
        this.clerkId = clerkId;
    }

    @Override
    @Step("{0} opens account details for clerk ID #clerkId")
    public <T extends Actor> void performAs(T actor) {
        Target target = MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(clerkId);
        actor.attemptsTo(ScrollUntilVisibleTask.forObject(target), ClickAction.on(target));
    }

    public static ClerkManagementOpenAccountDetailsTask withId(String clerkId) {
        return new ClerkManagementOpenAccountDetailsTask(clerkId);
    }
}
