@REGRESSION @DELIVERY @PROD
Feature: APIGEE : Pickup - Verify Apigee Pickup scenarios

Scenario Outline: Verify whether user is able to search for a  pickup store by postcode
  Given user continue to connect to apigee with login username as "SHOPPER_USERNAME4"
  When connection from user to apigee endpoint happens
  Then I search for the pickup stores in the postcode <PostCode>
  Then I validate that the fulfilmentMethod match to <FulfilmentMethod>

  Examples:
  |PostCode| FulfilmentMethod |
  |2099    | Pickup           |

  Scenario Outline: Verify whether user gets error message for invalid post code
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME5"
    When connection from user to apigee endpoint happens
    Then I search for the pickup stores with invalid postcode <PostCode>
    Then I validate that the no matching results are returned for the invalid postcode

    Examples:
      |PostCode|
      |0100    |


  Scenario Outline: Verify whether user is able to search stores by valid lat & long, storeAddressId and storeID
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    When connection from user to apigee endpoint happens
    Then I search for the pick up stores using latitude <latitude> & longitude <longitude> and verify if stores returned are sorted by distance by default
    Then I search for the pick up stores with Store AddressID <StoreAddressID> and validate that AddressText, Description and SuburbId are not blank
    Then I search for the pickup stores with StoreID <StoreID> and validate the AddressText, Description and SuburbId are not blank
    Examples:
      | latitude    | longitude   | StoreAddressID   | StoreID     |
      | -33.8854348 | 151.2079537 |  932627          |   1248      |


  Scenario Outline: Verify whether user is able to search stores by Invalid lat & long, storeAddressId and storeID
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    When connection from user to apigee endpoint happens
    Then I search for the pick up stores with invalid Store AddressID <StoreAddressID> and validate the response
    Then I search for the pickup stores with invalid StoreID <StoreID> and validate the response
    Examples:
      | StoreAddressID   | StoreID       |
      |  567             |   4500        |

