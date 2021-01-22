@SMOKE_SCANGO
Feature: In-App payment

  Background:
    Given a user enter valid username and password in the rewards portal
    And a user calls the Rewards API with valid Authcode in the header
    And a user calls the Login API with valid AccessToken in the header
    When user successfully logged in
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
#
#  Scenario Outline: Verify user is able to Add and Update Tiliter Quantity Item
#    Given I add a "<Tun item>" into cart
#    And I add a "<Tiliter Quantity item>" into cart
#    When I call Update Quantity API
#    And I call Load cart API
#    And I call Checkout API
#    And I call List Instruments API with "valid" Instrument ID
#    Then I verify payment is successfully completed through "In-App" Payment API
#    And I call Verify Cart API
#    And I verify transaction is available in the Transaction History API
#    And I verify the View receipt API for the transaction
#
#    Examples:
#      | Tun item       | Tiliter Quantity item |
#      | 19300605021799 |TQ000073803381         |
#
#  Scenario Outline: Verify user is able to Add and Update Tiliter Weight required Item
#    Given I add a "<Price embedded item>" into cart
#    And I add a "<Tiliter Weight item>" into cart
#    When I call Update Weight API
#    And I call Load cart API
#    And I call Checkout API
#    And I call List Instruments API with "valid" Instrument ID
#    Then I verify payment is successfully completed through "In-App" Payment API
#    And I call Verify Cart API
#    And I verify transaction is available in the Transaction History API
#    And I verify the View receipt API for the transaction
#
#    Examples:
#      |Price embedded item       | Tiliter Weight item   |
#      | 19300605021799           | TW00700133211         |
#
#
#  Scenario Outline: Verify user is able to Remove item and Delete the transaction
#    Given I add a "<Simple item>" into cart
#    And I add a "<Tiliter Weight item>" into cart
#    When I call Remove Item API
#    And I call Delete Transaction API
#    Then I verify "NO_ACTIVE_CART" status in UserProfile API
#
#    Examples:
#      |Simple item        | Tiliter Weight item   |
#      | 9300633031015     | TW00700133211         |

