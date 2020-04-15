@SPECIALSPAGE @LOL @REGRESSION
Feature: Verify the Specials components in Online mode for logged-in user

  @PROD
  Scenario: Verify the Specials component for logged-in user and in IN-Store mode
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    Then I make a request to Speicals page in "ONLINE" mode and verify the response

  @PROD
  Scenario: Verify the Specials component for logged-in user and in IN-Store mode
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME"
    When connection from user to apigee endpoint happens
    Then I make a request to Speicals page in IN-STORE mode and with store id "1248" verify the response