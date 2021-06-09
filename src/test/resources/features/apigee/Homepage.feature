@HOMEPAGE @Wolves
Feature: Verify the HomePage components in different Shopping modes for guest/logged-in user

  @PROD_APIGEE
  Scenario: Verify the Homepage components for logged-in user without EDR and in IN-Store mode
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    And I select an IN-STORE shop in postcode "2000"
    Then I make a request to Homepage in IN-STORE mode with store id "1248" with "NoEDR" and verify the response for Offer "All Specials"

  @PROD_APIGEE
  Scenario: Verify the Homepage components for guest user without EDR and in IN-Store mode
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And I select an IN-STORE shop in postcode "2000"
    Then I make a request to Homepage in IN-STORE mode with store id "1248" with "NoEDR" and verify the response for Offer "All Specials"

  Scenario: Verify the Homepage components for guest user without EDR and in Delivery mode
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And I pick a location at "Darcy Road" for delivery
    Then I make a request to Homepage in "online" mode with "NoEDR" and verify the response for Offer "Online Only Offers"
    And I add the stockcode with quantity "2" to trolley
    Then I verify the stockcode is set to "2" when I make a request to Homepage
    And I clear the trolley
    Then I verify the stockcode is not added to trolley when I make a request to Homepage

  Scenario: Verify the Homepage components for guest user without EDR and in Online Pickup mode
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And I set a pick up store using post code 2000
    Then I make a request to Homepage in "pickup" mode with "NoEDR" and verify the response for Offer "Online Only Offers"
    And I add the stockcode with quantity "2" to trolley
    Then I verify the stockcode is set to "2" when I make a request to Homepage
    And I clear the trolley
    Then I verify the stockcode is not added to trolley when I make a request to Homepage

  @REGRESSION_APIGEE
  Scenario: Verify the PromoTile, add products to trolley and verify the status of the product in PromoTile for logged-in user
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME10"
    When connection from user to apigee endpoint happens
    And I pick a location at "Darcy Road" for delivery
    Then I make a request to Homepage in "online" mode with "NoEDR" and verify the response with PromoCard
    Then I navigate to PromoTile from Homepage in "online" mode
    And I add the stockcode with quantity "3" to trolley
    Then I verify the stockcode is set to "3" when I make a request to PromoTile
    And I clear the trolley
    Then I verify the stockcode is not added to trolley when I make a request to PromoTile

  @REGRESSION_APIGEE
  Scenario: Verify the PromoTile, add products to trolley and verify the status of the product in PromoTile for guest user
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And I pick a location at "Darcy Road" for delivery
    Then I make a request to Homepage in "online" mode with "NoEDR" and verify the response with PromoCard
    Then I navigate to PromoTile from Homepage in "online" mode
    And I add the stockcode with quantity "3" to trolley
    Then I verify the stockcode is set to "3" when I make a request to PromoTile
    And I clear the trolley
    Then I verify the stockcode is not added to trolley when I make a request to PromoTile
