@Wolves
Feature: Search GraphQL
  # UAT has known issues so focus on PROD
  # Test failing due to bug TW-3462
#  @PROD_APIGEE @IRIS @PROD
#  Scenario: User never sees duplicate products in search results
#    Given I connect to apigee endpoint as a guest user
#    When user searches for online "Milk" products 20 at a time and scrolls to the end of the results
#    Then no duplicate results are returned
#    And the product total count matches the actual number of products returned

#  Taniwha Squad modified search to include a conditional flag to contain a Google Ad Banner
#   "variables": {
#     "searchTerm": "Coke",
#     "productsFeed": true
#   }
  @REGRESSION_APIGEE @IRIS @UAT @TEST # Only TEST/UAT as it requires a stable Adobe Target ad set up
  Scenario: Search with productsFeed contains same results as without productsFeed
    Given I connect to apigee endpoint as a guest user
    When user searches for "Coke" with productsFeed "false"
    And user searches for "Coke" with productsFeed "true"
    Then the products by search responses are identical

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a guest user with filter type Brand
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a logged in user with filter type Allergens
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Allergens" and by filter option as "Egg Free"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a guest user with filter type Dietary and Lifestyle
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Cheese" as the search product with sort option "TraderRelevance" and filter by "Dietary and Lifestyle" and by filter option as "Gluten Free"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a logged in user with filter type Health Star Rating
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Health Star Rating" and by filter option as "4"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a guest user with filter type Categories
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Category" and by filter option as "1_E872E7C"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a logged in and sort by Name Ascending[A-Z]
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user searches for "Milk" as the search product with sort option "Name" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a guest user and sort by Name Descending[Z-A]
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "NameDesc" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a logged in and sort by NEW
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user searches for "Milk" as the search product with sort option "AvailableDateDesc" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a guest user and sort by Price[Low-High]
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "PriceAsc" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a logged in and sort by Price[High-Low]
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user searches for "Milk" as the search product with sort option "PriceDesc" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a guest user and sort by Unit Price[Low-High]
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "CUPAsc" and filter by "Brand" and by filter option as "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product as a logged in and sort by Unit Price[High-Low]
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME1"
    And connection from user to apigee endpoint happens
    Then user searches for "Milk" as the search product with sort option "CUPDesc" and filter by "Brand" and by filter option as "A2"
