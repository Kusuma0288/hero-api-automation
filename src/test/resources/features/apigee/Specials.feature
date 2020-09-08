@SPECIALSPAGE @Lobsters @REGRESSION_APIGEE
Feature: Verify the Specials components in Online mode for logged-in user

  Scenario: Verify the "/wow/v2/specials" Specials component and the list of products ("/wow/v2/products") under a Specials Group for logged-in user and in IN-Store mode
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    Then I make a request to Specials page in "ONLINE" mode and verify the response
    Then I make a request to Products API to filter the products based on "0" Specials group in "ONLINE" mode
    And I add the stockcode with quantity "2" to trolley
    And I clear the trolley

  @PROD_APIGEE
  Scenario: Verify the "/wow/v2/specials" Specials component and the list of products ("/wow/v2/products") under a Specials Group for logged-in user and in IN-Store mode
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    Then I make a request to Specials page in IN-STORE mode and with store id "1248" verify the response
    Then I make a request to Products API to filter the products based on "1" Specials group for store "1248"