#@ignore
Feature: Control the flow of transactions
  As a User,
  I want to control the flow of transactions,
  So that I can manage the transactions in my business.

  #@ignore
  Scenario: 1 - Setup - Disable all prompts in flow
    Given Aureliano is in the Main Screen
    When he deactivates all toggles options
    Then he should see all toggles were deactivated

    #TODO: Split into get approvals and print the receipts

  #@ignore
  @printing
  Scenario: 2 - The clerk enables the Order Number prompting
    Given Aureliano is in the Main Screen
    When he enables the "Order Number" toggle
    Then he should see the "Enter the Order Number" is prompted in a Sale
    And he fills the prompt with 1
    And he gets a transaction approved validating its receipt

  #@ignore
  @printing
  Scenario: 3 - The clerk enables the Invoice Number prompting
    Given Aureliano is in the Main Screen
    When he enables the "Invoice Number" toggle
    Then he should see the "Enter the Invoice Number" is prompted in a Sale
    And he fills the prompt with 1
    And he gets a transaction approved validating its receipt
