@REGRESSION @DELIVERY @PROD
Feature: Verify Apigee Delivery scenarios

  Scenario Outline: Verify whether user is able to add a delivery address and the recently added address is set as primary
    Given user continue to connect to apigee with login username as "<username>"
    When connection from user to apigee endpoint happens
    And I pick a location at "<lookupAddress>" for delivery
     Then verify the address saved is set as primary address in MyAccount
    Examples:
      | username          | lookupAddress | position |
      | SHOPPER_USERNAME1 | Darcy Road    | 1        |



