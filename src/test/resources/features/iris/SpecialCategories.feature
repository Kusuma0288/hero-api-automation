@REGRESSION_APIGEE @VEGEMITE @PRODUCT_CATEGORIES
Feature: Special Categories GraphQL

  Scenario: Guest user recursively requests subcategories for online specials
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" subcategories for store: ""
    And user requests for "SPECIALS" subcategories for store: ""
    Then user has not yet reached the final subcategory
    And user requests for "SPECIALS" subcategories for store: ""
    Then user reaches the final subcategory

  Scenario: Guest user recursively requests subcategories for in-store specials
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" subcategories for store: "5654"
    And user requests for "SPECIALS" subcategories for store: "5654"
    Then user has not yet reached the final subcategory
    And user requests for "SPECIALS" subcategories for store: "5654"
    Then user reaches the final subcategory

  Scenario: Logged in user recursively requests subcategories for online specials
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" subcategories for store: ""
    And user requests for "SPECIALS" subcategories for store: ""
    Then user has not yet reached the final subcategory
    And user requests for "SPECIALS" subcategories for store: ""
    Then user reaches the final subcategory

  Scenario: Logged in user recursively requests subcategories for in-store specials
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" subcategories for store: "5654"
    And user requests for "SPECIALS" subcategories for store: "5654"
    Then user has not yet reached the final subcategory
    And user requests for "SPECIALS" subcategories for store: "5654"
    Then user reaches the final subcategory
