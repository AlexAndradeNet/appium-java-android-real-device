#@ignore
Feature: Control the flow of transactions.
  As a User,
  I want to control the flow of transactions,
  So that I can manage the transactions in my business.

  #@ignore
  Scenario: 1: The clerk enables the Order Number prompting.
    Given Aureliano is in the Main Screen,
    When he enables the Order Number toggle,
    Then he see the Order Number prompt is prompted in Sales.
