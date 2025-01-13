#@ignore
Feature: Password-protected notable transactions
  As Aureliano (Admin),
  I want notable transactions to be password-protected,
  So that I can manage who has access.

  #@ignore
  Scenario: 1: Setup - Clean the clerks list
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he removes all clerks except himself (ID 1),
    Then he should see only his ID 1 in the list.

  #@ignore
  Scenario: 2: Setup - Create the required clerks
    Given Aureliano, with ID 1 and Password 111111, is managing clerks,
    When he attempts to add multiple clerks with the following data:
      | Alias      | Clerk ID | Role     | Password |
      #| Aureliano  | 1       | Admin  | 111111   |
      | Melquiades | 2        | Manager  | 111111   |
      | Eusebia    | 3        | Employee | 111111   |
    Then he should see the each new clerk was created correctly.

  #@ignore
  Scenario: 3: Setup - Deactivate all prompts in flow
    Given Aureliano is in the Main Screen,
    When he deactivates all toggles options,
    Then he should see all toggles were deactivated.

  #@ignore
  Scenario Outline: 4: Check minimum access level for Admin
    Given Aureliano defined the minimum access level as Admin and opened the <functionality> option,
    # TODO: implement VHQ
    When he attempts to login in <functionality> using the <role>, with ID <clerk_id> and Password <password>,
    Then he should <access> access the <functionality> option as <role>.

    Examples:
      | functionality | clerk_id | role     | password | access  |
      # Refund
      | Refund        | 1        | Admin    | 111111   | have    |
      | Refund        | 2        | Manager  | 111111   | haven't |
      | Refund        | 3        | Employee | 111111   | haven't |
      # Moto
      | Moto          | 1        | Admin    | 111111   | have    |
      | Moto          | 2        | Manager  | 111111   | haven't |
      | Moto          | 3        | Employee | 111111   | haven't |
      # Batch
      | Settle        | 1        | Admin    | 111111   | have    |
      | Settle        | 2        | Manager  | 111111   | haven't |
      | Settle        | 3        | Employee | 111111   | haven't |
      # Void
      | Void          | 1        | Admin    | 111111   | have    |
      | Void          | 2        | Manager  | 111111   | haven't |
      | Void          | 3        | Employee | 111111   | haven't |
