#@ignore
Feature: Password-protected notable transactions
  As Aureliano (Admin),
  I want notable transactions to be password-protected,
  So that I can manage who has access.

  #@ignore
  Scenario: 1 - Setup - Disable all prompts in flow
    Given Aureliano is in the Main Screen
    When he deactivates all toggles options
    Then he should see all toggles were deactivated

  #@ignore
  @saf
  Scenario: 2 - Setup - Enable SAF mode
    Given Aureliano is in the Main Screen
    When he enables the SAF mode with ID 1 and Password 111111
    Then he should see that SAF was enabled

  #@ignore
  Scenario: 3 - Setup - Clean the clerks list
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to remove all clerks except himself (ID 1)
    Then he should see only his ID 1 is the only one in the list

  #@ignore
  Scenario: 4 - Setup - Create the required clerks
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to add multiple clerks with the following data:
      | alias      | clerk_id | role     | password |
      #| Aureliano  | 1       | Admin  | 111111   |
      | Melquiades | 2        | Manager  | 111111   |
      | Eusebia    | 3        | Employee | 111111   |
    Then he should see the each new clerk was created correctly

  #@ignore
  Scenario Outline: 5a - Check Admins are allowed when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be ALLOWED to access the functionality <functionality> as admin

    Examples:
      | Title                      | functionality | clerk_id | role  | password |
      | Refund: Admins are allowed | Refund        | 1        | Admin | 111111   |
      | Moto: Admins are allowed   | Moto          | 1        | Admin | 111111   |
      | Batch: Admins are allowed  | Settle        | 1        | Admin | 111111   |
      | Void: Admins are allowed   | Void          | 1        | Admin | 111111   |
      # SAF requires a different scenario because of the cucumber parser does not allow use tags in examples

  @saf
  #@ignore
  Scenario: 5b - SAF Check Admins are allowed when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and opened the SAF option
    # TODO: implement VHQ
    When he attempts to login in SAF using the Admin, with ID 1 and Password 111111
    Then he should be ALLOWED to access the functionality SAF as admin

  #@ignore
  # This is failing because needs to implement the role management against VHQ
  Scenario Outline: 6a - (EXPECTING TO FAIL) Check Managers and Employees are rejected when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be REJECTED to access the functionality <functionality> as <role>

    Examples:
      | Title                          | functionality | clerk_id | role     | password |
      # Refund
      | Refund: Managers are rejected  | Refund        | 2        | Manager  | 111111   |
      | Refund: Employees are rejected | Refund        | 3        | Employee | 111111   |
      # Moto
      | Moto: Managers are rejected    | Moto          | 2        | Manager  | 111111   |
      | Moto: Employees are rejected   | Moto          | 3        | Employee | 111111   |
      # Batch
      | Batch: Managers are rejected   | Settle        | 2        | Manager  | 111111   |
      | Batch: Employees are rejected  | Settle        | 3        | Employee | 111111   |
      # Void
      | Void: Managers are rejected    | Void          | 2        | Manager  | 111111   |
      | Void: Employees are rejected   | Void          | 3        | Employee | 111111   |
      # SAF requires a different scenario because of the cucumber parser does not allow use tags in examples

  @saf
  #@ignore
  # This is failing because needs to implement the role management against VHQ
  Scenario Outline: 6b - SAF (EXPECTING TO FAIL) Check Managers and Employees are rejected when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be REJECTED to access the functionality <functionality> as <role>

    Examples:
      | Title                       | functionality | clerk_id | role     | password |
      # SAF
      | SAF: Managers are rejected  | SAF           | 2        | Manager  | 111111   |
      | SAF: Employees are rejected | SAF           | 3        | Employee | 111111   |

  #@ignore
  @saf
  Scenario: 7 - Post-Setup - Disable SAF mode
    Given Aureliano is in the Main Screen
    When he disables the SAF mode with ID 1 and Password 111111
    Then he should see that SAF was disabled
