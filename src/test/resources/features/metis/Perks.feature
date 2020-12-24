@REGRESSION_REWARDS @CLOVER
Feature: Customers should be able to engage with perks

  Scenario: Customer opens the perks section
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user taps on the extra page
    Then the user should be able to see his perks