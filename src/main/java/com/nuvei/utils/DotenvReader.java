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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class DotenvReader {

    private static final String ENV_FILE = ".env";
    private static final LoggerWrapper logger = new LoggerWrapper(DotenvReader.class);

    private DotenvReader() {
        // Private constructor to prevent instantiation
    }

    public static void loadEnvFile() {
        Path envPath = Paths.get(System.getProperty("user.dir")).resolve(ENV_FILE);
        logger.info("Setting up Cucumber environment before all tests.");
        if (!Files.exists(envPath)) {
            return;
        }

        List<String> lines = null;
        try {
            lines = Files.readAllLines(envPath);
        } catch (IOException e) {
            logger.error(e.getMessage());
            return;
        }

        for (String line : lines) {
            String trimmed = StringUtils.isBlank(line) ? "" : line.trim();
            if (StringUtils.isBlank(trimmed) || trimmed.startsWith("#") || !trimmed.contains("=")) {
                continue;
            }
            int idx = trimmed.indexOf('=');
            String key = trimmed.substring(0, idx).trim();
            String val = trimmed.substring(idx + 1).trim();
            // inject as a System property so System.getProperty(key) returns it
            System.setProperty(key, val);
        }
    }
}
