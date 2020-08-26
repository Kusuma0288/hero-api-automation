@REGRESSION @Login
Feature: Log in to the app

  Scenario: Login using auth code
    Given a user has a Link session token
    When the user logs in with their authcode
    Then the user should be successfully logged to the Rewards App