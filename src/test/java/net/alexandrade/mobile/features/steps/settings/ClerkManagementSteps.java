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
        // Pending implementation
    }

    @When("he attempts delete all clerks except himself \\(ID {word}),")
    public void attemptsDeleteAllClerksExceptHimself(String clerkId) {
        // Pending implementation
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
        // Pending implementation
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

    @When("he attempts to create a new clerk with his own Clerk ID,")
    public void attemptsToCreateANewClerkWithHisOwnClerkID() {
        // Pending implementation
    }

    @When("he attempts to assign his own Clerk ID to another clerk \\(ex: ID {word}),")
    public void attemptsToAssignHisOwnClerkIDToAnotherClerk(String clerkID) {
        // Pending implementation
    }

    @When("he attempts to update {word} Clerk ID {word} to {word},")
    public void attemptsToUpdateClerkIDToAValidValue(
            String alias, String clerkId, String newClerkId) {
        // Pending implementation
    }

    @When("he attempts to update {word} password to {word},")
    public void attemptsToUpdatePasswordToAValidValue(String alias, String newPassword) {
        // Pending implementation
    }

    @When("he attempts to delete {word} account \\(ID {word}) but regrets it,")
    public void attemptsToDeleteAClerkButRegretsIt(String alias, String clerkId) {
        // Pending implementation
    }

    @Then("he should see that only his account \\(ID {word}) remains in the clerks list.")
    public void shouldSeeThatOnlyHisAccountRemainsInTheClerksList(String clerkList) {
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(clerkList);
    }

    @Then(
            "he should see that the clerks list is sorted numerically \\({string}) instead of"
                    + " alphabetically.")
    public void shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(
            String clerksList) {
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
        // Pending implementation
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
            "he, with ID {word}, should be able to use his new password {word} to revert it to"
                    + " {word}.")
    public void shouldBeAbleToUseHisNewPasswordToManageClerks(
            String clerkId, String currentPassword, String newPassword) {
        testNewPasswordAndChangeIt(
                OnStage.theActorInTheSpotlight(), clerkId, currentPassword, newPassword);
    }

    private void testNewPasswordAndChangeIt(
            Actor actor, String clerkId, String currentPassword, String newPassword) {
        actor.attemptsTo(MainTileScreenTasks.returnToMainScreen());
        managesClerks(actor, clerkId, currentPassword);

        // Reverts previous password
        requestsHisAccountDetails(clerkId);
        actor.attemptsTo(ClerkManagementTasks.changePassword(newPassword, newPassword));
    }

    @Then(
            "{actor}, with ID {word}, should be able to use his new password {word} to revert it to"
                    + " {word}.")
    public void actorShouldBeAbleToUseHisNewPasswordToManageClerks(
            Actor actor, String clerkId, String currentPassword, String newPassword) {
        testNewPasswordAndChangeIt(actor, clerkId, currentPassword, newPassword);
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

    @Then("he should see {word} new Clerk ID {word} in the clerks list.")
    public void shouldSeeNewClerkIDInTheClerksList(String alias, String clerkId) {
        // Pending implementation
    }

    @Then("he should still see {word} account \\(ID {word}) in the clerks list.")
    public void shouldSeeTheClerkStillInTheClerksList(String alias, String clerkId) {
        // Pending implementation
    }

    @When(
            "he adds a new clerk with Alias {word}, ID {word}, Role {word}, and Password"
                    + " {word},")
    public void heAddsANewClerkWithAliasIDRoleAndPassword(
            String alias, String clerkId, String role, String password) {
        Actor theActor = OnStage.theActorInTheSpotlight();
        theActor.attemptsTo(ClerkManagementTasks.addANewUser(clerkId, role, password));
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
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(clerkList);
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
            "she should see a list as a Employee, meaning she can see only her account, which is"
                    + " {word},")
    public void sheShouldSeeAListAsAEmployeeMeaningSheCanSeeOnlyHerAccountWhichIs(String clerkId) {
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(clerkId);
    }

    @And("she/he should have no option to add new clerks.")
    public void sheHasNoOptionToAddNewClerks() {
        checkAbilityToAddNewClerks(false);
    }

    @And("{actor} uses her/his ID {word} and Password {word} to list clerks,")
    public void eusebiaUsesHerIDAndPasswordToListClerks(
            Actor actor, String clerkId, String password) {
        managesClerks(actor, clerkId, password);
    }

    @Then(
            "he should see the list as an Admin, meaning he can see all clerks \\(Admin, Manager,"
                    + " and Employees = {string}),")
    public void heShouldSeeTheListAsAnAdminMeaningHeCanSeeAllClerksAdminManagerAndEmployees(
            String clerkList) {
        shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(clerkList);
    }
}
