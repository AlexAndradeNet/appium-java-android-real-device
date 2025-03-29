#@ignore
Feature: Semi-Integration Test Setup
  As an Admin,
  I want to control the Semi-Integration functionalities,
  So that I can manage different configurations.

  #@ignore
  Scenario: 1 - Setup - Clean the clerks list
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to remove all clerks except himself (ID 1)
    Then he should see only his ID 1 is the only one in the list

  #@ignore
  Scenario: 2 - Setup - Create the required clerks
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to add multiple clerks with the following data:
      | alias      | clerk_id | role     | password |
      #| Aureliano  | 1       | Admin  | 111111   |
      | Melquiades | 2        | Manager  | 111111   |
      | Eusebia    | 3        | Employee | 111111   |
    Then he should see the each new clerk was created correctly

  #@ignore
  Scenario: 3 - Deactivate Semi-Integration and verify terminal info
    Given Aureliano is managing the Semi-Integration settings
    When he attempts to deactivate the Semi-Integration feature
    Then he should see in the terminal’s information that Semi-Integration is not active

  #@ignore
  Scenario: 4 - Activate Semi-Integration without password prompt and verify terminal info
    Given Aureliano is managing the Semi-Integration settings
    When he attempts to activate the Semi-Integration feature leaving the password prompt off
    Then he should see that the terminal indicates Semi-Integration is active
    And he should see that NO Idle screen appears after waiting 60 seconds on the Main Screen

  #@ignore
  Scenario: 5 - Activate Semi-Integration with password prompt
    Given Aureliano is managing the Semi-Integration settings
    When he attempts to activate the Semi-Integration feature and the password prompt
    Then he should immediately see the Idle screen after returning to the Main Screen

  #@ignore
  Scenario: 6 - Check Admins are allowed when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and is in the Semi-Integration Idle Screen
    # TODO: implement VHQ
    When he attempts to log-in to Standalone Mode with ID 1 and password 111111
    Then he should see the Main screen
    And he should see that the Idle screen appears after waiting 60 seconds on the Main Screen

  #@ignore
  # BUG: This is failing because role management isn't implemented yet
  Scenario Outline: 7 - (EXPECTING TO FAIL) Check Managers and Employees are rejected when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and is in the Semi-Integration Idle Screen
    # TODO: implement VHQ
    When he attempts to log-in to Standalone Mode as <role> with ID <clerk_id>
    Then he should see be REJECTED to access the Standalone Mode as <role>

    Examples:
      | clerk_id | role     |
      #| 1       | Admin  |
      | 2        | Manager  |
      | 3        | Employee |

  #@ignore
  Scenario: 8 - Deactivate Semi-Integration leaving the password prompt active
    Given Aureliano is logged in Standalone Mode with ID 1 and password 111111
    When he attempts to deactivate the Semi-Integration feature leaving the password prompt active
    Then he should see in the terminal’s information that Semi-Integration is not active
    And he should see that NO Idle screen appears after waiting 60 seconds on the Main Screen

  #@ignore
  Scenario: 9 - Deactivate Semi-Integration and password features, then check terminal info
    Given Aureliano is managing the Semi-Integration settings
    When he attempts to deactivate the Semi-Integration feature and the password prompt
    Then he should see in the terminal’s information that Semi-Integration is not active
    And he should see that NO Idle screen appears after waiting 60 seconds on the Main Screen
