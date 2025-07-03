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

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Map;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

/** Minimal TestRail REST client for publishing results and attachments. */
public class TestRailClient {
    private final String baseUrl;
    private final String authHeader;
    private final int runId;
    private final ObjectMapper mapper = new ObjectMapper();

    public TestRailClient(String url, String user, String pass, int runId) {
        this.baseUrl = url + "/index.php?/api/v2/";
        this.authHeader =
                "Basic " + Base64.getEncoder().encodeToString((user + ":" + pass).getBytes());
        this.runId = runId;
    }

    public void publishResult(int caseId, int statusId, String comment) throws IOException {
        String endpoint = baseUrl + "add_result_for_case/" + runId + "/" + caseId;
        Map<String, Object> body =
                Map.of(
                        "status_id", statusId,
                        "comment", comment);
        HttpPost post = new HttpPost(endpoint);
        post.setHeader("Authorization", authHeader);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(
                new StringEntity(mapper.writeValueAsString(body), ContentType.APPLICATION_JSON));
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            int code = client.execute(post, HttpResponse::getCode);
            if (code < 200 || code > 299) {
                throw new RuntimeException("TestRail API responded: " + code);
            }
        }
    }

    public void addAttachmentToCase(int caseId, Path filePath) throws IOException {
        String endpoint = baseUrl + "add_attachment_to_case/" + caseId;
        HttpPost post = new HttpPost(endpoint);
        post.setHeader("Authorization", authHeader);

        // build multipart/form-data
        MultipartEntityBuilder builder =
                MultipartEntityBuilder.create()
                        .addBinaryBody(
                                "attachment",
                                Files.readAllBytes(filePath),
                                ContentType.DEFAULT_BINARY,
                                filePath.getFileName().toString());
        post.setEntity(builder.build());

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            int code = client.execute(post, HttpResponse::getCode);
            if (code < 200 || code > 299) {
                throw new RuntimeException("Attachment upload failed: " + code);
            }
        }
    }
}
