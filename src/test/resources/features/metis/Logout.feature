@REGRESSION_REWARDS @CLOVER
Feature: Log out of the App from Accounts section

  Scenario: Authenticated user can log out of account screen
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When the user logs out from the account screen
    Then the user should be logged out successfully
