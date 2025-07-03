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

import io.cucumber.java.Scenario;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SerenityReportHelper {

    private static final LoggerWrapper logger = new LoggerWrapper(SerenityReportHelper.class);

    private SerenityReportHelper() {
        throw new IllegalStateException("Utility class - cannot be instantiated");
    }

    /**
     * Saves data into the Serenity report.
     *
     * @param content the content.
     * @param title the title for the report.
     */
    public static void saveData(String title, String content) {
        logger.info("Receipt: \n" + content);

        Serenity.recordReportData().withTitle(title).andContents(content);
    }

    /**
     * Takes a screenshot and saves it both to the file system and the Serenity report.
     *
     * @param scenario The Cucumber scenario context for naming the screenshot file.
     */
    public static void saveScreenshot(Scenario scenario) {
        logger.info("####################### Scenario failed: " + scenario.getStatus());

        WebDriver webDriver = Serenity.getWebdriverManager().getCurrentDriver();
        if (!(webDriver instanceof TakesScreenshot)) {
            logger.error("WebDriver does not support screenshots.");
            return;
        }

        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        if (screenshot == null) {
            logger.error("Screenshot capture failed: returned null.");
            return;
        }

        File destination = new File("target/screenshots/" + scenario.getName() + ".png");

        try {
            Files.createDirectories(destination.getParentFile().toPath());
            Files.copy(
                    screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            logger.debug("Screenshot saved: " + destination.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Failed to save screenshot: " + e.getMessage());
            return;
        }

        try {
            Serenity.recordReportData()
                    .asEvidence()
                    .withTitle("Screenshot")
                    .downloadable()
                    .fromFile(destination.toPath());
        } catch (IOException e) {
            logger.error("Failed to attach screenshot to report: " + e.getMessage());
        }
    }
}
