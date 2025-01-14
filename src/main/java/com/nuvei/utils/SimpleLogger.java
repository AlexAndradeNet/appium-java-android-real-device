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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogger {

    private static final String DEFAULT_LOG_FORMAT =
            "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36}: %n%msg%n";

    private final Logger logger;

    public SimpleLogger(Class<?> clazz) {
        this(clazz, DEFAULT_LOG_FORMAT);
    }

    public SimpleLogger(Class<?> clazz, String logFormat) {
        // Configure slf4j-simple with desired log format
        System.setProperty(
                "org.slf4j.simpleLogger.logFile",
                "stdout"); // Change "stdout" to filename for file logging
        System.setProperty("org.slf4j.simpleLogger.showThreadName", "true");
        System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", logFormat);

        logger = LoggerFactory.getLogger(clazz);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
