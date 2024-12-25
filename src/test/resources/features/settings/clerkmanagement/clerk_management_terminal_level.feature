#@ignore
Feature: clerk and Role management
  As a user,
  I want to manage clerks in the terminal,
  so that I can control their roles in my business.


  #@ignore
  Scenario: 1.6: clerks can only change their own passwords, and there are no options to change Clerk ID or Role.
    Given Aureliano is managing clerks,
    When he requests his account details,
    Then he should only have the option to change his password, and no options for Clerk ID or Role.

  @ignore
  Scenario: 1.8: Unsuccessful change of their own password using the current password
    Given Aureliano is managing clerks,
    When he attempts to change his password to the current password,
    Then he should receive the error message "New password should not be same as old password!".
    And he should still be able to use his old password to manage clerks.

  @ignore
  Scenario: 1.9: Unsuccessful change of their own password due to a mismatch verification
    Given Aureliano is managing clerks,
    When he attempts to change his password failing the verification,
    Then he should receive the error message "Confirm password should be same as password.".
    And he should still be able to use his old password to manage clerks.

  @ignore
  Scenario: 1.10: Successful change of their own password
    Given Aureliano is managing clerks,
    When he attempts to change his password for a valid new password,
    Then he should be able to use his new password to manage clerks.

  @ignore
  Scenario: 1.1: Create new clerks
    Given Aureliano is managing clerks,
    When he adds new clerks with the required information,
      | Clerk ID | Role     | Password | Optional Alias |
      | 10       | Admin    | 111111   | Arcadio        |
      | 20       | Manager  | 111111   | Melquiades     |
      | 21       | Manager  | 111111   | Mercedes       |
      | 30       | Employee | 111111   | Eusebia        |
      | 31       | Employee | 111111   | Elena          |
      | 100      | Employee | 111111   | Escolastica    |
    Then he should see 7 clerks listed (himself and six new clerks).

  @ignore
  Scenario: 1.2: Verify correct clerks list sorting
    Given Aureliano is managing clerks,
    When he lists the clerks,
    Then he should see that the clerks list is sorted numerically ("1, 10, 20, 21, 31, 100") instead of alphabetically.

  @ignore
  Scenario: 1.3: Verify correct clerks list roles
    Given Aureliano is managing clerks,
    When he lists the clerks,
    Then he should see that each clerk has the correct role.
      | Clerk ID | Role     | Password | Optional Alias |
      | 10       | Admin    | 111111   | Arcadio        |
      | 20       | Manager  | 111111   | Melquiades     |
      | 21       | Manager  | 111111   | Mercedes       |
      | 30       | Employee | 111111   | Eusebia        |
      | 31       | Employee | 111111   | Elena          |
      | 100      | Employee | 111111   | Escolastica    |

  @ignore
  Scenario: 1.4: Verify correct clerks list roles
    Given Aureliano is managing clerks,
    When he search for the Clerk ID 20,
    Then he should see that the clerk "Melquiades" is listed as a "Manager".

  @ignore
  Scenario: 1.5: Verify correct clerks list roles
    Given Aureliano is managing clerks,
    When he search for the Clerk ID 200,
    Then he should that the list is empty.

  @ignore
  Scenario: 1.7: Admins can change other clerks' account details.
    Given Aureliano is managing clerks,
    When he requests another clerk's account details,
    Then he should have the options to change password, Clerk ID, and Role.

  @ignore
  Scenario: 1.11: Prevent creating a new clerk with an existing Clerk ID
    Given Aureliano is managing clerks,
    When he attempts to create a new clerk with his own Clerk ID,
    Then he should receive the error message "Please verify clerk Id and try again!".

  @ignore
  Scenario: 1.12: Prevent renaming a clerk to an existing Clerk ID
    Given Aureliano is managing clerks,
    When he attempts to assign his own Clerk ID to another clerk,
    Then he should receive an error message stating that the Clerk ID is already in use.

  @ignore
  Scenario: 1.13: Rename other clerk's Clerk ID.
    Given Aureliano is managing clerks,
    When he attempts to update Melquiades' Clerk ID to a valid value,
    Then he should see Melquiades' new Clerk ID in the clerks list.

  @ignore
  Scenario: 1.14: Change other clerk's password.
    Given Aureliano is managing clerks,
    When he attempts to update Melquiades' password to a valid value,
    Then Melquiades should be able to use his new password to manage clerks.

  # Filtering as Admin is managed in Scenarios 1.2 and 1.3

  @ignore
  Scenario: 1.16: Filter the clerks list as Manager.
    Given Melquiades is managing clerks,
    When he views the clerks list based on his role,
    Then he should have the visibility of a Manager, meaning she would see his account, all the Employees' roles, and have the option to add new clerks.

  @ignore
  Scenario: 1.17: Filter the clerks list as Employee.
    Given Eusebia is managing clerks,
    When she views the clerks list based on her role,
    Then she should have the visibility of an Employee, meaning she can only see her own clerk account, and doesn't have the option to add new clerks.

  @ignore
  Scenario: 1.18: Change a clerk's role from Eusebia to Manager.
    Given Aureliano is managing clerks,
    When he changes Eusebia's role from Employee to Manager,
    Then Eusebia should have the visibility of a Manager, meaning she would see her account, all the Employees' roles, and have the option to add new clerks.

  @ignore
  Scenario: 1.19: Change a clerk's role from Manager to Admin.
    Given Aureliano is managing clerks,
    When he changes Melquiades' role from Manager to Admin,
    Then Melquiades should have the visibility of an Admin, meaning he sees all clerks (Admin, Manager, and Employees).

  @ignore
  Scenario: 1.20: Change a clerk's role from Admin to Employee.
    Given Aureliano is managing clerks,
    When he changes the other clerk's role from Admin to Employee,
    Then they should have the visibility of an Employee, meaning they can only see their own clerk account, and doesn't have the option to add new clerks.

  @ignore
  Scenario: 1.21: clerk deletion regret.
    Given Aureliano is managing clerks,
    When he attempts to delete a clerk but regrets it,
    Then he should see the clerk still in the clerks list.

  @ignore
  Scenario: 1.22: clerk deletion: delete all clerks except himself
    Given Aureliano is managing clerks,
    When he attempts delete all clerks except himself,
    Then he should see that only his account remains in the clerks list.
