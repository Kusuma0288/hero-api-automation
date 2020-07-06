@REGRESSION @Lobsters
Feature: Verify Apigee Guest and Login Authentication
  Test for the guest/login feature

  Scenario: Test the login of guest user
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user successfully authenticate to apigee public api as guest
    And user continue to connect to apigee with default login and password
    Then user is successfully authenticated with all session details

  Scenario: Test the transitioning of guest user to invalid Shopper Login
    Given mobile user connect to apigee endpoint as guest
    When user successfully authenticate to apigee public api as guest
    And user continue to connect to apigee with login nouser@automation.com and password 123456
    Then user fails to authenticate as shopper

  Scenario: Testing the Invalid Auth Key Checking for Login
    Given user continue to connect to apigee with default login and password using apikey 1234567890
    Then user fails to authenticate as apikey is invalid

  Scenario: Test the Shopper Login failure with all session fields
    Given user continue to connect to apigee with login nouser@automation.com and password 123456
    When connection from user to apigee endpoint fails
    Then user fails to authenticate as shopper