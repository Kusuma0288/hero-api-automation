@REGRESSION_APIGEE @PRODUCT_DETAILS
Feature: Product details graphql

  @Wolves @PROD_APIGEE
  Scenario: Guest user queries online products details for an available product
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    When user requests for online "Milk" products by search
    And user selects a product to get "ONLINE" product details
    Then online product details are available

  @Wolves @PROD_APIGEE
  Scenario: Logged in user queries online products details for an available product
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    When user requests for online "Milk" products by search
    And user selects a product to get "ONLINE" product details
    Then online product details are available

  @VEGEMITE
  Scenario: Guest user queries instore products details for an available product
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    When user requests for instore "Bread" products by search for store "1649"
    And user selects a product to get "INSTORE" product details
    Then instore product details are available

  @VEGEMITE
  Scenario: Logged in user queries instore products details for an available product
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    When user requests for instore "Bread" products by search for store "1649"
    And user selects a product to get "INSTORE" product details
    Then instore product details are available