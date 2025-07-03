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
package com.nuvei.utils.testrail;

import com.nuvei.utils.LoggerWrapper;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TestRailCucumberPlugin implements ConcurrentEventListener {
    private final TestRailClient client;
    // 👇 Define your fixed attachment path here:
    private static final Path ATTACHMENT_PATH = Paths.get("target/screenshots/failure.png");
    private static final LoggerWrapper logger = new LoggerWrapper(TestRailCucumberPlugin.class);

    public TestRailCucumberPlugin() {
        String url = System.getProperty("testrail.url");
        String user = System.getProperty("testrail.username");
        String pass = System.getProperty("testrail.password");
        int runId = Integer.parseInt(System.getProperty("testrail.runId"));
        client = new TestRailClient(url, user, pass, runId);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
    }

    private void onTestCaseFinished(TestCaseFinished event) {
        // 1) Get TestRail case IDs from scenario tags:
        List<Integer> caseIds =
                event.getTestCase().getTags().stream()
                        .filter(name -> name.startsWith("TestRailCases:"))
                        .map(name -> name.substring("TestRailCases:".length()))
                        .map(Integer::parseInt)
                        .toList();

        if (caseIds.isEmpty()) {
            return;
        }

        // 2) Publish pass/fail
        boolean passed = event.getResult().getStatus().is(Status.PASSED);
        int statusId = passed ? 1 : 5;
        String comment = "Automated Cucumber run: " + (passed ? "PASSED" : "FAILED");

        for (int caseId : caseIds) {
            try {
                client.publishResult(caseId, statusId, comment);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

        // 3) On failure, upload the fixed-path attachment
        if (!passed) {
            for (int caseId : caseIds) {
                try {
                    client.addAttachmentToCase(caseId, ATTACHMENT_PATH);
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }
}
