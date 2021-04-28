@REGRESSION_APIGEE @Wolves
Feature: Search GraphQL

  Scenario: User never sees duplicate products in search results
    Given I connect to apigee endpoint as a guest user
    When user searches for online "Milk" products 20 at a time and scrolls to the end of the results
    Then no duplicate results are returned
    And the product total count matches the actual number of products returned
