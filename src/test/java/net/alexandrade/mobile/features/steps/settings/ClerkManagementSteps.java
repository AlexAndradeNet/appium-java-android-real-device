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
import net.alexandrade.mobile.screenplay.interactions.SwipeAction;
import net.alexandrade.mobile.screenplay.interactions.TapAction;
import net.alexandrade.mobile.screenplay.questions.ElementListQuestion;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.MainTileScreenTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.CommonTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.ClerkManagementTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.alexandrade.mobile.screenplay.ui.CommonObjects;
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

    @Given("{actor}, with ID {word} and Password {word}, is managing clerks,")
    public void isManagingClerks(Actor actor, String clerkId, String password) {
        managesClerks(actor, clerkId, password);
    }

    private void managesClerks(Actor theActor, String clerkId, String clerkPassword) {
        theActor.attemptsTo(
                MainSettingsTasks.openClerkManagementScreen(),
                LoginAsTasks.fillClerkID(clerkId),
                LoginAsTasks.fillPassword(clerkPassword));

        theActor.remember( // Save the clerk ID for later use
                CLERK_ID, clerkId);
        theActor.remember( // Save the clerk password for later use
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
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.changeRole(newRole),
                        MainTileScreenTasks.returnToMainScreen());
    }

    @When("he search for the Clerk ID {word},")
    public void searchForTheClerkID(String clerkId) {
        OnStage.theActorInTheSpotlight().attemptsTo(ClerkManagementTasks.searchForClerkId(clerkId));
    }

    @When("he requests his account details, which is {word},")
    public void requestsAnAccountDetails(String clerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(ClerkManagementTasks.openAccountDetailsForProfile(clerkId));
    }

    @When("he requests another clerk's account details \\(ex: ID {word}),")
    public void requestsAnotherClerkSAccountDetails(String clerkID) {
        requestsAnAccountDetails(clerkID);
    }

    @When("he, with ID {word}, attempts to change his password to the current password {word},")
    public void attemptsToChangeHisPasswordToTheCurrentPassword(
            String clerkId, String currentPassword) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.changePassword(currentPassword, currentPassword));
    }

    @When(
            "he, with ID {word}, attempts to change his password to {word}, failing the"
                    + " verification by entering {word},")
    public void attemptsToChangeHisPasswordButFailsTheVerification(
            String clerkId, String newPassword, String wrongConfirmationPassword) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.changePassword(
                                newPassword, wrongConfirmationPassword));
    }

    @When("he, with ID {word}, attempts to change his password to {word},")
    public void attemptsToChangeHisPasswordForAValidNewPassword(
            String clerkId, String newPassword) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.changePassword(newPassword, newPassword));
    }

    @When("he attempts to create a new clerk with his own Clerk ID {word},")
    public void attemptsToCreateANewClerkWithHisOwnClerkID(String clerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(ClerkManagementTasks.addANewUser(true, clerkId, "Employee", "111111"));
    }

    @When("he attempts to update {word} Clerk ID from {word} to {word},")
    public void attemptsToUpdateClerkIDToAValidValue(
            String alias, String currentClerkId, String newClerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(currentClerkId),
                        ClerkManagementTasks.changeClerkId(newClerkId));
    }

    @When("he attempts to update {word} \\(ID {word}) password to {word},")
    public void attemptsToUpdatePasswordToAValidValue(
            String alias, String clerkId, String newPassword) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.changePassword(newPassword, newPassword));
    }

    @When("he attempts to delete {word} account \\(ID {word}) but regrets it,")
    public void attemptsToDeleteAClerkButRegretsIt(String alias, String clerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.deleteClerk(true, false),
                        MainTileScreenTasks.returnToMainScreen());
    }

    @Then(
            "he should see that the clerks list is sorted numerically \\({string}) instead of"
                    + " alphabetically.")
    public void verifyClerkList(String clerksList) {
        Actor theActor = OnStage.theActorInTheSpotlight();

        theActor.attemptsTo(
                Ensure.that(theActor.recall("clerksList").toString()).isEqualTo(clerksList));
    }

    @Then("he should see that the clerk {word}, with ID {word}, is listed as a {word}.")
    public void shouldSeeThatTheClerkIsListedAsA(
            String clerkName, String clerkId, String clerkRole) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Just one result should be in the list",
                                        ElementListQuestion.quantityOf(
                                                MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS))
                                .isEqualTo(1),
                        Ensure.that(
                                        "Looking for %s to be in the list".formatted(clerkName),
                                        TextQuestion.of(
                                                MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE
                                                        .of(clerkId)))
                                .isEqualTo(clerkRole));
    }

    @Then("he should that the list is empty.")
    public void shouldThatTheListIsEmpty() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "The list should be empty",
                                        ElementListQuestion.quantityOf(
                                                MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS))
                                .isEqualTo(0));
    }

    @Then("he should see the options to change password, Clerk ID, and Role.")
    public void shouldHaveTheOptionsToChangePasswordClerkIDAndRole() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "Visibility of Password change button",
                                        VisibilityQuestion.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_PASSWORD))
                                .isTrue(),
                        Ensure.that(
                                        "Visibility of ID change button",
                                        VisibilityQuestion.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_CLERK_ID))
                                .isTrue(),
                        Ensure.that(
                                        "Visibility of Role change button",
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
                                "Visibility of the Title",
                                VisibilityQuestion.isPresent(ViewClerkScreen.TITLE))
                        .isTrue(),
                Ensure.that(
                                "Visibility of the Clerk ID label",
                                TextQuestion.of(ViewClerkScreen.LABEL_CLERK_ID))
                        .isEqualTo("#" + actor.recall(CLERK_ID).toString()),
                Ensure.that(
                                "Visibility of the Clerk Role",
                                TextQuestion.of(ViewClerkScreen.LABEL_CLERK_ROLE))
                        .isEqualTo("Admin"),
                Ensure.that(
                                "Visibility of Password change button",
                                VisibilityQuestion.isPresent(
                                        ViewClerkScreen.BUTTON_CHANGE_PASSWORD))
                        .isTrue(),
                Ensure.that(
                                "Visibility of ID change button",
                                VisibilityQuestion.isPresent(
                                        ViewClerkScreen.BUTTON_CHANGE_CLERK_ID))
                        .isFalse(),
                Ensure.that(
                                "Visibility of Role change button",
                                VisibilityQuestion.isPresent(
                                        ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE))
                        .isFalse());
    }

    @And("he should still be able to use his ID {word} and old password {word} to manage clerks.")
    public void shouldStillBeAbleToUseHisOldPasswordToManageClerks(
            String clerkId, String oldPassword) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(MainTileScreenTasks.returnToMainScreen());
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
        Actor theActor = OnStage.theActorInTheSpotlight();
        theActor.attemptsTo(MainTileScreenTasks.returnToMainScreen());
        managesClerks(theActor, clerkId, currentPassword);

        // Reverts previous password
        requestsAnAccountDetails(clerkId);
        theActor.attemptsTo(ClerkManagementTasks.changePassword(newPassword, newPassword));
    }

    @Then("he should receive the error message {string}.")
    public void shouldReceiveTheErrorMessage(String messageError) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(TextQuestion.of(CommonObjects.POPUP_MESSAGE_TITLE))
                                .isEqualTo("Alert Message"),
                        Ensure.that(TextQuestion.of(CommonObjects.POPUP_MESSAGE_CONTENT))
                                .isEqualTo(messageError),
                        TapAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_OK));
    }

    @Then("he should see {word} new Clerk ID is {word} in the clerks list.")
    public void shouldSeeNewClerkIDInTheClerksList(String alias, String clerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        Ensure.that(
                                        "The %s new Clerk %s should be present"
                                                .formatted(alias, clerkId),
                                        VisibilityQuestion.isPresent(
                                                MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE
                                                        .of(clerkId)))
                                .isTrue());
    }

    @Then("he should still see {word} account \\(ID {word}) in the clerks list.")
    public void shouldSeeTheClerkStillInTheClerksList(String alias, String clerkId) {
        Actor theActor = OnStage.theActorInTheSpotlight();

        reLogin(theActor);

        shouldSeeNewClerkIDInTheClerksList(alias, clerkId);
    }

    @When("he attempts to add multiple clerks with the following data:")
    public void heAddsANewClerkWithAliasIDRoleAndPassword(@NotNull DataTable dataTable) {
        Actor theActor = OnStage.theActorInTheSpotlight();

        dataTable.asLists().stream()
                .skip(1) // Skip the header row
                .forEach(
                        row -> {
                            String clerkId = row.get(1);
                            String role = row.get(2);
                            String password = row.get(3);

                            theActor.attemptsTo(
                                    ClerkManagementTasks.addANewUser(
                                            false, clerkId, role, password),
                                    ClerkManagementTasks
                                            .dismissSuccessConfirmationAfterAddingANewUser());
                        });

        theActor.remember("clerkListDataTable", dataTable);
    }

    @Then("he should see the each new clerk was created correctly.")
    public void heShouldSeeTheNewClerkIsListedAsWithTheRole() {
        Actor theActor = OnStage.theActorInTheSpotlight();
        DataTable dataTable = theActor.recall("clerkListDataTable");

        dataTable.asLists().stream()
                .skip(1) // Skip the header row
                .forEach(
                        row -> {
                            String clerkId = row.get(1);
                            String role = row.get(2);

                            WebElement clerkElement;

                            clerkElement =
                                    MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE
                                            .of(clerkId)
                                            .resolveFor(theActor);
                            if (VisibilityQuestion.isPresent(clerkElement)
                                    .answeredBy(theActor)
                                    .equals(false)) {
                                theActor.attemptsTo(SwipeAction.toUp());
                            }

                            theActor.attemptsTo(
                                    Ensure.that(
                                                    "Check ID %s has the role '%s'"
                                                            .formatted(clerkId, role),
                                                    TextQuestion.of(clerkElement))
                                            .isEqualTo(role));
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
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(clerkId),
                        ClerkManagementTasks.deleteClerk(false, true),
                        ClerkManagementTasks.dismissSuccessConfirmationAfterDeletingClerk(true),
                        MainTileScreenTasks.returnToMainScreen());
    }

    @Then("he should not see {word} account \\(ID {word}) in the clerks list.")
    public void heShouldNotSeeArcadioSAccountIDInTheClerksList(String alias, String clerkId) {
        Actor theActor = OnStage.theActorInTheSpotlight();

        reLogin(theActor);

        theActor.attemptsTo(
                Ensure.that(
                                "The %s new Clerk %s should not be present"
                                        .formatted(alias, clerkId),
                                VisibilityQuestion.isPresent(
                                        MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE.of(
                                                clerkId)))
                        .isFalse());
    }

    @When("he removes all clerks except himself \\(ID {word}),")
    public void heCleansTheClerksList(String clerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.removeClerksDifferentThan(clerkId),
                        MainTileScreenTasks.returnToMainScreen());
    }

    @Then("he should see only his ID {word} in the list.")
    public void heShouldSeeOnlyHisIDInTheList(String clerkId) {
        Actor theActor = OnStage.theActorInTheSpotlight();
        reLogin(theActor);
        theActor.attemptsTo(
                Ensure.that(
                                "The list should have only one element",
                                ElementListQuestion.quantityOf(
                                        MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS))
                        .isEqualTo(1),
                Ensure.that(
                                "The list should have only his ID",
                                VisibilityQuestion.isPresent(
                                        MainClerkManagementScreen.BUTTON_USER_PROFILE_ID.of(
                                                clerkId)))
                        .isTrue());
    }

    private void reLogin(Actor theActor) {
        managesClerks( // Re-login
                theActor, theActor.recall(CLERK_ID), theActor.recall(CLERK_PASSWORD));
    }

    @Given("{actor} is using the wrong Clerk ID {word},")
    public void aurelianoIsUsingTheWrongClerkID(Actor actor, String clerkId) {
        actor.attemptsTo(MainSettingsTasks.openClerkManagementScreen());
        actor.remember( // Save the clerk ID for later use
                CLERK_ID, clerkId);
    }

    @When("he tries to manage clerks with a wrong Clerk ID,")
    public void heTriesToManageClerks() {
        Actor theActor = OnStage.theActorInTheSpotlight();
        theActor.attemptsTo(LoginAsTasks.fillClerkID(true, theActor.recall(CLERK_ID)));
    }

    @Then("he should receive the error message error message {string} with the title {string}.")
    public void heShouldReceiveTheErrorMessageErrorMessageWithTheTitle(
            String alertMessage, String alertTitle) {
        Actor theActor = OnStage.theActorInTheSpotlight();

        theActor.attemptsTo(
                Ensure.that(TextQuestion.of(CommonObjects.POPUP_MESSAGE_TITLE))
                        .isEqualTo(alertTitle),
                Ensure.that(TextQuestion.of(CommonObjects.POPUP_MESSAGE_CONTENT))
                        .isEqualTo(alertMessage),
                TapAction.on(CommonObjects.POPUP_MESSAGE_BUTTON_OK),
                TapAction.on(CommonObjects.BUTTON_ARROW_BACK));

        if (alertMessage.contains("User")) {
            theActor.attemptsTo(
                    Ensure.that(VisibilityQuestion.isPresent(MainSettingsScreen.TITLE)).isTrue());
        }

        if (alertMessage.contains("password")) {
            theActor.attemptsTo(
                    Ensure.that(TextQuestion.of(NumericScreen.LABEL_REASON))
                            .isEqualTo("Enter your Clerk ID"));
        }
    }

    @Given("{actor}, with ID {word} and wrong Password {word},")
    public void aurelianoWithIDAndPassword(Actor actor, String clerkId, String clerkPassword) {
        actor.attemptsTo(MainSettingsTasks.openClerkManagementScreen());
        actor.remember( // Save the clerk ID for later use
                CLERK_ID, clerkId);
        actor.remember( // Save the clerk password for later use
                CLERK_PASSWORD, clerkPassword);
    }

    @When("he tries to manage clerks with a wrong password,")
    public void heTriesToManageClerksWithAWrongPassword() {
        Actor theActor = OnStage.theActorInTheSpotlight();
        theActor.attemptsTo(
                LoginAsTasks.fillClerkID(false, theActor.recall(CLERK_ID)),
                LoginAsTasks.fillPassword(true, theActor.recall(CLERK_PASSWORD)));
    }

    @When("he attempts to go to the previous screen")
    public void heAttemptsToGoToThePreviousScreen() {
        OnStage.theActorInTheSpotlight().attemptsTo(CommonTasks.tapBackArrow());
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
                        Ensure.that(VisibilityQuestion.isPresent(MainSettingsScreen.TITLE))
                                .isTrue());
    }
}
