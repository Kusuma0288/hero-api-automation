@Wolves @PROD_TRADER
Feature: Verify V3 Search scenarios

  @REGRESSION
  Scenario Outline: To verify that the guest user is able to search for an item under delivery mode

    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    Then I search for the address <DeliveryAddress>
    And I select the "1" address as checkout address from matching addresses
    Then I search for the product "Milk" with the new search endpoint

    Examples:
      | DeliveryAddress                    |
      | 19 Rudd Street, Narellan, NSW 2567 |

  Scenario Outline: To verify that the logged in user is able to search for an item under delivery mode

    Given apigee connect to trader public api endpoint with login containing <EmailAddress> and password
    When connection from apigee to trader public api shopper endpoint happens
    Then I search for the address <DeliveryAddress>
    And I select the "1" address as checkout address from matching addresses
    Then I search for the product "Milk" with the new search endpoint

    Examples:
      | DeliveryAddress                    | EmailAddress |
      | 19 Rudd Street, Narellan, NSW 2567 | shopapp+61   |

  Scenario: To verify that the guest user is able to search for an item under pick up mode

    Given apigee connect to the trader public V2 endpoint as guest with postcode 2204
    When connection from apigee to trader public api endpoint happens
    Then I search for the product "Eggs" with the new search endpoint

  Scenario Outline: To verify that the logged in user is able to search for an item under pick up mode

    Given apigee connect to trader public api endpoint with login containing <EmailAddress> and password
    When connection from apigee to trader public api shopper endpoint happens
    Then I search for the pickup stores in the postcode <PostCode>
    And I select the "1" pickup store as checkout pickup store from matching stores for the logged in user
    Then I search for the product "Eggs" with the new search endpoint

    Examples:
      | PostCode | EmailAddress |
      | 2204     | shopapp+61   |