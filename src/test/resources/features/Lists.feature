@REGRESSION @LIST @LION @SMOKE
Feature: Verify Apigee List scenarios

  Scenario Outline: Verify whether user is able to create List and switch the created List to default List
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    When connection from user to apigee endpoint happens
    And I search for the address "<address>"
    And I select the "1" address as fulfilment address from matching addresses
    And I clear ALL the list for the user
    And I create a list with list name as "<List Name 1>"
    And I create a list with list name as "<List Name 2>"
    And I switch the default list to "<List Name 1>"
    Then I verify list "<List Name 1>" is set as default list
    Examples:
      | address    | List Name 1 | List Name 2 |
      | Darcy Road | AutoList12  | AutoList23  |

  Scenario Outline: Create List and add free text item and Verify whether user is able to delete the item and list
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME7"
    When connection from user to apigee endpoint happens
    And I search for the address "<address>"
    And I select the "1" address as fulfilment address from matching addresses
    And I clear ALL the list for the user
    And I create a list with exact list name as "<List Name>"
    And I add free text item "<Free Text Item>" to list
    And user verifies the free text item "<Free Text Item>" is added to list
    Then user deletes free text item "<Free Text Item>" from the new list
    And I delete the newly created list
    Examples:
      | address    | List Name      | Free Text Item |
      | Darcy Road | AutoList Exact | Coke           |


  Scenario Outline: Create List and check / uncheck free text item to the list
    Given user continue to connect to apigee with login containing apigee99
    When connection from user to apigee endpoint happens
    And I search for the address "<address>"
    And I select the "1" address as fulfilment address from matching addresses
    And I clear ALL the list for the user
    And I create a list with exact list name as "<List Name>"
    And I add free text item "<Free Text Item>" to list and check the item to list
    Then user verifies the free text item "<Free Text Item>" is added to list and is checked
    When user un-checks the free text item "<Free Text Item>" from the newly created list
    Then user verifies the free text item "<Free Text Item>" is added to "<List Name>" is unchecked
    And checks free text item "<Free Text Item>" from the "<List Name>"
    Then user verifies the free text item "<Free Text Item>" is added to "<List Name>" is checked
    Examples:
      | address    | List Name      | Free Text Item |
      | Darcy Road | AutoList Exact | Coke           |

  Scenario Outline: Validate "<Version>" list to trolley merge scenario for a logged in user
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME7"
    When connection from user to apigee endpoint happens
    And I search for the pickup stores in the postcode 2204
    And I set the fulfilmentMethod to "<Fulfillment>" for the 1 store
    And I clear ALL the list for the user
    And I clear the trolley
    And I create a list with exact list name as "<List Name>"
    And I search for the product Milk in pickup mode and store response
    And I add 2 available products with "<Quantity>" each from the store to "<Version>" list "<List Name>"
    Then I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And I add items to cart after selecting "<Quantity>" for every item from "<Version>" list "<List Name>"
    Then I verify that the correct items with quantity from "<Version>" list "<List Name>" are added to the cart
    Examples:
      | Quantity | List Name      | Fulfillment | Version |
      | 1        | AutoList Exact | Pickup      | V2      |
      | 1        | AutoList Exact | Pickup      | V3      |

  Scenario Outline: Validate "<Version>" list to trolley merge scenario for a logged in user
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME7"
    When connection from user to apigee endpoint happens
    And I search for the pickup stores in the postcode <Post Code>
    And I set the fulfilmentMethod to "<Fulfillment>" for the 1 store
    And I clear ALL the list for the user
    And I clear the trolley
    And I create a list with exact list name as "<List Name>"
    And I search for the product <Product> in <Fulfillment> mode and store response
    And I add 2 available products with "<Quantity>" each from the store to "<Version>" list "<List Name>"
    Then I verify that the items saved to "<Version>" list "<List Name>" are unchecked
    And I add items to cart after selecting "<Quantity>" for every item from "<Version>" list "<List Name>"
    Then I verify that the correct items with quantity from "<Version>" list "<List Name>" are added to the cart
    Examples:
      | Quantity | List Name      | Fulfillment | Version | Post Code | Product |
      | 1        | AutoList Exact | Pickup      | V2      | 2204      | Milk    |
      | 1        | AutoList Exact | Pickup      | V3      | 2204      | Milk    |
