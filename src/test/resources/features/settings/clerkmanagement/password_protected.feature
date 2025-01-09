#@ignore
Feature: As Aureliano (Admin), I want notable transactions to be password-protected so that I can manage who has access.
  Acceptance Criteria

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
      | Melquiades | 22       | Manager  | 111111   |
      | Eusebia    | 33       | Employee | 111111   |
    Then he should see the each new clerk was created correctly.

  #@ignore
  Scenario: 3: Setup - Deactivate all prompts in flow
    Given Aureliano is in the Main Screen,
    When he deactivates all toggles options,
    Then he should see all toggles were deactivated.

  #@ignore
  @flaky
  Scenario Outline: 4: Check minimum access level for Admin
    Given Aureliano defined the minimum access level for password-protected functionalities as Admin,
    # TODO: implement VHQ
    When he attempts to do a <Functionality> using the <Role>, with ID <Clerk ID> and Password <Password>,
    Then he should <Access> access the <Functionality> option as <Role>.
    Examples:
      | Functionality | Clerk ID | Role     | Password | Access  |
      # Refund
      | Refund        | 1        | Admin    | 111111   | have    |
      | Refund        | 22       | Manager  | 111111   | haven't |
      | Refund        | 33       | Employee | 111111   | haven't |
      # Moto
      | Moto          | 1        | Admin    | 111111   | have    |
      | Moto          | 22       | Manager  | 111111   | haven't |
      | Moto          | 33       | Employee | 111111   | haven't |
      # Batch
      | Settle         | 1        | Admin    | 111111   | have    |
      | Settle         | 22       | Manager  | 111111   | haven't |
      | Settle         | 33       | Employee | 111111   | haven't |
      # Void
      | Void          | 1        | Admin    | 111111   | have    |
      | Void          | 22       | Manager  | 111111   | haven't |
      | Void          | 33       | Employee | 111111   | haven't |
