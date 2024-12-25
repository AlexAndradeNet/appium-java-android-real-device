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
import net.alexandrade.mobile.screenplay.questions.ElementVisibility;
import net.alexandrade.mobile.screenplay.tasks.commons.LoginAsTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.ClerkManagementTasks;
import net.alexandrade.mobile.screenplay.tasks.settings.MainSettingsTasks;
import net.alexandrade.mobile.screenplay.ui.settings.clerkmanagement.ViewClerkScreen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

public class ClerkManagementSteps {
    @Given("{actor} is managing clerks,")
    public void isManagingClerks(Actor theActor) {
        theActor.attemptsTo(MainSettingsTasks.openClerkManagementScreen(), LoginAsTasks.admin());
        // TODO: Ensure first screen is the clerk management screen
    }

    @When("he lists the clerks,")
    public void listsTheClerks() {}

    @When("he/she views the clerks list based on his/her role,")
    public void heViewsTheClerkListBasedOnHisRole() {
        listsTheClerks();
    }

    @When("he changes (the other ){word} role from {word} to {word},")
    public void updatesClerkRoleFromEmployeeToManager(
            String clerk, String oldRole, String newRole) {}

    @When("he attempts delete all clerks except himself,")
    public void attemptsDeleteAllClerksExceptHimself() {}

    @When("he adds new clerks with the required information,")
    public void addsNewClerksWithTheRequiredInformation() {}

    @When("he search for the Clerk ID {int},")
    public void searchForTheClerkID(int clerkId) {}

    @When("he requests his account details,")
    public void requestsHisAccountDetails() {
        OnStage.theActorInTheSpotlight()
                .attemptsTo(ClerkManagementTasks.openAccountDetailsForProfile("1"));
    }

    @When("he requests another clerk's account details,")
    public void requestsAnotherClerkSAccountDetails() {}

    @When("he attempts to change his password to the current password,")
    public void attemptsToChangeHisPasswordToTheCurrentPassword() {}

    @When("he attempts to change his password failing the verification,")
    public void attemptsToChangeHisPasswordButFailsTheVerification() {}

    @When("he attempts to change his password for a valid new password,")
    public void attemptsToChangeHisPasswordForAValidNewPassword() {}

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

    @Then("he should see {int} clerks listed \\(himself and six new clerks).")
    public void shouldSeeClerksListedHimselfAndSixNewClerks(int numberOfClerks) {}

    @Then(
            "he should see that the clerks list is sorted numerically \\({string}) instead of"
                    + " alphabetically.")
    public void shouldSeeThatTheClerksListIsSortedNumericallyInsteadOfAlphabetically(
            String clerksList) {}

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
                                        ElementVisibility.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_PASSWORD))
                                .isTrue(),
                        Ensure.that(
                                        "Visibility of ID change button",
                                        ElementVisibility.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_CLERK_ID))
                                .isFalse(),
                        Ensure.that(
                                        "Visibility of Role change button",
                                        ElementVisibility.isPresent(
                                                ViewClerkScreen.BUTTON_CHANGE_CLERK_ROLE))
                                .isFalse());
    }

    @And("he should still be able to use his old password to manage clerks.")
    public void shouldStillBeAbleToUseHisOldPasswordToManageClerks() {}

    @Then("he should be able to use his new password to manage clerks.")
    public void shouldBeAbleToUseHisNewPasswordToManageClerks() {}

    @Then("he should receive the error message {string}.")
    public void shouldReceiveTheErrorMessage(String messageError) {}

    @Then("he should receive an error message stating that the Clerk ID is already in use.")
    public void shouldReceiveAnErrorMessageStatingThatTheClerkIDIsAlreadyInUse() {}

    @Then("he should see {word} new Clerk ID in the clerks list.")
    public void shouldSeeNewClerkIDInTheClerksList(String clerk) {}

    @Then("{word} should be able to use his new password to manage clerks.")
    public void shouldBeAbleToUseHisNewPasswordToManageClerks(String clerk) {}

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
}
