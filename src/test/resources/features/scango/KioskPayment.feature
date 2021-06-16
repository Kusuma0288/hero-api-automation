@SCAN_N_GO @SMOKE_SCANGO @REGRESSION_APIGEE
Feature: Kiosk payment

  Background:
    Given I get Refresh Token from Firestore
    And a user calls the Refresh Token API to get new Access Token
    And I Update the Refresh Token to Firestore
    And user calls the Start Shop API

  Scenario: Verify user is able to complete payment through Kiosk for voluntary transfer
    Given I add a "Simple item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Kiosk Load Cart API
    And I call Kiosk Checkout API
    Then I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify user is able to complete payment through Kiosk when In-app payment limit exceeds
    Given I add a "Simple item" into cart
    And I add a "Tun item" into cart
    And I add a "Price embedded item" into cart
    And I add a "Tiliter Quantity item" into cart
    And I add a "Tiliter Weight item" into cart
    And I add a "Shelf Label item" into cart
    And I add a "GS1 item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I call Kiosk Checkout API
    Then I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify user is able to complete payment through Kiosk when a cart has an Intervention item
    Given I add a "Simple item" into cart
    And I add a "Age restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I get Team Member barcode from Firestore Document
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify Team member is able to void the transaction at Kiosk when a cart has an Intervention item
    Given I add a "Simple item" into cart
    And I add a "Age restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I get Team Member barcode from Firestore Document
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    And I call Reason code API to get valid reason
    And I verify transaction is voided through Kiosk Void Transaction API

  Scenario: Verify user is able to complete payment through Kiosk when In-App Payment failed twice
    Given I add a "Simple item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Payment API with invalid Instruments ID twice
    And I call Verify Cart API
    And I call Kiosk Load Cart API
    And I call Kiosk Checkout API
    And I verify payment is successfully completed through Kiosk Payment API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction