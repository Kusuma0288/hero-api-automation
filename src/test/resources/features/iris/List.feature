@REGRESSION_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Verify GraphQl List scenarios for User

  Background:
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    And connection from user to apigee endpoint happens

  Scenario Outline: Validate Synclist scenario for create, edit and delete, and get list of lists
    When I create a list with list name "<List Name 1>" and color "<color1>"
    Then I verify list is created with correct details
    And I get a list of lists
    And I verify created list by listId
    When user requests for online "Milk" products by search
    And I add 4 numbers of a Product from search result and free text "MilkA2" in the list
    Then I verify Product and free text is added with correct details
    When I edit a created list to list name "<List Name 2>" with color "<color2>"
    Then I verify list is Edited with correct details
    And I update Product quantity to 10 and free text "coke" in the list
    Then I verify Product and free text is updated with correct details
    Then I delete the product added to the list
    And I delete the newly created and edited list
    Examples:
      | List Name 1   | color1  | List Name 2    | color2  |
      | AutoListFirst | #00AB4E | AutoListSecond | #ED1C24 |
