@REGRESSION
Feature: Verify Redemption Settings
  Test for the Redemption Feature

  Scenario: Test the redemption Settings endpoint with valid access token
    Given a user has a Link session token
      And the user logs in with their authcode
      And the user should be logged into the Rewards App
    When the user makes a request for the redemption settings
    Then user should be able to choose how is rewarded