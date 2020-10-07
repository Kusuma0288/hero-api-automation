@REGRESSION_REWARDS @CLOVER
Feature: Login safely and securely with an OTP

  Scenario: Reopen the app after token is expired
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When the user reopens the app
    Then the user should be able to enter the app

  Scenario: Unauthenticated user cannot login
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When an unauthorised user opens the app
    Then the user should not be able to enter the app