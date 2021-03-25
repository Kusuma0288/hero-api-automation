@REGRESSION_TRADER @GUEST_STORE @PROD_TRADER @Wolves
Feature: Guest set store verification  check for set store


  Scenario: Test the Guest Login for V3 with only Device Id
    Given apigee connect to trader public api endpoint as guest with only device id
    When connection from apigee to trader public api endpoint happens
    Then apigee successfully authenticate to trader public api endpoint as guest with all session fields

  Scenario: Test the Guest Login for V3 and verifying Primary Address for multiple login
    Given apigee connect to trader public api endpoint as guest with only device id
    And apigee successfully authenticate to trader public api endpoint as guest
    And I search for address "190 George St, PARRAMATTA  NSW  2150" without saving for future lookup
    And I select the "1" address as checkout address from matching addresses
    Then I should see the address as primary for guest user

  Scenario: Verify the primary address for signed up user and switch the delivery addresses
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    And I search for address "1 Darcy Road" and select the 1 address as checkout address from matching addresses
    And I use the following details for signing up as a new user in same device
      | firstName  | lastName   | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      | tradertest | tradertest | traderAPI@gmail.com | test123  | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | true           |
    Then verify the address added recently is set as primary in MyAccount for signed up user

    And I search for address "190 George St" and select the 1 address as checkout address from matching addresses
    And I search for address "200 George St" and select the 1 address as checkout address from matching addresses
    Then verify the address added/switched recently is set as primary in MyAccount
    Then verify all the adresses added recently are reflected in MyAccount

    When I switch the address "190 George St" to primary address
    Then verify the address added/switched recently is set as primary in MyAccount
    And I remove all the stored delivery addresses








