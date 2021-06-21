@REGRESSION_TRADER @SHOPPER_LOGIN @GUEST_TO_SHOPPER @PROD_TRADER @EAGLE @SMOKE_TRADER
Feature: Verify Customer login authentication
  Test for the login feature if working in all possible modes

  Scenario: Test the transitioning of guest user to V2 Shopper Login
    Given apigee connect to trader public api endpoint as guest
    And apigee successfully authenticate to trader public api endpoint as guest
    When apigee continue to connect to trader public api endpoint with default login and password
    Then apigee successfully authenticate to trader public api endpoint as shopper with all session details

  Scenario: Test the V2 Shopper Login with all session fields
    Given apigee connect to trader public api endpoint with default login and password
    When connection from apigee to trader public api shopper endpoint happens
    Then apigee successfully authenticate to trader public api endpoint as shopper with all session details

  Scenario: Test the V2 Shopper Login with default username and password
    Given apigee connect to trader public api endpoint with default login and password
    Then apigee successfully authenticate to trader public api endpoint as shopper with all session details

  Scenario: Test the V2 Shopper Login failure with all session fields
    Given apigee connect to trader public api endpoint with login traderapi1@automation.com and password 123456
    When connection from apigee to trader public api shopper endpoint happens
    Then apigee failed to authenticate to trader public api endpoint as shopper


  Scenario: Test the transitioning of  guest user to invalid V2 Shopper Login
    Given apigee connect to trader public api endpoint as guest
    And apigee successfully authenticate to trader public api endpoint as guest
    When apigee continue to connect to trader public api endpoint with login nouser@automation.com and password 123456
    Then apigee failed to authenticate to trader public api endpoint as shopper

  Scenario: Testing the Invalid Auth Key Checking for Login
    Given apigee connect to trader public api endpoint with default login and password using apikey 1234567890
    Then apigee failed to authenticate apikey to trader public api endpoint
