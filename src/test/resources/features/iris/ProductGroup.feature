@REGRESSION_APIGEE @PRODUCT_GROUPS @Taniwha @Lobsters
Feature: Product groups graphql

  Scenario: User queries a product group
    Given I connect to apigee endpoint as a mobile, guest user
    When I request product group "25"
    Then I can see the product group with products listed