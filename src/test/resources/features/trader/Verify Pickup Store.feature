@PICKUP_STORE @REGRESSION @Wolves
Feature: Verify the pickup store
  Test for the pickup store structure

  @SMOKE @ProdRegression
  Scenario: Login as Guest and check the pickup structure by suburb
    Given apigee connect to trader public api endpoint as guest
    And connection from apigee to trader public api endpoint happens
    When apigee successfully authenticate to trader public api endpoint as guest
    And verify the pickup stores in the "CENTRAL" suburb

  Scenario Outline: Verify setting and verification for DeliveryMethod of store with apis/v2/pickupstores for Guest Login for V3 with Fulfilment Store Id
    Given apigee connect to trader public api endpoint as guest with fulfilment store id <StoreId>
    When connection from apigee to trader public api endpoint happens
    When apigee calls get pickup stores for the fulfilment store id <StoreId>
    Then apigee successfully authenticate to trader public api endpoint as guest shopper with selected pickup store and <deliveryMethod>

    Examples:
      | StoreId | deliveryMethod |
      | 1111    | Pickup         |

  @ProdRegression
  Scenario: Login as Guest and check the pickup structure by postcode
    Given apigee connect to trader public api endpoint as guest
    And connection from apigee to trader public api endpoint happens
    When apigee successfully authenticate to trader public api endpoint as guest
    And verify the pickup stores in the postcode 2000


  Scenario Outline: Validate the Fulfillment method for Pickup (DriveUp and InStore)

    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    And I clear the trolley for the shopper
    And I search for the product "Milk" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    When apigee connect to trader public api endpoint with login containing <EmailAddress> and password
    When connection from apigee to trader public api shopper endpoint happens
    Then I search for the pickup stores in the postcode <PostCode>
    And I select the "1" pickup store as checkout pickup store from matching stores for the logged in user
    And I add wait for 5000 msecs to make sure the sync is successful
    Then I validate that the fulfilmentMethod match to FulfilmentMethod for pickup mode stores in V3_CHECKOUT_ADDRESS
    Examples:
      | PostCode | EmailAddress |
      | 2174     | shopapp+61   |
      | 2000     | shopapp+61   |
      | 2099     | shopapp+61   |


