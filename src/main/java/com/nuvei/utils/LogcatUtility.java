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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import net.serenitybdd.screenplay.Actor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;

public class LogcatUtility {
    private static final LoggerWrapper LOGGER = new LoggerWrapper(LogcatUtility.class);

    private Thread logThread;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final ConcurrentLinkedQueue<String> logData = new ConcurrentLinkedQueue<>();

    // --- public API -------------------------------------------------------------

    public synchronized void startLogcat(Actor actor) {
        if (isLogcatAlreadyRunning()) { // 1 decision
            return;
        }

        running.set(true);
        var driver = actor.usingAbilityTo(BrowseTheApp.class).driver();
        AtomicReference<List<LogEntry>> logEntries = new AtomicReference<>();

        logThread = new Thread(() -> runLogcatLoop(driver, logEntries));
        logThread.setDaemon(true); // JVM‑friendly
        logThread.start();

        LOGGER.info("Logcat capture started.");
    }

    // --- private helpers --------------------------------------------------------

    /** Guard clause to avoid starting a second capture thread. */
    private boolean isLogcatAlreadyRunning() {
        boolean alreadyRunning = running.get() || (logThread != null && logThread.isAlive());
        if (alreadyRunning) {
            LOGGER.warn("Logcat capture is already running.");
        }
        return alreadyRunning;
    }

    /** Main loop executed inside {@code logThread}. */
    private void runLogcatLoop(WebDriver driver, AtomicReference<List<LogEntry>> logEntries) {

        while (running.get()) { // 1 loop
            fetchLogEntries(driver, logEntries);
            persistNewEntries(logEntries);
            sleepOneSecond();
        }
    }

    /** Grabs the latest Logcat entries, swallowing any driver‑side exceptions. */
    private void fetchLogEntries(WebDriver driver, AtomicReference<List<LogEntry>> logEntries) {
        try {
            logEntries.set(driver.manage().logs().get("logcat").getAll());
        } catch (Exception ignored) {
            // Intentionally ignored
        }
    }

    /** Appends each fresh message to the shared queue. */
    private void persistNewEntries(AtomicReference<List<LogEntry>> logEntries) {
        List<LogEntry> entries = logEntries.get();
        if (entries != null && !entries.isEmpty()) { // 1 decision
            entries.forEach(e -> logData.add(e.getMessage()));
        }
    }

    /** Cooperative delay; also handles interruption cleanly. */
    private void sleepOneSecond() {
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            running.set(false);
            Thread.currentThread().interrupt();
        }
    }

    public synchronized String stopLogcat() {
        if (!running.get()) {
            LOGGER.warn("Logcat capture is not running.");
            return "";
        }

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
    }
}
