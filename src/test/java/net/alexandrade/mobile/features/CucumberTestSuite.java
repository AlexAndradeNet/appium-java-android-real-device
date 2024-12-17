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

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/features")
@ConfigurationParameter(key = "cucumber.glue", value = "net.alexandrade.mobile.features.steps")
@ConfigurationParameter(
        key = "cucumber.plugin",
        value =
                "io.cucumber.core.plugin.SerenityReporterParallel,pretty,timeline:build/test-results/timeline")
@ExcludeTags({"@ignore", "@wip", "@manual", "@skip"})
public class CucumberTestSuite {}
