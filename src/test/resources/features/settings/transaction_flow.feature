#@ignore
Feature: Control the flow of transactions.
  As a User,
  I want to control the flow of transactions,
  So that I can manage the transactions in my business.

  #@ignore
  Scenario: 1: Setup - Disable all prompts in flow.
    Given Aureliano is in the Main Screen,
    When he enables the "Order Number" toggle,
    Then he should see the "Enter the Order Number" is prompted in Sales.
