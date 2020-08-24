@REGRESSION @Wolves
Feature: Checkout API scenarios

  Background:
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    When connection from user to apigee endpoint happens

  Scenario Outline: Verify getting and setting of v3/checkout windows and Packaging preference for Pick up mode
    Given I set a pick up store using post code 2204
    And I clear the trolley
    When I search for the product Nivea in pickup mode and store response
    And I add the 3 available products with 3 each from the store to the V3 trolley
    And I get the available windows for the logged in user with storeId or addressId
    Then I reserve the available window for the selected "<Mode>"
    Then I validate that user is able to select Reusable bags as packaging preference
    When I get the checkout summary details for the "<Mode>" order
    Then I validate the selected "<Mode>" and selected windows
    Then I validate the product subtotal and total GST
    And I validate the packaging fee and preference
    And I make a payment using CREDIT-CARD

    Examples:
      | Mode   |
      | Pickup |

  Scenario Outline:  Verify getting and setting of v3/checkout windows and Packaging preference for Delivery mode
    Given I pick a location at "<lookupAddress>" for delivery
    And I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And I clear the trolley
    When I search for the product Nivea in online mode and store response
    And I add the 5 available products with 5 each from the store to the V3 trolley
    And I get the available windows for the logged in user with storeId or addressId
    Then I reserve the available window for the selected "<Mode>"
    And I validate that user is able to select Reusable bags as packaging preference
    When I get the checkout summary details for the "<Mode>" order
    Then I validate the selected "<Mode>" and selected windows
    Then I validate the product subtotal and total GST
    And I validate the packaging fee and preference
    And I validate the leave unattended flag to be enabled
    And I make a payment using CREDIT-CARD

    Examples:
      | Mode     | lookupAddress |
      | Delivery | Darcy Road    |


  Scenario Outline: To verify that the leave unattended flag is disabled when user selects a delivery now window
    Given I pick a location at "<lookupAddress>" for delivery
    And I make a request to fulfilment api with primary address id to set the address as fulfilment address
    When I search for the product eggs in online mode and store response
    And I add the 2 available products with 1 each from the store to the V3 trolley
    And I get the available Delivery Now window to reserve them and validate the leave unattended flag

    Examples:
      | lookupAddress |
      | 2 Court rd    |

  Scenario Outline: To verify that the user is able to complete a pick up order using Paypal payment
    Given I set a pick up store using post code 2204
    And I clear the trolley
    When I search for the product Milk in pickup mode and store response
    And I add the 5 available products with 5 each from the store to the V3 trolley
    And I get the available windows for the logged in user with storeId or addressId
    Then I reserve the available window for the selected "<Mode>"
    Then I validate that user is able to select BYO as packaging preference
    When I get the checkout summary details for the "<Mode>" order
    And I complete the payment via saved paypal account
    And I verify the completed "<Mode>" order

    Examples:
      | Mode   |
      | Pickup |

  Scenario Outline: To verify that the user is able to complete a delivery order using Paypal payment
    Given I pick a location at "<lookupAddress>" for delivery
    And I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And I clear the trolley
    When I search for the product Milk in online mode and store response
    And I add the 5 available products with 5 each from the store to the V3 trolley
    And I get the available windows for the logged in user with storeId or addressId
    Then I reserve the available window for the selected "<Mode>"
    And I validate that user is able to select Reusable bags as packaging preference
    When I get the checkout summary details for the "<Mode>" order
    And I complete the payment via saved paypal account
    And I verify the completed "<Mode>" order
    Examples:
      | Mode     | lookupAddress |
      | Delivery | Darcy Road    |
