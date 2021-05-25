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