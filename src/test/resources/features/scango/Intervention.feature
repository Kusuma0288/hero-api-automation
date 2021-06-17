Feature: Intervention

  Background:
    Given a user enter valid username and password in the rewards portal
    And a user calls the Rewards API with valid Authcode in the header
    And a user calls the Login API with valid AccessToken in the header
    When user successfully logged in
    And user calls the Start Shop API

  Scenario: Verify Intervention Item is removed from kiosk when cart has Simple and Age Restricted Item in cart
    Given I add a "Simple item" into cart
    And I add a "Age restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify Intervention Item is removed from kiosk when cart has TUN and Sale Restricted Item in cart
    Given I add a "TUN item" into cart
    And I add a "Sale restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify Intervention Item is removed from kiosk when cart has same Quantity Restricted Item in cart
    Given I add a "Tiliter Quantity item" into cart
    And I add a "Quantity restricted item_1" into cart
    And I add a "Tiliter Weight item" into cart
    And I add a "Quantity restricted item_1" into cart
    And I add a "Quantity restricted item_1" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify Intervention Item is removed from kiosk when cart has different Quantity Restricted Item in cart
    Given I add a "Simple item" into cart
    And I add a "Quantity restricted item_1" into cart
    And I add a "GS1 item" into cart
    And I add a "Quantity restricted item_2" into cart
    And I add a "Quantity restricted item_1" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify Intervention Item is removed from kiosk when cart has all types of Intervention items in cart
    Given I add a "Simple item" into cart
    And I add a "Quantity restricted item_1" into cart
    And I add a "Sale restricted item" into cart
    And I add a "Quantity restricted item_2" into cart
    And I add a "Quantity restricted item_1" into cart
    And I add a "Age restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify Intervention Item is removed from kiosk when cart has only Intervention items in cart
    Given I add a "Age restricted item" into cart
    And I add a "Sale restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    Then I verify "CANCELLED" status in UserProfile API