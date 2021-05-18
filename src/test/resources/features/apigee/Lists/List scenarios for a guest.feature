@REGRESSION_APIGEE @PROD_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Verify Apigee List scenarios for Guest


  Background:
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    And user successfully authenticate to apigee public api as guest

  Scenario Outline: Validate "<Version>" list to trolley sync scenario for a guest user in Pickup mode
    Given I set a pick up store using post code <Post Code>
    When I clear ALL the lists for the user
    And I clear the trolley
    And I create a list with exact list name as "<List Name>"
    And I search to add "<Quantity>" "<Product>" products to the "<Version>" list "<List Name>"
    Then I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And I add items to cart after selecting "<Quantity>" for every item from "<Version>" list "<List Name>"
    And I verify that the correct items with quantity from "<Version>" list "<List Name>" are added to the cart
    Examples:
      | Quantity | List Name      | Version | Post Code | Product |
      | 1        | AutoList Exact | V2      | 2067      | Milk    |
      | 1        | AutoList Exact | V3      | 2067      | Bread   |

  Scenario Outline: Validate "<Version>" list to trolley sync scenario for a guest user in Delivery mode
    Given I pick a location at "<Address>" for delivery
    When I clear ALL the lists for the user
    And I clear the trolley
    And I create a list with exact list name as "<List Name>"
    And I search to add "<Quantity>" "<Product>" products to the "<Version>" list "<List Name>"
    Then I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And I add items to cart after selecting "<Quantity>" for every item from "<Version>" list "<List Name>"
    And I verify that the correct items with quantity from "<Version>" list "<List Name>" are added to the cart
    Examples:
      | Quantity | List Name      | Version | Address    | Product |
      | 1        | AutoList Exact | V2      | Darcy Road | Milk    |
      | 1        | AutoList Exact | V3      | Darcy Road | Bread   |


  Scenario Outline: Validate "<Version>" list sync/merge for guest user when user logs in to the app in Delivery mode
    Given I pick a location at "<Address>" for delivery
    When I clear ALL the lists for the user
    And I create a list with exact list name as "<List Name>"
    And I search to add "<Quantity>" "<Product>" products to the "<Version>" list "<List Name>"
    And I add free text item "<Free Text Item>" to list and check the item to list
    Then user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    And I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And user verifies the free text item "<Free Text Item>" is added to "<List Name>" is checked
    Examples:
      | Quantity | List Name      | Version | Address    | Product | Free Text Item |
      | 1        | AutoList Exact | V2      | Darcy Road | Milk    | Coke           |
      | 1        | AutoList Exact | V3      | Darcy Road | Bread   | Coke           |
