@REGRESSION @TROLLEY @LION @SMOKE
Feature: Verify Apigee Trolley functions
@PROD
  Scenario: Searching the product and adding to V3 trolley
  Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    When connection from user to apigee endpoint happens
    And I clear the trolley
    Then I search for the pickup stores in the postcode 2204
    Then I set the fulfilmentMethod to "Pickup" for the 1 store
    When I search for the product Milk in pickup mode and store response
    And I add the 2 available products with 1 each from the store to the V3 trolley

  Scenario: Searching the product and adding to V2 trolley
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME7"
    When connection from user to apigee endpoint happens
    And I clear the trolley
    Then I search for the pickup stores in the postcode 2204
    Then I set the fulfilmentMethod to "Pickup" for the 1 store
    When I search for the product Milk in pickup mode and store response
    And I add the 2 available products with 1 each from the store to the V2 trolley

  @IN_PROGRESS
  Scenario: Verify get items from V3 trolley
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME7"
    When connection from user to apigee endpoint happens
    And I clear the trolley
    And I set a pick up store
    And I add some items to the trolley
    Then I should be able to successfully view the items in my trolley