@REGRESSION_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Verify Apigee List scenarios for User


  Background:
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    And connection from user to apigee endpoint happens

  Scenario Outline: Verify whether user is able to create List and switch the old List to default
    Given I set a pick up store using post code <Post Code>
    When I clear ALL the lists for the user
    And I create a list with list name as "<List Name 1>"
    And I create a list with list name as "<List Name 2>"
    And I switch the default list to "<List Name 1>"
    Then I verify list "<List Name 1>" is set as default list
    Examples:
      | Post Code | List Name 1 | List Name 2 |
      | 2204      | AutoList12  | AutoList23  |

  @PROD_APIGEE
  Scenario Outline: Create List and add free text item and Verify whether user is able to delete the item and list
    Given I pick a location at "<Address>" for delivery
    When I clear ALL the lists for the user
    And I create a list with exact list name as "<List Name>"
    And I add free text item "<Free Text Item>" to list
    And user verifies the free text item "<Free Text Item>" is added to list
    Then user deletes free text item "<Free Text Item>" from the new list
    And I delete the newly created list
    Examples:
      | Address    | List Name      | Free Text Item |
      | Darcy Road | AutoList Exact | Coke           |


  Scenario Outline: Create List and check / uncheck free text item to the list
    Given I pick a location at "<Address>" for delivery
    When I clear ALL the lists for the user
    And I create a list with exact list name as "<List Name>"
    And I add free text item "<Free Text Item>" to list and check the item to list
    And user verifies the free text item "<Free Text Item>" is added to list and is checked
    And user un-checks the free text item "<Free Text Item>" from the newly created list
    Then user verifies the free text item "<Free Text Item>" is added to "<List Name>" is unchecked
    And checks free text item "<Free Text Item>" from the "<List Name>"
    And user verifies the free text item "<Free Text Item>" is added to "<List Name>" is checked
    Examples:
      | Address    | List Name      | Free Text Item |
      | Darcy Road | AutoList Exact | Coke           |

  Scenario Outline: Validate "<Version>" list to trolley sync scenario for a logged in user in Pickup Mode
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
      | 1        | AutoList Exact | V3      | 2067      | Milk    |

  Scenario Outline: Validate "<Version>" list to trolley merge scenario for a logged in user in Delivery Mode
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