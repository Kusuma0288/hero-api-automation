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

