@DELIVERY @REGRESSION @Wolves
Feature: Verify Delivery Options
  Test for the delivery options

  Scenario Outline: Login as Shopper and check the Crate to Bench packaging option in V2 Checkout
    Given apigee connect to trader public api endpoint with login containing shopapp+26 and password
    When apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I could not leave the products unattended
    And I clear the trolley for the shopper
    And I search for the product "Cooking Oil" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    And I get the available delivery windows for the logged in user with storeId or addressId
    And I set the selected available delivery window for the logged in user
    And I select the delivery packaging options available for the shopper to "Reusable Bags"
    Then I do a V2 checkout and should see "Reusable bags" packaging selected
    #Then I do a V2 checkout and should see crate to bench packaging unavailable
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Login as Shopper and check the Crate to Bench packaging option in V3 Checkout
    Given apigee connect to trader public api endpoint with login containing shopapp+27 and password
    When apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I could not leave the products unattended
    And I clear the trolley for the shopper
    And I search for the product "Eggs" and should see more than 1 matching results
    And I add 8 items to my trolley from search list with 8 quantity each
    And I get the available delivery windows for the logged in user with storeId or addressId
    And I set the selected available delivery window for the logged in user
    And I select the delivery packaging options available for the shopper to "Reusable Bags"
    Then I do a V3 checkout and should see "Reusable bags" packaging selected
    #Then I do a V3 checkout and should see crate to bench packaging unavailable
    Examples:
      | Address                            |
      | 19 Rudd Street, Narellan, NSW 2567 |