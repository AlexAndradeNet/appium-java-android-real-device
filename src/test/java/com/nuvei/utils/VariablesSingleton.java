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
package com.nuvei.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
/**
 * Singleton class to manage variables across executions This class is thread-safe and uses the Bill
 * Pugh Singleton Design Pattern.
 */
public class VariablesSingleton {
    private int numpadUsageCount = 0;

    private VariablesSingleton() {
        // Prevent instantiation
    }

    // Static inner class - only loaded when getInstance() is called
    private static class SingletonHelper {
        private static final VariablesSingleton INSTANCE = new VariablesSingleton();
    }

    public static VariablesSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
