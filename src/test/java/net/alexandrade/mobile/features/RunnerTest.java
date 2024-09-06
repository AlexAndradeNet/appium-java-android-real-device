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
package net.alexandrade.mobile.features;

import static org.junit.Assert.assertTrue;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;

@TestMethodOrder(MethodOrderer.Random.class)
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "net.alexandrade.mobile.features.steps",
        plugin = "pretty",
        tags = "not @ignore")
class RunnerTest {
    private RunnerTest() {
        super();
    }

    @Test
    void test() {
        assertTrue(true);
    }

    /*
    DO NOT PUT OTHER METHODS HERE
     */
}
