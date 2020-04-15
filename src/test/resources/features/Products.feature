@REGRESSION @PRODUCTS @PROD
Feature: Verify products by aisle and category

  Scenario: Verify products by aisle and category in INSTORE mode with type as specials
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME2"
    When connection from user to apigee endpoint happens
    And I search for an IN-STORE with postcode "2000" and I select the "1" store from matching in-stores
    And I make a request to V3 categories with type as Specials
    And I make a request to V2 products with specials and verify the response

  Scenario: Verify products by aisle and category in INSTORE mode without type as specials
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME3"
    When connection from user to apigee endpoint happens
    And I search for an IN-STORE with postcode "2000" and I select the "1" store from matching in-stores
    And I make a request to V3 categories without type as Specials
    And I make a request to V2 products without specials and verify the response


