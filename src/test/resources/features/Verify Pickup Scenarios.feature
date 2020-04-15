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


  Scenario Outline: Verify whether user is able to search stores by lat & long
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    When connection from user to apigee endpoint happens
    Then I search for the pick up stores using latitude <latitude> & longitude <longitude> and verify if stores returned are sorted by distance by default
    Then I search for the pick up stores with Store AddressID <StoreAddressID> and validate that AddressText, Area and SuburbId are not blank
    Examples:
      | latitude    | longitude   | StoreAddressID   |
      | -33.8854348 | 151.2079537 |  932627          |



