@ignore
Feature: Password-protected notable transactions (manual execution)
  As Aureliano (Admin),
  I want notable transactions to be password-protected,
  So that I can manage who has access.

  @ignore
  # This is necessary to ensure that the first screen of the functionality is
  # always the same
  Scenario: 1 - Setup - Disable all prompts in flow
    Given Aureliano is in the Main Screen
    When he deactivates all toggles options
    Then he should see all toggles were deactivated

  @ignore
  Scenario: 2 - Setup - Enable SAF mode
    Given Aureliano is in the Main Screen
    When he enables the SAF mode with ID 1 and Password 111111
    Then he should see that SAF was enabled

  @ignore
  # This is necessary to clean states from manual tests
  Scenario: 3 - Setup - Clean the clerks list
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to remove all clerks except himself (ID 1)
    Then he should see only his ID 1 is the only one in the list

  @ignore
  Scenario: 4 - Setup - Create the required clerks
    Given Aureliano, with ID 1 and Password 111111, is managing clerks
    When he attempts to add multiple clerks with the following data:
      | alias      | clerk_id | role     | password |
      #| Aureliano  | 1       | Admin  | 111111   |
      | Melquiades | 2        | Manager  | 111111   |
      | Eusebia    | 3        | Employee | 111111   |
    Then he should see the each new clerk was created correctly

  #@ignore
  Scenario Outline: 5 - Check Admins and Managers are allowed when minimum access level is Manager
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be ALLOWED to access the functionality <functionality> as admin

    Examples:
      | Title                        | functionality | clerk_id | role    | password |
      # Refund
      | Refund: Admins have access   | Refund        | 1        | Admin   | 111111   |
      | Refund: Managers have access | Refund        | 2        | Manager | 111111   |
      # Moto
      | Moto: Admins have access     | Moto          | 1        | Admin   | 111111   |
      | Moto: Managers have access   | Moto          | 2        | Manager | 111111   |
      # Batch
      | Batch: Admins have access    | Settle        | 1        | Admin   | 111111   |
      | Batch: Managers have access  | Settle        | 2        | Manager | 111111   |
      # Void
      | Void: Admins have access     | Void          | 1        | Admin   | 111111   |
      | Void: Managers have access   | Void          | 2        | Manager | 111111   |
      # SAF
      | SAF: Admins have access      | SAF           | 1        | Admin   | 111111   |
      | SAF: Managers have access    | SAF           | 2        | Manager | 111111   |

  @ignore
  Scenario Outline: 6 - Check only Employees are rejected when minimum access level is Manager
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be REJECTED to access the functionality <functionality> as <role>

    Examples:
      | functionality | clerk_id | role     | password |
      | Refund        | 3        | Employee | 111111   |
      | Moto          | 3        | Employee | 111111   |
      | Settle        | 3        | Employee | 111111   |
      | Void          | 3        | Employee | 111111   |
      | SAF           | 3        | Employee | 111111   |

  @ignore
  Scenario Outline: 7 - Check all clerk roles are allowed when minimum access level is Employee
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>
    Then he should be ALLOWED to access the functionality <functionality> as admin

    Examples:
      | functionality | clerk_id | role     | password |
      # Refund
      | Refund        | 1        | Admin    | 111111   |
      | Refund        | 2        | Manager  | 111111   |
      | Refund        | 3        | Employee | 111111   |
      # Moto
      | Moto          | 1        | Admin    | 111111   |
      | Moto          | 2        | Manager  | 111111   |
      | Moto          | 3        | Employee | 111111   |
      # Batch
      | Settle        | 1        | Admin    | 111111   |
      | Settle        | 2        | Manager  | 111111   |
      | Settle        | 3        | Employee | 111111   |
      # Void
      | Void          | 1        | Admin    | 111111   |
      | Void          | 2        | Manager  | 111111   |
      | Void          | 3        | Employee | 111111   |
      # SAF
      | SAF           | 1        | Admin    | 111111   |
      | SAF           | 2        | Manager  | 111111   |
      | SAF           | 3        | Employee | 111111   |

  @ignore
  Scenario: 8 - Post-Setup - Disable SAF mode
    Given Aureliano is in the Main Screen
    When he disables the SAF mode with ID 1 and Password 111111
    Then he should see that SAF was disabled
