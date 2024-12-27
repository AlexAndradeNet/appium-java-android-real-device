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
    private static final String DEFAULT_ID = "1";
    private static final String DEFAULT_PASSWORD = "111111";

    @Given("{actor} is managing clerks,")
    public void isManagingClerks(Actor theActor) {
        managesClerks(theActor, DEFAULT_ID, DEFAULT_PASSWORD);
    }

    private void managesClerks(Actor theActor, String clerkId, String clerkPassword) {
        theActor.attemptsTo(
                MainSettingsTasks.openClerkManagementScreen(),
                LoginAsTasks.clerk(clerkId, clerkPassword));
    }

    @When("he lists the clerks,")
    public void listsTheClerks() {
        OnStage.theActorInTheSpotlight()
                .remember( // Save the list of clerks for later use
                        "clerksList",
                        ElementListQuestion.listOfValues(
                                MainClerkManagementScreen.LIST_OF_USER_PROFILE_IDS));
    }

    @When("he/she views the clerks list based on his/her role,")
    public void heViewsTheClerkListBasedOnHisRole() {
        listsTheClerks();
    }

    @When("he changes (the other ){word} role from {word} to {word},")
    public void updatesClerkRoleFromEmployeeToManager(
            String clerk, String oldRole, String newRole) {}

    @When("he attempts delete all clerks except himself,")
    public void attemptsDeleteAllClerksExceptHimself() {}

    @When("he search for the Clerk ID {int},")
    public void searchForTheClerkID(int clerkId) {}

    @When("he requests his account details,")
    public void requestsHisAccountDetails() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(ClerkManagementTasks.openAccountDetailsForProfile(DEFAULT_ID));
    }

    @When("he requests another clerk's account details,")
    public void requestsAnotherClerkSAccountDetails() {}

    @When("he attempts to change his password to the current password,")
    public void attemptsToChangeHisPasswordToTheCurrentPassword() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(DEFAULT_ID),
                        ClerkManagementTasks.changePassword(DEFAULT_PASSWORD, DEFAULT_PASSWORD));
    }

    @When("he attempts to change his password failing the verification,")
    public void attemptsToChangeHisPasswordButFailsTheVerification() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(DEFAULT_ID),
                        ClerkManagementTasks.changePassword(DEFAULT_PASSWORD, "222222"));
    }

    @When("he attempts to change his password for a valid new password,")
    public void attemptsToChangeHisPasswordForAValidNewPassword() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.openAccountDetailsForProfile(DEFAULT_ID),
                        ClerkManagementTasks.changePassword("222222", "222222"));
    }

    @When("he attempts to create a new clerk with his own Clerk ID,")
    public void attemptsToCreateANewClerkWithHisOwnClerkID() {}

    @When("he attempts to assign his own Clerk ID to another clerk,")
    public void attemptsToAssignHisOwnClerkIDToAnotherClerk() {}

    @When("he attempts to update {word} Clerk ID to a valid value,")
    public void attemptsToUpdateClerkIDToAValidValue(String clerk) {}

    @When("he attempts to update {word} password to a valid value,")
    public void attemptsToUpdatePasswordToAValidValue(String clerk) {}

    @When("he attempts to delete a clerk but regrets it,")
    public void attemptsToDeleteAClerkButRegretsIt() {}

    @Then("he should see that only his account remains in the clerks list.")
    public void shouldSeeThatOnlyHisAccountRemainsInTheClerksList() {}

    @Then(
            "he should see that the clerks list is sorted numerically \\({string}) instead of"
                    + " alphabetically.")
    public void shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(
            String clerksList) {
        Actor theActor = OnStage.theActorInTheSpotlight();
        theActor.attemptsTo(
                Ensure.that(theActor.recall("clerksList").toString()).isEqualTo(clerksList));
    }

    @Then("he should see that each clerk has the correct role.")
    public void shouldSeeThatEachClerkHasTheCorrectRole() {}

    @Then("he should see that the clerk {string} is listed as a {string}.")
    public void shouldSeeThatTheClerkIsListedAsA(String clerkName, String clerkRole) {}

    @Then("he should that the list is empty.")
    public void shouldThatTheListIsEmpty() {}

    @Then("he should have the options to change password, Clerk ID, and Role.")
    public void shouldHaveTheOptionsToChangePasswordClerkIDAndRole() {}

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

    @And("he should still be able to use his old password to manage clerks.")
    public void shouldStillBeAbleToUseHisOldPasswordToManageClerks() {
        OnStage.theActorInTheSpotlight().attemptsTo(MainTileScreenTasks.returnToMainScreen());
        isManagingClerks(OnStage.theActorInTheSpotlight());
        Ensure.that(VisibilityQuestion.isPresent(MainClerkManagementScreen.TITLE)).isTrue();
    }

    @Then("he should be able to use his new password to manage clerks.")
    public void shouldBeAbleToUseHisNewPasswordToManageClerks() {
        OnStage.theActorInTheSpotlight().attemptsTo(MainTileScreenTasks.returnToMainScreen());
        managesClerks(OnStage.theActorInTheSpotlight(), DEFAULT_ID, "222222");

        // Reverts previous password
        requestsHisAccountDetails();
        OnStage.theActorInTheSpotlight()
                .attemptsTo(
                        ClerkManagementTasks.changePassword(DEFAULT_PASSWORD, DEFAULT_PASSWORD));
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

    @Then("he should receive an error message stating that the Clerk ID is already in use.")
    public void shouldReceiveAnErrorMessageStatingThatTheClerkIDIsAlreadyInUse() {}

    @Then("he should see {word} new Clerk ID in the clerks list.")
    public void shouldSeeNewClerkIDInTheClerksList(String clerk) {}

    @Then("he should see the clerk still in the clerks list.")
    public void shouldSeeTheClerkStillInTheClerksList() {}

    @Then(
            "she/they should have the visibility of an Employee, meaning she/they can only see"
                + " her/their own clerk account, and doesn't have the option to add new clerks.")
    public void
            shouldHaveTheVisibilityOfAnEmployeeMeaningSheCanOnlySeeHerOwnClerkAccountAndDoesNotHaveTheOptionToAddNewClerks() {}

    @Then(
            "(.*) should have the visibility of an Admin, meaning he sees all clerks"
                    + " \\(Admin, Manager, and Employees).")
    public void shouldHaveTheVisibilityOfAnAdminMeaningHeSeesAllClerksAdminManagerAndEmployees() {}

    @Then(
            "(.*) should have the visibility of a Manager, meaning he/she would see his/her"
                    + " account, all the Employees' roles, and have the option to add new clerks.")
    public void
            shouldHaveTheVisibilityOfAManagerMeaningSheWouldSeeHisAccountAllTheEmployeesRolesAndHaveTheOptionToAddNewClerks() {}

    @When(
            "he adds a new clerk with Alias {string}, ID {string}, Role {string}, and Password"
                    + " {string},")
    public void heAddsANewClerkWithAliasIDRoleAndPassword(
            String alias, String clerkId, String role, String password) {
        Actor theActor = OnStage.theActorInTheSpotlight();
        theActor.remember("clerkId" + alias, clerkId); // Save the clerkId for later use
        theActor.attemptsTo(ClerkManagementTasks.addANewUser(clerkId, role, password));
    }

    @Then("he should see the new clerk is listed as {string} with the role {string}.")
    public void heShouldSeeTheNewClerkIsListedAsWithTheRole(String clerkId, String role) {
        Actor theActor = OnStage.theActorInTheSpotlight();
        WebElement clerkElement =
                MainClerkManagementScreen.LABEL_USER_PROFILE_ROLE.of(clerkId).resolveFor(theActor);
        theActor.attemptsTo(
                ScrollAction.scrollUp(),
                Ensure.that(TextQuestion.of(clerkElement)).isEqualTo(role));
    }
}
