@REGRESSION_APIGEE @LOBSTERS @RECIPES
Feature: Recipe Details GraphQL

  Scenario: Guest user requests for a recipe details
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user requests for "herb-and-garlic-sweet-potato-stacks" recipe details
    Then user is able to see recipe details

  Scenario: Logged in user requests for a recipe details
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
    When connection from user to apigee endpoint happens
    And user requests for "herb-and-garlic-sweet-potato-stacks" recipe details
    Then user is able to see recipe details