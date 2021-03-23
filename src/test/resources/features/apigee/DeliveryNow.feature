@Falcon @REGRESSION_APIGEE
Feature: Verify all the DeliveryNow related test cases

  @HOMEPAGE
  Scenario Outline: Verify the DeliveryNow Card in HomePage response for logged-in user in Delivery mode (DN Eligible address)
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I pick a location at "<address>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    Then I make a request to Homepage in Delivery mode and verify the DeliveryNowCard for "<eligibility>" address
    Examples:
      | address                                    | eligibility |
      | 407-419 Elizabeth St, SURRY HILLS NSW 2010 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340        | Ineligible  |

  @HOMEPAGE
  Scenario Outline: Verify the DeliveryNow Card in HomePage response for logged-in user in In-Store mode (DN Eligible address)
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I pick a location at "<address>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And I select an IN-STORE shop in postcode "2000"
    Then I make a request to Homepage in IN-STORE mode with store id "1248" and verify the DeliveryNowCard for "<eligibility>" address
    Examples:
      | address                                    | eligibility |
      | 407-419 Elizabeth St, SURRY HILLS NSW 2010 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340        | Ineligible  |


  @HOMEPAGE
  Scenario Outline: Verify the DeliveryNow Card in HomePage response for logged-in user in PickUp mode (DN Eligible address)
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I pick a location at "<address>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And I set a pick up store using post code 2204
    Then I make a request to Homepage in "pickup" and verify the DeliveryNowCard for "<eligibility>" address
    Examples:
      | address                                    | eligibility |
      | 407-419 Elizabeth St, SURRY HILLS NSW 2010 | Eligible    |
      | 31 Dean St, NORTH TAMWORTH NSW 2340        | Ineligible  |


