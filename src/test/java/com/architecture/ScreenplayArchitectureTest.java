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
package com.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;

@AnalyzeClasses(packages = "com.nuvei.screenplay")
public class ScreenplayArchitectureTest {

    // 1) Packages must live under src/main/java, not under test:
    // @ArchTest
    static final ArchRule screenPlayArtifactsInMain =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAnyPackage(
                            "..screenplay.tasks..",
                            "..screenplay.interactions..",
                            "..screenplay.questions..",
                            "..screenplay.ui..",
                            "..screenplay.ability..")
                    .should()
                    .resideInAPackage("com.nuvei.screenplay..")
                    .because("All reusable Screenplay code belongs in production sources");

    // 2) Tasks may only depend on Interactions and Questions (and Abilities):
    // @ArchTest
    static final ArchRule tasksOnlyDependOnInteractionsAndQuestions =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("..screenplay.tasks..")
                    .should()
                    .onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "com.nuvei.screenplay.tasks..",
                            "com.nuvei.screenplay.interactions..",
                            "com.nuvei.screenplay.questions..",
                            "com.nuvei.screenplay.ability..",
                            "java..")
                    .because("Tasks orchestrate Interactions & Questions, nothing else");

    // 3) Interactions may only depend on UI and Ability (and standard libs):
    // @ArchTest
    static final ArchRule interactionsOnlyDependOnUiAndAbility =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("..screenplay.interactions..")
                    .should()
                    .onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "com.nuvei.screenplay.ui..",
                            "com.nuvei.screenplay.ability..",
                            "org.openqa.selenium..",
                            "java..")
                    .because("Interactions are low‐level UI actions");

    // 4) Step definitions / test runners may not depend on Tasks:
    // @ArchTest
    static final ArchRule stepsNotDependOnTasks =
            ArchRuleDefinition.noClasses()
                    .that()
                    .resideInAPackage("..stepdefinitions..")
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("..screenplay.tasks..")
                    .because("Glue code should only reference Tasks, not the other way around");

    // 5) Questions may only depend on UI (for reading state) and Ability:
    // @ArchTest
    static final ArchRule questionsOnlyDependOnUiAndAbility =
            ArchRuleDefinition.classes()
                    .that()
                    .resideInAPackage("..screenplay.questions..")
                    .should()
                    .onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "com.nuvei.screenplay.ui..", "com.nuvei.screenplay.ability..", "java..")
                    .because("Questions read state via Targets and driver Abilities");
}
