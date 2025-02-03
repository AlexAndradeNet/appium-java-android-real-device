#@ignore
Feature: Password-protected notable transactions.
  As Aureliano (Admin),
  I want notable transactions to be password-protected,
  So that I can manage who has access.

  #@ignore
  Scenario: 1: Setup - Disable all prompts in flow
    Given Aureliano is in the Main Screen
    When he deactivates all toggles options
    Then he should see all toggles were deactivated

  #@ignore
  Scenario: 2: Setup - Enable SAF mode
    Given Aureliano is in the Main Screen
    When he enables the SAF mode with ID 1 and Password 111111
    Then he should see that SAF was enabled

  #@ignore
  Scenario: 3: Setup - Clean the clerks list
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to remove all clerks except himself (ID 1)
    Then he should see only his ID 1 is the only one in the list

  #@ignore
  Scenario: 4: Setup - Create the required clerks
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to add multiple clerks with the following data:
      | alias      | clerk_id | role     | password |
      #| Aureliano  | 1       | Admin  | 111111   |
      | Melquiades | 2        | Manager  | 111111   |
      | Eusebia    | 3        | Employee | 111111   |
    Then he should see the each new clerk was created correctly

  #@ignore
  Scenario Outline: 5: Check Admins are allowed when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be ALLOWED to access the functionality <functionality> as admin

    Examples:
      | functionality | clerk_id | role  | password |
      | Refund        | 1        | Admin | 111111   |
      | Moto          | 1        | Admin | 111111   |
      | Settle        | 1        | Admin | 111111   |
      | Void          | 1        | Admin | 111111   |
      | SAF           | 1        | Admin | 111111   |

  #@ignore
  # This is failing because needs to implement the role management against VHQ
  Scenario Outline: 6: (EXPECTING TO FAIL) Check Managers and Employees are rejected when minimum access level is Admin
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be REJECTED to access the functionality <functionality> as <role>

    Examples:
      | functionality | clerk_id | role     | password |
      # Refund
      | Refund        | 2        | Manager  | 111111   |
      | Refund        | 3        | Employee | 111111   |
      # Moto
      | Moto          | 2        | Manager  | 111111   |
      | Moto          | 3        | Employee | 111111   |
      # Batch
      | Settle        | 2        | Manager  | 111111   |
      | Settle        | 3        | Employee | 111111   |
      # Void
      | Void          | 2        | Manager  | 111111   |
      | Void          | 3        | Employee | 111111   |
      # Void
      | SAF           | 2        | Manager  | 111111   |
      | SAF           | 3        | Employee | 111111   |

  #@ignore
  Scenario: 7: Post-Setup - Disable SAF mode
    Given Aureliano is in the Main Screen
    When he disables the SAF mode with ID 1 and Password 111111
    Then he should see that SAF was disabled
