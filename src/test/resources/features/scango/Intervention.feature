@ScanNGo @SMOKE_SCANGO  @REGRESSION_APIGEE
Feature: Intervention

  Background:
    Given I get Refresh Token from Firestore
    And a user calls the Refresh Token API to get new Access Token
    And I Update the Refresh Token to Firestore
    And user calls the Start Shop API

  Scenario: Verify Intervention Item is removed from kiosk when cart has only Intervention items in cart
    Given I add a "Age restricted item" into cart
    When I call Load cart API
    And I call Checkout API
    And I call Verify Cart API
    And I get Team Member barcode from Firestore Document
    And I call Team member barcode API to get team member access token
    And I call Kiosk Load Cart API
    Then I verify Intervention items are removed through Kiosk Remove Intervention API
    Then I verify Kiosk Checkout API throws valid error response
    Then I verify "CANCELLED" status in UserProfile API