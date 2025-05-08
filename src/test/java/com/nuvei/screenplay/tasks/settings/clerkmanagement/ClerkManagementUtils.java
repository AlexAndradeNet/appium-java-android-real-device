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

import com.nuvei.screenplay.ui.settings.clerkmanagement.ChangeClerkRoleScreen;
import net.serenitybdd.screenplay.targets.Target;

public class ClerkManagementUtils {
    private ClerkManagementUtils() {}

    public static Target getTargetForRole(String role) {
        return switch (role) {
            case "Admin" -> ChangeClerkRoleScreen.BUTTON_ROLE_ADMIN;
            case "Manager" -> ChangeClerkRoleScreen.BUTTON_ROLE_MANAGER;
            case "Employee" -> ChangeClerkRoleScreen.BUTTON_ROLE_EMPLOYEE;
            default -> throw new IllegalArgumentException("Invalid role: " + role);
        };
    }
}
