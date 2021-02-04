@LION
Feature: Verify V3 Checkout scenarios

  @REGRESSION_TRADER
  Scenario Outline: Validate that customer as a guest
  set the 'delivery' mode and select a delivery address
  add the products to trolley as a guest
  login as a valid user and select a available date/time [/v2/checkout/windows]
  validate that selected delivery date and time are reflected in V3 checkout response

    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    Then I search for the address <DeliveryAddress>
    And I select the "1" address as checkout address from matching addresses
    And I clear the trolley for the shopper
    And I search for the product "Milk" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    When apigee connect to trader public api endpoint from guest to logged in user with username <EmailAddress> and password
    When connection from apigee to trader public api shopper endpoint happens
    And I get the addresses for my account
    And I get the available delivery windows for the logged in user with storeId or addressId
    And I set the selected available delivery window for the logged in user
    Then I validate that the V3Checkout returns the selected window for the logged in user
    Then I validate that the fulfilmentMethod and DeliveryMethod match to Courier for delivery mode in V3Checkout response
    Then I clear the checkout details
    Examples:
      | DeliveryAddress              | EmailAddress      |
      | 1 Darcy Rd, PORT KEMBLA 2505 | SHOPPER_WOLVES1   |


  @REGRESSION_TRADER
  Scenario Outline: Validate that customer as a guest
  set the 'pickup' mode and select a pickup store
  add the products to trolley as a guest
  login as a valid user and select a available date/time [/v2/checkout/windows]
  validate that selected pickup date and time are reflected in V3 checkout response

    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    And I clear the trolley for the shopper
    And I search for the product "Milk" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    When apigee connect to trader public api endpoint from guest to logged in user with username <EmailAddress> and password
    When connection from apigee to trader public api shopper endpoint happens
    Then I search for the pickup stores in the postcode <PostCode>
    And I select the "1" pickup store as checkout pickup store from matching stores for the logged in user
    And I get the available pickup windows for the logged in user with storeId or addressId
    And I set the selected available delivery window for the logged in user
    Then I validate that the V3Checkout returns the selected window for the logged in user
    Then I clear the checkout details
    Examples:
      | PostCode | EmailAddress      |
      | 2000     | SHOPPER_WOLVES2   |

  Scenario: Validate the E2E scenario with Paypal Payment
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME8 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address 19 Rudd Street Narellan
    And I select the "1" address as checkout address from matching addresses
    When I clear the trolley for the shopper
    And I search for the product "Nivea" and should see more than 1 matching results
    And I add 10 items to my trolley from search list with 10 quantity each
    And I get the available delivery windows for the logged in user with storeId or addressId
    And I set the selected available delivery window for the logged in user
    And I add wait for 5000 msecs to make sure the sync is successful
    Then I validate that the V3Checkout returns the selected window for the logged in user
    And I make a checkout to place an order
    And I set the payment as Paypal and make a payment
    And I verify the order details for Courier method
    Then I clear the checkout details

  Scenario Outline: Login as Shopper and verify for the delivery now and leave unattended
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME1 and password
    When apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for address "<Address>" without saving for future lookup
    And I select the "1" address as checkout address from matching addresses
    And I could not leave the products unattended
    And I clear the trolley for the shopper
    And I search for the product "Milk" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    And I get the available delivery now windows for the logged in user
    And I set the selected available delivery now window for the logged in user
    Then I validate that the V3Checkout returns the selected window for the delivery now user
    Then I clear the checkout details
    Examples:
      | Address                         |
      | 2 Court Rd, DOUBLE BAY NSW 2028 |

