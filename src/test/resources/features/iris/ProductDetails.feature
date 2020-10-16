@REGRESSION_APIGEE @PRODUCT_DETAILS @Lobsters @IN_PROGRESS
Feature: Product details graphql

  Scenario: Guest user queries online products details for an available product
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    When user requests for online "Milk" products by search
    And user selects a product to get "ONLINE" product details
    Then online product details for the available product are satisfactory for user display

  Scenario: Logged in user queries online products details for an available product
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    When user requests for online "Milk" products by search
    And user selects a product to get "ONLINE" product details
    Then online product details for the available product are satisfactory for user display

  Scenario: Guest user queries instore products details for an available product
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    When user requests for instore "Bread" products by search for store "1649"
    And user selects a product to get "INSTORE" product details
    Then instore product details for the available product are satisfactory for user display

  Scenario: Logged in user queries instore products details for an available product
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    When user requests for instore "Bread" products by search for store "1649"
    And user selects a product to get "INSTORE" product details
    Then instore product details for the available product are satisfactory for user display