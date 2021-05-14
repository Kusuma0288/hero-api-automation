@WEEKLYPICKS  @REGRESSION_TRADER @Wolves
Feature: Verify Products weekly picks Api

  @SMOKE_TRADER
  Scenario: Login as Shopper and verify the Products weekly picks api successful response with a list of products
    #WeeklyPick Api is a test data specific please do not reuse ,change data or remove rewards cards from this user.
    Given apigee continue to connect to trader public api endpoint with login test123456@lol.com and password 123456
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When customer calls trader weekly picks api
    Then verify trader weekly picks api responds successfully with list of products
