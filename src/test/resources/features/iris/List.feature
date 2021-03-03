@REGRESSION_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Verify GraphQl List scenarios for User

  Background:
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    And connection from user to apigee endpoint happens

  Scenario Outline: Validate list scenario for graphql
    When I clear ALL the lists for the user
    And I create a list with list name "<List Name 1>" and color "<color>" and added FreeText "<FreeText>" and Product "<Quantity>" "<Product>"
    Then I verify list is created with correct details
    Examples:
      | List Name 1 | color | Product  | Quantity   | FreeText|
      | AutoList12  | Green | 785455   | 11         | bread   |