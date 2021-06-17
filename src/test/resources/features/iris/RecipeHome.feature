@REGRESSION_APIGEE @PROD_APIGEE @LOBSTERS @RECIPES
Feature: Recipe Home GraphQL

  Scenario: Guest user requests for a recipe home
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for recipe home
    Then user is able to see recipe home

  Scenario: Logged in user requests for a recipe home
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for recipe home
    Then user is able to see recipe home