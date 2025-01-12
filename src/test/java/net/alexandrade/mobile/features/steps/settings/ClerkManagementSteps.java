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
package net.alexandrade.mobile.features.steps.settings;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.questions.ElementListQuestion;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.ClerkManagementTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.alexandrade.mobile.screenplay.ui.NumericScreen;
import net.alexandrade.mobile.screenplay.ui.settings.MainSettingsScreen;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class ClerkManagementSteps {

    private static final String CLERK_ID = "clerkId";
    private static final String CLERK_PASSWORD = "clerkPassword";
    private static final String SCREEN_TITLE = "CLERK MANAGEMENT";

    @Given("{actor}, with ID {word} and Password {word}, is managing clerks,")
    public void isManagingClerks(Actor actor, String clerkId, String password) {
        managesClerks(actor, clerkId, password);
    }

    private void managesClerks(Actor actor, String clerkId, String clerkPassword) {
        actor.attemptsTo(
                MainSettingsTasks.openClerkManagementScreen(actor),
                LoginAsTasks.fillClerkID(actor, clerkId),
                LoginAsTasks.fillPassword(actor, clerkPassword));

        actor.remember( // Save the clerk ID for later use
                CLERK_ID, clerkId);
        actor.remember( // Save the clerk password for later use
                CLERK_PASSWORD, clerkPassword);
    }

    @When("he/she lists the clerks,")
    public void listsTheClerks() {
        OnStage.theActorInTheSpotlight()
                .remember( // Save the list of clerks for later use
                        "clerksList",
                        ElementListQuestion.listOfValues(
                                MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS));
    }

    @When("he changes {word}, with ID {word}, role from {word} to {word},")
    public void updatesClerkRoleFromEmployeeToManager(
            String alias, String clerkId, String oldRole, String newRole) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.changeRole(actor, newRole),
                CommonTasks.returnToMainScreen(actor));
    }

    @When("he search for the Clerk ID {word},")
    public void searchForTheClerkID(String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ClerkManagementTasks.searchForClerkId(actor, clerkId));
    }

    @When("he requests his account details, which is {word},")
    public void requestsAnAccountDetails(String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId));
    }

    @When("he requests another clerk's account details \\(ex: ID {word}),")
    public void requestsAnotherClerkSAccountDetails(String clerkID) {
        requestsAnAccountDetails(clerkID);
    }

    @When("he, with ID {word}, attempts to change his password to the current password {word},")
    public void attemptsToChangeHisPasswordToTheCurrentPassword(
            String clerkId, String currentPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.changePassword(actor, currentPassword, currentPassword));
    }

    @When(
            "he, with ID {word}, attempts to change his password to {word}, failing the"
                    + " verification by entering {word},")
    public void attemptsToChangeHisPasswordButFailsTheVerification(
            String clerkId, String newPassword, String wrongConfirmationPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.changePassword(actor, newPassword, wrongConfirmationPassword));
    }

    @When("he, with ID {word}, attempts to change his password to {word},")
    public void attemptsToChangeHisPasswordForAValidNewPassword(
            String clerkId, String newPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.changePassword(actor, newPassword, newPassword));
    }

    @When("he attempts to create a new clerk with his own Clerk ID {word},")
    public void attemptsToCreateANewClerkWithHisOwnClerkID(String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.addANewUser(actor, true, clerkId, "Employee", "111111"));
    }

    @When("he attempts to update {word} Clerk ID from {word} to {word},")
    public void attemptsToUpdateClerkIDToAValidValue(
            String alias, String currentClerkId, String newClerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, currentClerkId),
                ClerkManagementTasks.changeClerkId(actor, newClerkId));
    }

    @When("he attempts to update {word} \\(ID {word}) password to {word},")
    public void attemptsToUpdatePasswordToAValidValue(
            String alias, String clerkId, String newPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.changePassword(actor, newPassword, newPassword));
    }

    @When("he attempts to delete {word} account \\(ID {word}) but regrets it,")
    public void attemptsToDeleteAClerkButRegretsIt(String alias, String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.deleteClerk(actor, true, false),
                CommonTasks.returnToMainScreen(actor));
    }

    @Then(
            "he should see that the clerks list is sorted numerically \\({string}) instead of"
                    + " alphabetically.")
    public void verifyClerkList(String clerksList) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(Ensure.that(actor.recall("clerksList").toString()).isEqualTo(clerksList));
    }

    @Then("he should see that the clerk {word}, with ID {word}, is listed as a {word}.")
    public void shouldSeeThatTheClerkIsListedAsA(
            String clerkName, String clerkId, String clerkRole) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see just one result in the list",
                                        ElementListQuestion.quantityOf(
                                                MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS))
                                .isEqualTo(1),
                        Ensure.that(
                                        "Should see that the Clerk ID '%s' is in the list"
                                                .formatted(clerkName),
                                        TextQuestion.of(
                                                MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE
                                                        .of(clerkId)))
                                .isEqualToIgnoringCase(clerkRole));
    }

    @Then("he should that the list is empty.")
    public void shouldThatTheListIsEmpty() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see that the list is empty",
                                        ElementListQuestion.quantityOf(
                                                MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS))
                                .isEqualTo(0));
    }

    @Then("he should see the options to change password, Clerk ID, and Role.")
    public void shouldHaveTheOptionsToChangePasswordClerkIDAndRole() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see the 'Change Password' button",
                                        VisibilityQuestion.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_PASSWORD))
                                .isTrue(),
                        Ensure.that(
                                        "Should see the 'Change ID' button",
                                        VisibilityQuestion.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_CLERK_ID))
                                .isTrue(),
                        Ensure.that(
                                        "Should see the 'Role Change' button",
                                        VisibilityQuestion.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE))
                                .isTrue());
    }

    @Then(
            "he should only have the option to change his password, and no options for Clerk ID or"
                    + " Role.")
    public void shouldOnlyHaveTheOptionToChangeHisPasswordAndNoOptionsForClerkIDOrRole() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                Ensure.that(
                                "Should see the screen title",
                                VisibilityQuestion.isPresent(ViewClerkScreen.TITLE))
                        .isTrue(),
                Ensure.that(
                                "Should see the 'Clerk ID; label",
                                TextQuestion.of(ViewClerkScreen.LABEL_CLERK_ID))
                        .isEqualToIgnoringCase("#" + actor.recall(CLERK_ID).toString()),
                Ensure.that(
                                "Should see the 'Clerk Role' label",
                                TextQuestion.of(ViewClerkScreen.LABEL_CLERK_ROLE))
                        .isEqualToIgnoringCase("Admin"),
                Ensure.that(
                                "Should see the 'Change Password' button",
                                VisibilityQuestion.isPresent(
                                        ViewClerkScreen.BUTTON_CHANGE_PASSWORD))
                        .isTrue(),
                Ensure.that(
                                "Should see the 'Change ID' button",
                                VisibilityQuestion.isPresent(
                                        ViewClerkScreen.BUTTON_CHANGE_CLERK_ID))
                        .isFalse(),
                Ensure.that(
                                "Should see the 'Role Change' button",
                                VisibilityQuestion.isPresent(
                                        ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE))
                        .isFalse());
    }

    @And("he should still be able to use his ID {word} and old password {word} to manage clerks.")
    public void shouldStillBeAbleToUseHisOldPasswordToManageClerks(
            String clerkId, String oldPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(CommonTasks.returnToMainScreen(actor));
        managesClerks(actor, clerkId, oldPassword);
        checkAbilityToAddNewClerks(true);
    }

    @Then(
            "he, with ID {word}, should be able to use his new password {word} to revert it to"
                    + " {word}.")
    public void shouldBeAbleToUseHisNewPasswordToManageClerks(
            String clerkId, String currentPassword, String newPassword) {
        testNewPasswordAndChangeIt(clerkId, currentPassword, newPassword);
    }

    private void testNewPasswordAndChangeIt(
            String clerkId, String currentPassword, String newPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(CommonTasks.returnToMainScreen(actor));
        managesClerks(actor, clerkId, currentPassword);

        // Reverts previous password
        requestsAnAccountDetails(clerkId);
        actor.attemptsTo(ClerkManagementTasks.changePassword(actor, newPassword, newPassword));
    }

    @Then("he should receive the error message {string}.")
    public void shouldReceiveTheErrorMessage(String messageError) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.validateAndDismissPopupAlertWithOkButton(
                        actor, "Alert Message", messageError));
    }

    @Then("he should see {word} new Clerk ID is {word} in the clerks list.")
    public void shouldSeeNewClerkIDInTheClerksList(String alias, String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();

        WebElement clerkElement =
                MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE.of(clerkId).resolveFor(actor);

        actor.attemptsTo(
                CommonTasks.navigateMenuUntilElementIsVisible(actor, clerkElement),
                Ensure.that(
                                "Should see that the new ID '%s' for %s is present"
                                        .formatted(clerkId, alias),
                                VisibilityQuestion.isPresent(clerkElement))
                        .isTrue());
    }

    @Then("he should still see {word} account \\(ID {word}) in the clerks list.")
    public void shouldSeeTheClerkStillInTheClerksList(String alias, String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();

        reLogin(actor);

        shouldSeeNewClerkIDInTheClerksList(alias, clerkId);
    }

    @When("he attempts to add multiple clerks with the following data:")
    public void heAddsANewClerkWithAliasIDRoleAndPassword(@NotNull DataTable dataTable) {
        Actor actor = OnStage.theActorInTheSpotlight();

        dataTable.asLists().stream()
                .skip(1) // Skip the header row
                .forEach(
                        row -> {
                            String clerkId = row.get(1);
                            String role = row.get(2);
                            String password = row.get(3);

                            actor.attemptsTo(
                                    ClerkManagementTasks.addANewUser(
                                            actor, false, clerkId, role, password),
                                    ClerkManagementTasks
                                            .dismissSuccessConfirmationAfterAddingANewUser(actor));
                        });

        actor.remember("clerkListDataTable", dataTable);
    }

    @Then("he should see the each new clerk was created correctly.")
    public void heShouldSeeTheNewClerkIsListedAsWithTheRole() {
        Actor actor = OnStage.theActorInTheSpotlight();
        DataTable dataTable = actor.recall("clerkListDataTable");

        dataTable.asLists().stream()
                .skip(1) // Skip the header row
                .forEach(
                        row -> {
                            String clerkId = row.get(1);
                            String role = row.get(2);

                            WebElement clerkElement =
                                    MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE
                                            .of(clerkId)
                                            .resolveFor(actor);

                            actor.attemptsTo(
                                    CommonTasks.navigateMenuUntilElementIsVisible(
                                            actor, clerkElement),
                                    Ensure.that(
                                                    "Should see that the role for Clerk ID %s is '%s'"
                                                            .formatted(clerkId, role),
                                                    TextQuestion.of(clerkElement))
                                            .isEqualToIgnoringCase(role));
                        });
    }

    @Then(
            "he/she should see the list as a Manager, meaning he/she can see his/her account and"
                    + " all the Employees' roles \\({string}),")
    public void shouldSeeTheListAsAManagerMeaningHeCanSeeHisAccountAllTheEmployeesRoles(
            String clerkList) {
        verifyClerkList(clerkList);
    }

    @And("he/she should have the option to add new clerks.")
    public void hasTheOptionToAddNewClerks() {
        checkAbilityToAddNewClerks(true);
    }

    private static void checkAbilityToAddNewClerks(boolean isPresent) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should see the 'Add New User' button",
                                        VisibilityQuestion.isPresent(
                                                MainClerkManagementScreen.BUTTON_ADD_NEW_USER))
                                .isEqualTo(isPresent));
    }

    @Then(
            "she/he should see a list as an Employee, meaning she/he can see only her/his account,"
                    + " which is {word},")
    public void sheShouldSeeAListAsAEmployeeMeaningSheCanSeeOnlyHerAccountWhichIs(String clerkId) {
        verifyClerkList(clerkId);
    }

    @And("she/he should have no option to add new clerks.")
    public void sheHasNoOptionToAddNewClerks() {
        checkAbilityToAddNewClerks(false);
    }

    @And("he uses {word} credentials, ID {word} and Password {word}, to list clerks,")
    public void eusebiaUsesHerIDAndPasswordToListClerks(
            String alias, String clerkId, String password) {
        managesClerks(OnStage.theActorInTheSpotlight(), clerkId, password);
        listsTheClerks();
    }

    @Then(
            "he should see the list as an Admin, meaning {word} can see all clerks \\(Admin,"
                    + " Manager, and Employees = {string}),")
    public void heShouldSeeTheListAsAnAdminMeaningHeCanSeeAllClerksAdminManagerAndEmployees(
            String alias, String clerkList) {
        verifyClerkList(clerkList);
    }

    @Then("he should use the ID {word} with the new password {word} to revert it to {word}.")
    public void heShouldUseTheIDWithTheNewPasswordToRevertItTo(
            String clerkId, String currentPassword, String newPassword) {
        testNewPasswordAndChangeIt(clerkId, currentPassword, newPassword);
    }

    @Then(
            "he should see the list as a Manager, meaning he can see {word} account and all the"
                    + " Employees roles \\({string}),")
    public void heShouldSeeTheListAsAManagerMeaningHeCanSeeEusebiaSAccountAndAllTheEmployeesRoles(
            String alias, String clerkList) {
        verifyClerkList(clerkList);
    }

    @When("he attempts to delete {word} account \\(ID {word})")
    public void heAttemptsToDeleteArcadioSAccountID(String alias, String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.openAccountDetailsForProfile(actor, clerkId),
                ClerkManagementTasks.deleteClerk(actor, false, true),
                ClerkManagementTasks.dismissSuccessConfirmationAfterDeletingClerk(actor, true),
                CommonTasks.returnToMainScreen(actor));
    }

    @Then("he should not see {word} account \\(ID {word}) in the clerks list.")
    public void heShouldNotSeeArcadioSAccountIDInTheClerksList(String alias, String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();

        reLogin(actor);

        actor.attemptsTo(
                Ensure.that(
                                "Should see the Clerk ID '%s' is not present for %s"
                                        .formatted(clerkId, alias),
                                VisibilityQuestion.isPresent(
                                        MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE.of(
                                                clerkId)))
                        .isFalse());
    }

    @When("he removes all clerks except himself \\(ID {word}),")
    public void heCleansTheClerksList(String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                ClerkManagementTasks.removeClerksDifferentThan(actor, clerkId),
                CommonTasks.returnToMainScreen(actor));
    }

    @Then("he should see only his ID {word} in the list.")
    public void heShouldSeeOnlyHisIDInTheList(String clerkId) {
        Actor actor = OnStage.theActorInTheSpotlight();
        reLogin(actor);
        actor.attemptsTo(
                Ensure.that(
                                "Should see that the list just have one element",
                                ElementListQuestion.quantityOf(
                                        MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS))
                        .isEqualTo(1),
                Ensure.that(
                                "Should see that the list have only their own ID: '%s'"
                                        .formatted(clerkId),
                                VisibilityQuestion.isPresent(
                                        MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(
                                                clerkId)))
                        .isTrue());
    }

    private void reLogin(Actor actor) {
        managesClerks( // Re-login
                actor, actor.recall(CLERK_ID), actor.recall(CLERK_PASSWORD));
    }

    @Given("{actor} is using the wrong Clerk ID {word},")
    public void aurelianoIsUsingTheWrongClerkID(Actor actor, String clerkId) {
        actor.attemptsTo(MainSettingsTasks.openClerkManagementScreen(actor));
        actor.remember( // Save the clerk ID for later use
                CLERK_ID, clerkId);
    }

    @When("he tries to manage clerks with a wrong Clerk ID,")
    public void heTriesToManageClerks() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(LoginAsTasks.fillClerkID(actor, SCREEN_TITLE, actor.recall(CLERK_ID)));
    }

    @Then("he should receive the error message error message {string} with the title {string}.")
    public void heShouldReceiveTheErrorMessageErrorMessageWithTheTitle(
            String alertMessage, String alertTitle) {
        Actor actor = OnStage.theActorInTheSpotlight();

        actor.attemptsTo(
                CommonTasks.validateAndDismissPopupAlertWithOkButton(
                        actor, alertTitle, alertMessage),
                CommonTasks.tapBackArrow(actor));

        if (alertMessage.contains("User")) {
            heShouldSeeTheMainFunctionScreenInsteadOfThePasswordProtectedFunctionScreen();
        }

        if (alertMessage.contains("password")) {
            actor.attemptsTo(
                    Ensure.that(
                                    "Should be returned to the Clerk ID entry screen",
                                    TextQuestion.of(NumericScreen.LABEL_REASON))
                            .isEqualToIgnoringCase("Enter your Clerk ID"));
        }
    }

    @Given("{actor}, with ID {word} and wrong Password {word},")
    public void aurelianoWithIDAndPassword(Actor actor, String clerkId, String clerkPassword) {
        actor.attemptsTo(MainSettingsTasks.openClerkManagementScreen(actor));
        actor.remember( // Save the clerk ID for later use
                CLERK_ID, clerkId);
        actor.remember( // Save the clerk password for later use
                CLERK_PASSWORD, clerkPassword);
    }

    @When("he tries to manage clerks with a wrong password,")
    public void heTriesToManageClerksWithAWrongPassword() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(
                LoginAsTasks.fillClerkID(actor, actor.recall(CLERK_ID)),
                LoginAsTasks.fillPassword(actor, SCREEN_TITLE, actor.recall(CLERK_PASSWORD)));
    }

    @When("he attempts to go to the previous screen")
    public void heAttemptsToGoToThePreviousScreen() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(CommonTasks.tapBackArrow(actor));
    }

    @When("he attempts to go to the previous screen using the physical back key")
    public void heAttemptsToGoToThePreviousScreenUsingThePhysicalBackKey() {
        OnStage.theActorInTheSpotlight().attemptsTo(CommonTasks.pressPhysicalBackKey());
    }

    @Then(
            "he should see the main function screen instead of the password-protected function"
                    + " screen.")
    public void heShouldSeeTheMainFunctionScreenInsteadOfThePasswordProtectedFunctionScreen() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Should be returned to the Settings main screen",
                                        VisibilityQuestion.isPresent(MainSettingsScreen.TITLE))
                                .isTrue());
    }
}
