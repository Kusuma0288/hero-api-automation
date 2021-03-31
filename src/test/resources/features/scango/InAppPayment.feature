@SMOKE_SCANGO
Feature: In-App payment

  Background:
    Given I get Refresh Token from Firestore
    And a user calls the Refresh Token API to get new Access Token
    And I Update the Refresh Token to Firestore
    And user calls the Start Shop API

  Scenario: Verify user is able to complete payment through In-App
    Given I add a "Simple item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify user is able to Add and Update Tiliter Quantity Item
    Given I add a "Tun item" into cart
    And I add a "Tiliter Quantity item" into cart
    When I call Update Quantity API
    And I call Load cart API
    And I call Checkout API
    And I call List Instruments API
    Then I verify payment is successfully completed through In-App Payment API
    And I call Verify Cart API
    And I verify transaction is available in the Transaction History API
    And I verify the View receipt API for the transaction

  Scenario: Verify user is able to Remove item and Delete the transaction
    Given I add a "Price embedded item" into cart
    And I add a "Simple item" into cart
    When I call Remove Item API
    And I call Delete Transaction API
    Then I verify "CANCELLED" status in UserProfile API
