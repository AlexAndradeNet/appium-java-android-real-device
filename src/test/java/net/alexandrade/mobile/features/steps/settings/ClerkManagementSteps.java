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

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.alexandrade.mobile.screenplay.interactions.ScrollAction;
import net.alexandrade.mobile.screenplay.interactions.TapAction;
import net.alexandrade.mobile.screenplay.questions.ElementListQuestion;
import net.alexandrade.mobile.screenplay.questions.TextQuestion;
import net.alexandrade.mobile.screenplay.questions.VisibilityQuestion;
import net.alexandrade.mobile.screenplay.tasks.MainTileScreenTasks;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.ClerkManagementTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.alexandrade.mobile.screenplay.ui.CommonObjects;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.MainClerkManagementScreen;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.WebElement;

public class ClerkManagementSteps {

    @Given("{actor}, with ID {word} and Password {word}, is managing clerks,")
    public void isManagingClerks(Actor actor, String clerkId, String password) {
        managesClerks(actor, clerkId, password);
    }

    private void managesClerks(Actor theActor, String clerkId, String clerkPassword) {
        theActor.attemptsTo(
                MainSettingsTasks.openClerkManagementScreen(),
                LoginAsTasks.clerk(clerkId, clerkPassword));

        theActor.remember( // Save the clerk ID for later use
                "clerkId", clerkId);
        theActor.remember( // Save the clerk password for later use
                "clerkPassword", clerkPassword);
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
    public void requestsHisAccountDetails(String clerkId) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(ClerkManagementTasks.openAccountDetailsForProfile(clerkId));
    }

    @When("he requests another clerk's account details \\(ex: ID {word}),")
    public void requestsAnotherClerkSAccountDetails(String clerkID) {
        requestsHisAccountDetails(clerkID);
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
                .attemptsTo(ClerkManagementTasks.addANewUser(clerkId, "Employee", "111111"));
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
    public void shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(
            String alias, String clerksList) {
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
            "(.*), with ID {word}, should be able to use his new password {word} to revert it to"
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
        requestsHisAccountDetails(clerkId);
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
                        TapAction.on(CommonObjects.POPUP_MESSAGE_FIRST_OR_UNIQUE_BUTTON));
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

        managesClerks( // Re-login
                theActor, theActor.recall("clerkId"), theActor.recall("clerkPassword"));

        shouldSeeNewClerkIDInTheClerksList(alias, clerkId);
    }

    @When(
            "he adds a new clerk with Alias {word}, ID {word}, Role {word}, and Password"
                    + " {word},")
    public void heAddsANewClerkWithAliasIDRoleAndPassword(
            String alias, String clerkId, String role, String password) {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.addANewUser(clerkId, role, password),
                        ClerkManagementTasks.dismissSuccessConfirmationAfterAddingANewUser());
    }

    @Then("he should see the new clerk is listed as {word} with the role {word}.")
    public void heShouldSeeTheNewClerkIsListedAsWithTheRole(String clerkId, String role) {
        Actor theActor = OnStage.theActorInTheSpotlight();
        WebElement clerkElement =
                MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE.of(clerkId).resolveFor(theActor);
        theActor.attemptsTo(
                ScrollAction.scrollUp(),
                Ensure.that(TextQuestion.of(clerkElement)).isEqualTo(role));
    }

    @Then(
            "he/she should see the list as a Manager, meaning he/she can see his/her account and"
                    + " all the Employees' roles \\({string}),")
    public void shouldSeeTheListAsAManagerMeaningHeCanSeeHisAccountAllTheEmployeesRoles(
            String clerkList) {
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(null, clerkList);
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
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(null, clerkId);
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
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(alias, clerkList);
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
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(alias, clerkList);
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

        managesClerks( // Re-login
                theActor, theActor.recall("clerkId"), theActor.recall("clerkPassword"));

        theActor.attemptsTo(
                Ensure.that(
                                "The %s new Clerk %s should not be present"
                                        .formatted(alias, clerkId),
                                VisibilityQuestion.isPresent(
                                        MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE.of(
                                                clerkId)))
                        .isFalse());
    }
}
