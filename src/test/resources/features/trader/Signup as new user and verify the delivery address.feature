@GUEST_ADDRESS @REGRESSION_TRADER @SIGN_UP @LION

Feature: Signup as a new user and verify the deliver address from GUEST

  Scenario Outline: Test the Guest Login for V3 and transitioning to a new shopper
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I use the following details for signing up as a new user in same device
      | firstName | lastName | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      | anish     | pillai   | traderAPI@gmail.com | test123  | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | true			|
    Then I login to signed up account to see the addresses
    Examples:
      | Address                              |
      | 201 George St, PARRAMATTA  NSW  2150 |

  Scenario Outline: Test the Guest Login for V3 and searching for suburb with space is searchable
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I use the following details for signing up as a new user in same device
      | firstName | lastName | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      | anish     | pillai   | traderAPI@gmail.com | test123  | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | true			|
    Then I login to signed up account to see the address information match
    Examples:
      | Address                                      |
      | 9 Francis Avenue, BRIGHTON LE SANDS NSW 2216 |

  Scenario: Signup as a new shopper and login with the user
    Given I use the following details for signing up as a new user
      | firstName | lastName | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      | anish     | pillai   | traderAPI@gmail.com | 123456   | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | true			|
    And apigee successfully authenticate to trader public api endpoint with signedup user session details
    And apigee connect to trader public api endpoint with newly created login and password "123456"
    When connection from apigee to trader public api shopper endpoint happens
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details


  Scenario Outline: Test the Guest Login for V3 and transitioning to a new shopper to verify the address Id
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I use the following details for signing up as a new user in same device
      | firstName | lastName | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      | anish     | pillai   | traderAPI@gmail.com | 123456   | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | true			|
    Then apigee successfully authenticate to trader public api endpoint with signedup user session details
    When apigee connect to trader public api endpoint with newly created login and password "123456"
    And connection from apigee to trader public api shopper endpoint happens
    Then I login to signed up account to see the addresses
    Examples:
      | Address                          |
      | 102 George St, North Strathfield |

  Scenario: Signup as an existing shopper and verify the error
    Given I use the exact details for signing up as a new shopper
      | firstName | lastName | emailAddress                 | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      |           |          | shopapp+25@woolworths.com.au |          | 000         | 01/01/1940  | false             | false                    | false                            | WBT          | true			 |
    When connection from apigee to trader public api signup endpoint happens
    Then trader signup api responds with the response status "400" and following error fields
      | fieldName    | errorDescription                             |
      | FirstName    | First name is required                       |
      | LastName     | Last name is required                        |
      | Password     | Password is required                         |
      | EmailAddress | The entered email already exists             |
      | MobilePhone  | A valid Australian phone number is required. |

  Scenario Outline: Signup with different combinations and check results
    Given I use the following details for signing up as a new user
      | firstName   | lastName   | emailAddress   | password   | mobilePhone   | dateOfBirth   | isBusinessShopper   | emailProductsAndServices   | smsProductsServicesAndPromotions   | campaignName   | agreeToTsAndCs |
      | <firstName> | <lastName> | <emailAddress> | <password> | <mobilePhone> | <dateOfBirth> | <isBusinessShopper> | <emailProductsAndServices> | <smsProductsServicesAndPromotions> | <campaignName> | true			 |
    When connection from apigee to trader public signup api endpoint happens
    Then I should see the following "<status>" captured with status code as <statusCode> having <errorFields>

    Examples:
      | firstName    | lastName     | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | status | errorFields                                          | statusCode |
      |              | Lautomation  | traderAPI@gmail.com | 123456   | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | ERROR  | FirstName                                            | 400        |
      | FAutomation  |              | traderAPI@gmail.com | 123456   | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | ERROR  | LastName                                             | 400        |
      | FAutomation  | LAutomation  | traderAPI@gmail.com |          | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | ERROR  | Password                                             | 400        |
      | FAutomation  | LAutomation  | tradergmail.com     |          | 043587      | 01/01/1940  | false             | false                    | false                            | WBT          | ERROR  | Password;MobilePhone;EmailAddress_Format             | 400        |
      | F Automation | L Automation | traderAPI@gmail.com | 123456   |             | 01/01/1940  | false             | false                    | false                            | WBT          | ERROR  | MobilePhone                                          | 400        |
      |              |              |                     |          |             | 01/01/1940  | false             | false                    | false                            | WBT          | ERROR  | FirstName;LastName;Password;MobilePhone;EmailAddress | 400        |
      | F Automation | L Automation | traderAPIgmail@.com | 123456   |             |             | false             | false                    | false                            | WBT          | ERROR  | EmailAddress_Format;MobilePhone                      | 400        |

  Scenario: Signup as a new shopper and login with the user with wrong password
    Given I use the following details for signing up as a new user
      | firstName | lastName   | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName | agreeToTsAndCs |
      | trader    | automation | traderAPI@gmail.com | 123456   | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          | true		      |
    And apigee successfully authenticate to trader public api endpoint with signedup user session details
    And apigee connect to trader public api endpoint with newly created login and password "123456"
    And connection from apigee to trader public api shopper endpoint happens
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When apigee try to connect to trader public api endpoint with newly created login and invalid password for 6 attempts
    Then apigee failed to authenticate to trader public api endpoint as shopper and is locked out