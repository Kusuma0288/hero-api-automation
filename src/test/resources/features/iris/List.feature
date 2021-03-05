@REGRESSION_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Verify GraphQl List scenarios for User

  Background:
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    And connection from user to apigee endpoint happens

  Scenario Outline: Validate list scenario for graphql
    When I create a list with list name "<List Name 1>" and color "<color1>" and added FreeText "<FreeText>" and Product "<Quantity>" "<Product>"
    Then I verify list is created with correct details
    When I edit a created list to list name "<List Name 2>" with color "<color2>"
    Then I verify list is Edited with correct details
    And I delete the newly created and edited list
    Examples:
      | List Name 1    | color1 | Product  | Quantity   | FreeText| List Name 2    | color2 |
      | AutoListFirst  | Green  | 785455   | 11         | bread   | AutoListSecond | Red    |