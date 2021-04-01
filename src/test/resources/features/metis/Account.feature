@REGRESSION_REWARDS @CLOVER
Feature: Account tab provides user to manage their rewards account

  Scenario: User selects the Rewards Account
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects the account screen
    Then the user should be able to see the account details