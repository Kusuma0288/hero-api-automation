@TROLLEY @REGRESSION_TRADER @PROD_TRADER @LION
Feature: Verify Shopping Trolley
  Test for the Shopping Trolley working in all possible modes

  @SMOKE_TRADER
  Scenario: Login as Shopper and add items to the Trolley
    Given apigee connect to trader public api endpoint with default login and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When I clear the trolley for the shopper
    And I search for the product "Cooking Oil" and should see more than 1 matching results
    And I add 2 items to my trolley from search list with 1 quantity each
    And I search for the product "Tea" and should see more than 1 matching results
    And I keep on adding 3 items to my existing trolley from search list with 1 quantity each
    Then shopper trolley should have 5 products
    And I clear the trolley for the shopper

  Scenario: Login as Shopper and checking for failure when invalid products are added
    Given apigee connect to trader public api endpoint with default login and password
    And apigee successfully authenticate to trader public api endpoint as logged in user
    When I clear the trolley for the shopper
    And I add the following products to the trolley
      | stockCode | name | quantity | status |
      | 88        | Milk | 1        | FAILED |
    Then shopper trolley should have 0 products

  Scenario Outline: Login as Shopper and check leave unattended options with regular items
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME2 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I clear the trolley for the shopper
    And I search for the product "Biscuit" and should see more than 1 matching results
    And I add 4 items to my trolley from search list with 1 quantity each
    Then shopper trolley should have 4 products
    And I can leave the products unattended
    When I do a V2 checkout then I should see the leave unattended option "ENABLED" and turned "ON"
    And I clear the trolley for the shopper
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Login as Shopper and check leave unattended options with restricted items
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME1 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I clear the trolley for the shopper
    And I search for the product "Whisky" and should see more than 1 matching results
    And I add few restricted items to my trolley from search list
    And I do a V2 checkout then I should see the leave unattended option "DISABLED" and turned "OFF"
    And I clear the trolley for the shopper
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Login as Shopper and check leave unattended options after removing restricted items
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME2 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I clear the trolley for the shopper
    And I search for the product "Cookies" and should see more than 1 matching results
    And I add 1 items to my trolley from search list with 1 quantity each
    Then shopper trolley should have 1 products
    And I can leave the products unattended
    When I do a V2 checkout then I should see the leave unattended option "ENABLED" and turned "ON"
    And I search for the product "Whisky" and should see more than 1 matching results
    And I add few restricted items to my trolley from search list
    And I do a V2 checkout then I should see the leave unattended option "DISABLED" and turned "OFF"
    And I clear the trolley for the shopper
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario: Login as Guest and signup as shopper to verify trolley items are migrated
    Given apigee connect to trader public api endpoint as guest
    And apigee successfully authenticate to trader public api endpoint as guest
    And I search for the product "Cookies" and should see more than 1 matching results
    And I add 5 items to my trolley from search list with 1 quantity each
    Then shopper trolley should have 5 products
    And I use the following details for signing up as a new user in same device
      | firstName | lastName | emailAddress        | password | mobilePhone | dateOfBirth | isBusinessShopper | emailProductsAndServices | smsProductsServicesAndPromotions | campaignName |agreeToTsAndCs|
      | anish     | pillai   | traderAPI@gmail.com | 123456   | 0435876458  | 01/01/1940  | false             | false                    | false                            | WBT          |true          |
    And apigee successfully authenticate to trader public api endpoint with signedup user session details
    When apigee connect to trader public api endpoint with newly created login and password "123456"
    And connection from apigee to trader public api shopper endpoint happens
    Then shopper trolley should have 5 products
    And I clear the trolley for the shopper

  Scenario: Login as Guest and then login as shopper to verify trolley items are migrated
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME3 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I clear the trolley for the shopper
    And apigee connect to trader public api endpoint as guest
    And apigee successfully authenticate to trader public api endpoint as guest
    And I search for the product "Cookies" and should see more than 1 matching results
    And I add 2 items to my trolley from search list with 1 quantity each
    Then shopper trolley should have 2 products
    When apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME4 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    Then shopper trolley should have 2 products
    And I clear the trolley for the shopper

  Scenario Outline: Login as Shopper and Search for Products
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME5 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I search for the product "St Agnes vs Brandy" and should see more than 0 matching results
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Login as Shopper and Search for Restricted Products
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME6 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I clear the trolley for the shopper
    And I search for the product "Whisky" and should see more than 1 matching results
    And I add few restricted items to my trolley from search list
    And I clear the trolley for the shopper
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Login as Shopper and add notes to an item in Trolley and verify the note is saved
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME7 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    When I clear the trolley for the shopper
    And I search for the product "Nivea" and should see more than 1 matching results
    And I add 2 of the searched item to my trolley with note <note>
    Then verify the note <note> is added to item in trolley
    And I clear the trolley for the shopper
    And I want to add 2 of the same item again to trolley
    Then verify no note is added when we add the same product again with no note
    #TODO: Adding item with note has some cache issue, need to have a look into this

    Examples:
      | note       | Address                                |
      | Note Added | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario: Clear items from trolley as shopper and retrieve items from the trolley
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME4 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I clear the trolley for the shopper
    And I retrieve the V2 endpoint of the trolley

  Scenario Outline: Clear a specified item from Trolley and verify the item is deleted
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME4 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    When I clear the trolley for the shopper
    And I search for the product "Chicken" and should see more than 1 matching results
    And I add 3 items to my trolley from search list with 1 quantity each
    And shopper trolley should have 3 products
    And I delete 1 product from trolley and verify it is deleted
    Then shopper trolley should have 2 products
    And I clear the trolley for the shopper
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Add items with quantity 0 and verify that the trolley is empty
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME6 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    When I clear the trolley for the shopper
    And I search for the product "Nivea" and should see more than 1 matching results
    And I add 3 items to my trolley from search list with 0 quantity each
    Then shopper trolley should have 0 products
    And I clear the trolley for the shopper
    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |

  Scenario Outline: Login as Guest and add address and then login as shopper to verify trolley items are migrated
    Given apigee continue to connect to trader public api endpoint with login containing SHOPPER_USERNAME1 and password
    And I clear the trolley for the shopper
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME2 and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    And I clear the trolley for the shopper
    When apigee connect to trader public api endpoint as guest
    And apigee successfully authenticate to trader public api endpoint as guest
    And I search for the address <Address>
    And I select the "1" address as checkout address from matching addresses
    And I search for the product "Chicken" and should see more than 1 matching results
    And I add 3 items to my trolley from search list with 1 quantity each
    And I search for the product "Nivea" and should see more than 1 matching results
    And I keep on adding 2 items to my existing trolley from search list with 5 quantity each
    And I search for the product "Soap" and should see more than 1 matching results
    And I keep on adding 1 items to my existing trolley from search list with 2 quantity each
    And I search for the product "Chips" and should see more than 1 matching results
    And I keep on adding 2 items to my existing trolley from search list with 1 quantity each
    And I search for the product "Oil" and should see more than 1 matching results
    And I keep on adding 3 items to my existing trolley from search list with 1 quantity each
    Then shopper trolley should have 11 products

    When apigee continue to connect to trader public api endpoint with login containing SHOPPER_USERNAME1 and password
    And I check the delivery method to be "Courier"
    And I retrieve the trolley for the shopper to check for 11 products
    Then I verify all 11 items are available in the Trolley
    And I clear the trolley for the shopper

    Examples:
      | Address                                |
      | 34 Victoria Rd, Marrickville NSW  2204 |



