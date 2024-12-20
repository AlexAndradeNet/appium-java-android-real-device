# @ignore
Feature: Solve course exercises
  As a User
  I want to test all the exercises
  So that I can practice the new knowledge

  #@ignore
  Scenario: The trader explores the menu
    Given Jacob is in the Main Screen
    When he opens the Sale main tile
    Then he see the numbers of items is 8

  @ignore
  Scenario: The clerk enables the Order Number prompt
    Given Jacob is in the Main Screen
    When he enables the Order Number prompt
    Then he see the Order Number prompt is enabled
