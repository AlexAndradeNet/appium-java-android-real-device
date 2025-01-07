#@ignore
Feature: Help Desk menu.
  As Sebastian (Support Technician),
  I want to control access to the help menu,
  so that users cannot damage critical information.

  #@ignore
  Scenario: 1. Restrict help menu access
    Given Aureliano is in the Main Screen,
    When he attempts to open the help menu with password 111111,
    Then he should see the message "Invalid Password" with description "Please verify and try again!".

  #@ignore
  Scenario: 2. Allow help desk access
    Given Sebastian is in the Main Screen,
    When he attempts to open the help menu with a calculated super-password,
    Then he should have access to the help menu.
