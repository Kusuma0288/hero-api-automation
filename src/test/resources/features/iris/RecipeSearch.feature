@REGRESSION_APIGEE @LOBSTERS @RECIPES
Feature: Recipes search which is used by Recipe feed/List screen

  Background:
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens

  Scenario Outline: Guest user can search for recipes by source and tag
    When user search for source: "<Source>" and tag: "<Tags>"
    Then user can see the recipe search results
    Examples:
      | Source   | Tags              |
      | JO       |                   |
      |          |  Asian            |
      | FI       |  Dinner           |