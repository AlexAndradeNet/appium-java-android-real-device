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

import com.nuvei.screenplay.ability.BrowseTheApp;
import com.nuvei.screenplay.interactions.WaitAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import net.serenitybdd.screenplay.Actor;
import org.junit.platform.commons.util.StringUtils;
import org.openqa.selenium.logging.LogEntry;

public class LogcatUtility {
    private static final SimpleLogger LOGGER = new SimpleLogger(LogcatUtility.class);

    private Thread logThread;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final ConcurrentLinkedQueue<String> logData = new ConcurrentLinkedQueue<>();

    public synchronized void startLogcat(Actor actor) {
        var driver = BrowseTheApp.driverFor(actor);

        if (!running.get() && (logThread == null || !logThread.isAlive())) {
            running.set(true);

            AtomicReference<List<LogEntry>> logEntries = new AtomicReference<>();

            logThread =
                    new Thread(
                            () -> {
                                while (running.get()) {

                                    try {
                                        logEntries.set(
                                                driver.manage().logs().get("logcat").getAll());
                                    } catch (Exception ignored) {
                                        // Ignored
                                    }

                                    if (!logEntries.get().isEmpty()) {
                                        for (LogEntry entry : logEntries.get()) {
                                            logData.add(entry.getMessage());
                                        }
                                    }

                                    WaitAction.forSpecificTime(1); // Poll every second
                                }
                            });

            logThread.setDaemon(true); // Ensures the thread stops when JVM exits
            logThread.start();
            LOGGER.info("Logcat capture started.");
        } else {
            LOGGER.warn("Logcat capture is already running.");
        }
    }

    public synchronized String stopLogcat() {
        if (running.get()) {
            running.set(false);
            if (logThread != null) {
                logThread.interrupt();
                try {
                    logThread.join(2000); // Wait for thread to stop
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve interrupt status
                }
            }
            LOGGER.info("Logcat capture stopped.");

            // Ensure logs are properly retrieved before clearing
            List<String> collectedLogs;
            synchronized (logData) {
                collectedLogs = new ArrayList<>(logData);
                logData.clear(); // Reset for next session
            }

            String logs = String.join("\n", collectedLogs);

            if (logs.contains("read: unexpected EOF!")) {
                LOGGER.warn("Logcat capture was interrupted unexpectedly.");
                return "";
            } else {
                return logs;
            }
        } else {
            LOGGER.warn("Logcat capture is not running.");
            return "";
        }
    }

    private static String extractReceipt(int receiptNumber, String logcatContent) {
        if (StringUtils.isBlank(logcatContent)) {
            return "";
        }

        List<String> lines = new ArrayList<>(Arrays.asList(logcatContent.split("\n")));
        StringBuilder extractedContent = new StringBuilder();

        int doctypeCount = 0;
        boolean isCapturing = false;

        for (String line : lines) {
            // Extract the sixth column (log message)
            String[] columns = line.split("\\s+", 6);
            String logMessage = columns.length >= 6 ? columns[5] : "";

            if (logMessage.contains("PrintReceiptPre: <html>")) { // Header
                doctypeCount++;
                if (doctypeCount == receiptNumber) {
                    isCapturing = true; // Start capturing after the second occurrence
                }
            }

            if (isCapturing) {
                extractedContent.append(logMessage).append("\n");
            }

            if (isCapturing && logMessage.contains("</html>")) {
                break; // Stop at the next occurrence of `</html>`
            }
        }

        String receipt = extractedContent.toString().trim();
        receipt = receipt.replace("PrintReceiptPre: ", ""); // Remove the prefix
        receipt = removeHtmlTags(receipt);
        receipt = removeEmptyLines(receipt);
        receipt = receipt.replaceAll("  ", " "); // Remove all whitespace
        return receipt;
    }

    public synchronized String getCustomerReceipt(String logcatContent) {
        return extractReceipt(2, logcatContent);
    }

    public static synchronized String getMerchantReceipt(String logcatContent) {
        return extractReceipt(1, logcatContent);
    }

    private static String removeEmptyLines(String input) {
        return String.join(
                "\n",
                Arrays.stream(input.split("\n"))
                        .filter(line -> !line.trim().isEmpty()) // Remove empty and whitespace-only
                        // lines
                        .toArray(String[]::new));
    }

    private static String removeHtmlTags(String input) {
        input = input.replaceAll("(?s)<style.*?>.*?</style>", ""); // Removes <style> and content
        return input.replaceAll("<[^>]+>", ""); // Removes all HTML tags
    }
}
