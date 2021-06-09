@Wolves
Feature: Search GraphQL
#  UAT has known issues so focus on PROD
  @PROD_APIGEE @IRIS @PROD
  Scenario: User never sees duplicate products in search results
    Given I connect to apigee endpoint as a guest user
    When user searches for online "Milk" products 20 at a time and scrolls to the end of the results
    Then no duplicate results are returned
    And the product total count matches the actual number of products returned

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
  Scenario: Search a product with filter type Brand
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Brand" and by brand "A2"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product with filter type Allergens
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Allergens" and by brand "Egg Free"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product with filter type Dietary and Lifestyle
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Dietary and Lifestyle" and by brand "Gluten Free"

  @REGRESSION_APIGEE @IRIS @UAT @TEST
  Scenario: Search a product with filter type Health Star Rating
    Given I connect to apigee endpoint as a guest user
    Then user searches for "Milk" as the search product with sort option "TraderRelevance" and filter by "Health Star Rating" and by brand "4"
