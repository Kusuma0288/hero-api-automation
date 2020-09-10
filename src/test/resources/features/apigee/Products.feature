@REGRESSION_APIGEE @PRODUCTS @PROD_APIGEE @Lobsters
Feature: Verify products by aisle and category

  Scenario: Verify products by aisle and category in INSTORE mode with type as specials
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME2"
    When connection from user to apigee endpoint happens
    And I select an IN-STORE shop in postcode "2000"
    And I make a request to V3 categories with type as Specials
    And I make a request to V2 products with specials and verify the response

  Scenario: Verify products by aisle and category in INSTORE mode without type as specials
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME3"
    When connection from user to apigee endpoint happens
    And I select an IN-STORE shop in postcode "2000"
    And I make a request to V3 categories without type as Specials
    And I make a request to V2 products without specials and verify the response


