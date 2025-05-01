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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.platform.commons.util.StringUtils;

public class ReceiptUtility {
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
