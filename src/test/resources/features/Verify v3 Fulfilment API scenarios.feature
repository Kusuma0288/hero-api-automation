Feature: v3 Fulfilment API scenarios

  Scenario Outline:  To verify that the guest user is able to set an address as fulfilment address and
    Verify the transition from guest user to logged in user with an address ID
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And I search for the address "<lookupAddress>"
    And I select the "<position>" address as fulfilment address from matching addresses
    Then I make a request to fulfilment api with primary address id to set the address as fulfilment address
    And  user continue to connect to apigee with login username as "<Username>"
    When connection from user to apigee endpoint happens
    Then filter the address by address text and verify address saved is set as primary address in MyAccount
    Then I make a GET request to fulfilment api and verify the fulfilment address
    Examples:
      | lookupAddress | position | Username         |
      | Darcy Road    | 1        |SHOPPER_USERNAME8 |


  Scenario Outline:  To verify the fulfilment api response with invalid access token
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And I search for the address "<lookupAddress>"
    And I select the "<position>" address as fulfilment address from matching addresses
    Then I make a request with invalid address to fulfilment api with primary address id to set the address as fulfilment address
    Examples:
      | lookupAddress | position |
      | Darcy Road | 1 |

