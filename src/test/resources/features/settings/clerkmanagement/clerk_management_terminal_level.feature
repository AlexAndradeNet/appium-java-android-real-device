#@ignore
Feature: clerk and Role management
  As a user,
  I want to manage clerks in the terminal,
  so that I can control their roles in my business.


  #@ignore
  Scenario: 1: clerks can only change their own passwords, and there are no options to change Clerk ID or Role.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he requests his account details, which is 1,
    Then he should only have the option to change his password, and no options for Clerk ID or Role.

  #@ignore
  Scenario: 2: Unsuccessful change of their own password using the current password
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he, with ID 1, attempts to change his password to the current password 111111,
    Then he should receive the error message "New password should not be same as old password!".
    And he should still be able to use his ID 1 and old password 111111 to manage clerks.

  #@ignore
  Scenario: 3: Unsuccessful change of their own password due to a mismatch verification
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he, with ID 1, attempts to change his password to 222222, failing the verification by entering 333333,
    Then he should receive the error message "Confirm password should be same as password.".
    And he should still be able to use his ID 1 and old password 111111 to manage clerks.

  #@ignore
  Scenario: 4: Successful change of their own password
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he, with ID 1, attempts to change his password to 222222,
    Then he, with ID 1, should be able to use his new password 222222 to revert it to 111111.

  #@ignore
  Scenario: 5: Clean the clerks list
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he cleans the clerks list,
    Then he should see only his ID 1 in the list.

  #@ignore
  Scenario Outline: 6: Create new clerks
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he adds a new clerk with Alias <Alias>, ID <Clerk ID>, Role <Role>, and Password <Password>,
    Then he should see the new clerk is listed as <Clerk ID> with the role <Role>.
    Examples:
      | Alias       | Clerk ID | Role     | Password |
      | Arcadio     | 10       | Admin    | 111111   |
      | Melquiades  | 20       | Manager  | 111111   |
      | Mercedes    | 21       | Manager  | 111111   |
      | Eusebia     | 30       | Employee | 111111   |
      | Elena       | 31       | Employee | 111111   |
      | Escolastica | 100      | Employee | 111111   |

  #@ignore
  Scenario: 7: Verify correct clerks list sorting
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he lists the clerks,
    Then he should see that the clerks list is sorted numerically ("1, 10, 20, 21, 30, 31, 100") instead of alphabetically.

  #@ignore
  Scenario: 8: Verify correct clerks list roles
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he search for the Clerk ID 20,
    Then he should see that the clerk Melquiades, with ID 20, is listed as a Manager.

  #@ignore
  Scenario: 9: Verify correct clerks list roles
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he search for the Clerk ID 200,
    Then he should that the list is empty.

  #@ignore
  Scenario: 10: Admins can change other clerks' account details.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he requests another clerk's account details (ex: ID 20),
    Then he should see the options to change password, Clerk ID, and Role.

  #@ignore
  Scenario: 11: Prevent creating a new clerk with an existing Clerk ID
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he attempts to create a new clerk with his own Clerk ID 1,
    Then he should receive the error message "Please verify User Id and try again!".

  #@ignore
  Scenario: 12: Rename other clerk's Clerk ID.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he attempts to update Melquiades' Clerk ID from 20 to 22,
    Then he should see Melquiades' new Clerk ID is 22 in the clerks list.

  #@ignore
  Scenario: 13: Change other clerk's password.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he attempts to update Melquiades' (ID 22) password to 222222,
    Then he should use the ID 22 with the new password 222222 to revert it to 111111.

  #@ignore
  Scenario: 14: Filter the clerks list as Manager.
    Given Melquiades, with ID 22 and Password 111111, is managing clerks,
    When he lists the clerks,
    Then he should see the list as a Manager, meaning he can see his account and all the Employees' roles ("22, 30, 31, 100"),
    And he should have the option to add new clerks.

  #@ignore
  Scenario: 15: Filter the clerks list as Employee.
    Given Eusebia, with ID 30 and Password 111111, is managing clerks,
    When she lists the clerks,
    Then she should see a list as an Employee, meaning she can see only her account, which is 30,
    And she should have no option to add new clerks.

  #@ignore
  Scenario: 16: Change a clerk's role from Eusebia to Manager.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he changes Eusebia's, with ID 30, role from Employee to Manager,
    And he uses Eusebia's credentials, ID 30 and Password 111111, to list clerks,
    Then he should see the list as a Manager, meaning he can see Eusebia's account and all the Employees roles ("30, 31, 100"),
    And he should have the option to add new clerks.

  #@ignore
  Scenario: 17: Change a clerk's role from Manager to Admin.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he changes Melquiades', with ID 22, role from Manager to Admin,
    And he uses Melquiades' credentials, ID 22 and Password 111111, to list clerks,
    Then he should see the list as an Admin, meaning Melquiades can see all clerks (Admin, Manager, and Employees = "1, 10, 21, 22, 30, 31, 100"),
    And he should have the option to add new clerks.

  #@ignore
  Scenario: 18: Change a clerk's role from Admin to Employee.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he changes Arcadio', with ID 10, role from Admin to Employee,
    And he uses Arcadio's credentials, ID 10 and Password 111111, to list clerks,
    Then he should see a list as an Employee, meaning he can see only his account, which is 10,
    And he should have no option to add new clerks.

  #@ignore
  Scenario: 19: clerk deletion regret.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he attempts to delete Arcadio's account (ID 10) but regrets it,
    Then he should still see Arcadio's account (ID 10) in the clerks list.

  #@ignore
  Scenario: 20: clerk deletion.
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he attempts to delete Arcadio's account (ID 10)
    Then he should not see Arcadio's account (ID 10) in the clerks list.
