@ignore
Feature: Control the flow of transactions
  As a User
  I want to control the flow of transactions
  So that I can manage the transactions in my business

  #@ignore
  Scenario: The clerk enables the Order Number prompt
    Given Aureliano is in the Main Screen
    When he enables the Order Number prompt
    Then he see the Order Number prompt is enabled
