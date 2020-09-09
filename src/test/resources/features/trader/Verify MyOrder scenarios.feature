Feature: Verify E2E scenario with v3 My orders API

  Scenario Outline: Validate the E2E scenario with v3 My order API
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    Then I search for the address <DeliveryAddress>
    And I select the "1" address as checkout address from matching addresses
    And I clear the trolley for the shopper
    And I search for the product "Milk" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    When apigee connect to trader public api endpoint from guest to logged in user with username <EmailAddress> and password
    When connection from apigee to trader public api shopper endpoint happens
    And I get the available delivery windows for the logged in user with storeId or addressId
    And I set the selected available delivery window for the logged in user
    Then I validate that the V3Checkout returns the selected window for the logged in user
    And I verify the V3 My Order details for Order status, Fulfillment method, Delivery address, Delivery date and TotalOrderCount
    Then I clear the checkout details
    Examples:
      | DeliveryAddress                    | EmailAddress |
      | 19 Rudd Street, Narellan, NSW 2567 | shopapp+57   |
