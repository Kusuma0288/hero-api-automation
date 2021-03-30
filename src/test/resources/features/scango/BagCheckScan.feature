Feature: Bag Check Scan

  Background:
    Given I get Refresh Token from Firestore
    And a user calls the Refresh Token API to get new Access Token
    And I Update the Refresh Token to Firestore
    And user calls the Start Shop API

  Scenario: Verify user is able to complete through in-app and Bag Check Scan matched
    Given I add a "Simple item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify the Bag Check Scan API is "matched" for the transaction

  Scenario: Verify user is able to complete through in-app and Bag Check Scan mismatched
    Given I add a "Price embedded item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify the Bag Check Scan API is "mismatched" for the transaction

  Scenario: Verify user is able to complete through Kiosk and Bag Check Scan matched
    Given I add a "TUN" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Kiosk Load Cart API
    And I call Kiosk Checkout API
    Then I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction
    And I verify the Bag Check Scan API is "matched" for the transaction

  Scenario: Verify user is able to complete through Kiosk and Bag Check Scan mismatched
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
    And I verify the Bag Check Scan API is "mismatched" for the transaction

  Scenario: Verify user is able to complete through in-app and Skip Bag Check Scan
    Given I add a "Simple item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify the Skip Bag Check Scan API for the transaction

  Scenario: Verify user is not prompted for Bag check when user's cart has Tiliter Weight Required Item
    Given I add a "TILITER_WEIGHT_ITEM" into cart
    When I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify the "bagcheckrequiredflag" in Verify Cart API

  Scenario: Verify user is not prompted for Bag check when user's cart has Shelf Label Bakery Items
    Given I add a "Shelf Label item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify the "bagcheckrequiredflag" in Verify Cart API

  Scenario: Verify user is not prompted for Bag check when user's cart has only Intervention item
    Given I add a "Age restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I verify the "bagcheckrequiredflag" in Verify Cart API