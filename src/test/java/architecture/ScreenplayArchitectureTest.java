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
package architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

class ScreenplayArchitectureTest {

    // @Test
    void tasks_must_not_have_actor_parameters() {
        JavaClasses classes = new ClassFileImporter().importPackages("com.nuvei");

        ArchRule rule =
                methods()
                        .that()
                        .areDeclaredInClassesThat()
                        .implement(Task.class)
                        .should()
                        .notHaveRawParameterTypes(Actor.class)
                        .because(
                                "Tasks should encapsulate *what* is done; "
                                        + "the framework provides the Actor at runtime");

        rule.check(classes);
    }
}
