@IN_PROGRESS
Feature: Verify Homepage Promo Tile that calls productsByProductGroup graphql endpoint

  Scenario: Sample graphql call to demo graphql query parser
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    When connection from user to apigee endpoint happens
    Then I search for the ProductGroup with id "25" and validate the response