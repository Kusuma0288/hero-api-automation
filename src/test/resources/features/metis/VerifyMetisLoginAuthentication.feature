@REGRESSION
Feature: Verify metis Link and Login Feature
  Test for the login feature using card number

  @IN_PROGRESS
  Scenario: Test the login of user to metis endpoint
    Given user can successfully get sessionToken
    Then user can login via authcode
    Then user can successfully login to Rewards App