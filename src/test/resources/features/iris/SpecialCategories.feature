@REGRESSION_APIGEE @Lobsters @PRODUCT_CATEGORIES
Feature: Special Categories Graphql

  Scenario: Guest user queries specials online categories
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" sub-categories for store: ""
    And user requests for "SPECIALS" sub-categories for store: ""
    And user requests for "SPECIALS" sub-categories for store: ""
#    Then user can see products listed for that category


  Scenario: Guest user queries Specials In-Store categories
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" sub-categories for store: "5654"
    And user requests for "SPECIALS" sub-categories for store: "5654"
    And user requests for "SPECIALS" sub-categories for store: "5654"
#    Then user can see products listed for that sub-category


  Scenario: Logged-in user queries Specials Online categories
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" sub-categories for store: ""
    And user requests for "SPECIALS" sub-categories for store: ""
    And user requests for "SPECIALS" sub-categories for store: ""
#    Then user can see products listed for that sub-category

  Scenario: Logged-in user queries Specials In-Store categories
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "SPECIALS" sub-categories for store: "5654"
    And user requests for "SPECIALS" sub-categories for store: "5654"
    And user requests for "SPECIALS" sub-categories for store: "5654"
#    Then user can see products listed for that sub-category
