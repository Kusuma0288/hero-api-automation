@REGRESSION_APIGEE @Lobsters @PRODUCT_CATEGORIES
Feature: Product Categories Graphql

  Scenario: Guest user queries Product Online categories
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "PRODUCTS" sub-categories for store: ""
    And user requests for "PRODUCTS" sub-categories for store: ""
    And user requests for "PRODUCTS" sub-categories for store: ""
#    Then user can see products listed for that sub-category

  Scenario: Guest user queries Product In-Store categories
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "PRODUCTS" sub-categories for store: "5654"
    And user requests for "PRODUCTS" sub-categories for store: "5654"
    And user requests for "PRODUCTS" sub-categories for store: "5654"
#    Then user can see products listed for that sub-category

  Scenario: Logged-in user queries Product Online categories
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "PRODUCTS" sub-categories for store: ""
    And user requests for "PRODUCTS" sub-categories for store: ""
    And user requests for "PRODUCTS" sub-categories for store: ""
#    Then user can see products listed for that sub-category

  Scenario: Logged-in user queries Product In-Store categories
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "PRODUCTS" sub-categories for store: "5654"
    And user requests for "PRODUCTS" sub-categories for store: "5654"
    And user requests for "PRODUCTS" sub-categories for store: "5654"
#    Then user can see products listed for that sub-category
