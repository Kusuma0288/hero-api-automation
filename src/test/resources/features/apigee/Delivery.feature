@REGRESSION @DELIVERY @PROD @Wolves
Feature: Verify Apigee Delivery scenarios

  Scenario Outline: Verify whether user is able to add a delivery address and the recently added address is set as primary
    Given user continue to connect to apigee with login username as "<username>"
    When connection from user to apigee endpoint happens
    And I pick a location at "<lookupAddress>" for delivery
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
     Then verify the address saved is set as primary address in MyAccount
    Examples:
      | username          | lookupAddress |
      | SHOPPER_USERNAME1 | Darcy Road    |



