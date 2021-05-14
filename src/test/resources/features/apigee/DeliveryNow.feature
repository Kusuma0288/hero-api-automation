@Falcon @REGRESSION_APIGEE
Feature: Verify all the DeliveryNow related test cases

  @HOMEPAGE
  Scenario Outline: Verify the DeliveryNow Card in HomePage response for logged-in user in Delivery mode (DN Eligible address)
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I pick a location at "<Address>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    Then I make a request to Homepage in Delivery mode and verify the DeliveryNowCard for "<Eligibility>" address
    Examples:
      | Address                                       | Eligibility |
      | 13-19 Seven Hills Rd, BAULKHAM HILLS NSW 2153 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340           | Ineligible  |

  @HOMEPAGE
  Scenario Outline: Verify the DeliveryNow Card in HomePage response for logged-in user in In-Store mode (DN Eligible address)
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I pick a location at "<Address>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And I select an IN-STORE shop in postcode "2000"
    Then I make a request to Homepage in IN-STORE mode with store id "1248" and verify the DeliveryNowCard for "<Eligibility>" address
    Examples:
      | Address                                       | Eligibility |
      | 13-19 Seven Hills Rd, BAULKHAM HILLS NSW 2153 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340           | Ineligible  |

  @HOMEPAGE
  Scenario Outline: Verify the DeliveryNow Card in HomePage response for logged-in user in PickUp mode (DN Eligible address)
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I pick a location at "<Address>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And I set a pick up store using post code 2204
    Then I make a request to Homepage in "pickup" and verify the DeliveryNowCard for "<Eligibility>" address
    Examples:
      | Address                                       | Eligibility |
      | 13-19 Seven Hills Rd, BAULKHAM HILLS NSW 2153 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340           | Ineligible  |

  @CHECKOUT
  Scenario Outline: To verify the Delivery Now Status on checkout screen in Delivery Mode (for eligible and ineligible address)
    Given User logs in with username as "SHOPPER_USERNAME"
    And Selects Delivery Shopping Mode and set a Delivery Now eligible "<Address>"
    And I clear my trolley
    When I search for the product oil in online mode and store response
    And I add the 2 available products with 5 each from the store to the V3 trolley
    Then Validate Checkout response and Validate Delivery Now Status for "<Eligibility>" address in "Online" mode
    Examples:
      | Address                                       | Eligibility |
      | 13-19 Seven Hills Rd, BAULKHAM HILLS NSW 2153 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340           | Ineligible  |

  @CHECKOUT
  Scenario Outline: To verify the Delivery Now Status on checkout screen in Pickup Mode (for eligible and ineligible address)
    Given User logs in with username as "SHOPPER_USERNAME"
    And Selects Delivery Shopping Mode and set a Delivery Now eligible "<Address>"
    And Sets a pick up store using post code "2010"
    And I clear my trolley
    When I search for the product oil in online mode and store response
    And I add the 2 available products with 5 each from the store to the V3 trolley
    Then Validate Checkout response and Validate Delivery Now Status for "<Eligibility>" address in "Pickup" mode
    Examples:
      | Address                                       | Eligibility |
      | 13-19 Seven Hills Rd, BAULKHAM HILLS NSW 2153 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340           | Ineligible  |

  @CHECKOUT
  Scenario Outline: Login with existing user id and place Delivery Now Order
    Given User logs in with username as "SHOPPER_USERNAME"
    And Selects Delivery Shopping Mode and set a Delivery Now eligible "<Address>"
    And I clear the trolley
    When Adds following items to the cart
#    (less than max limit) to the cart including chilled/frozen items.
      | products            | quantity |
      | Oil                 | 20       |
    Then Navigates to trolley and validate the items are added as expected
    And Navigates to Checkout and Validate Delivery Now tile is available and enabled
    And Validates text on Delivery Now tile and text under Learn More section
    When Selects DeliveryNow Tile
    When I get the checkout summary details for the "<Mode1>" order
    And I complete the payment via saved paypal account
    And I verify the completed "<Mode1>" order
    Then I check my order details in order summary page
#   Implement Validations
    Examples:
      | Address                                       | Mode1    |
      | 13-19 Seven Hills Rd, BAULKHAM HILLS NSW 2153 | Delivery |

