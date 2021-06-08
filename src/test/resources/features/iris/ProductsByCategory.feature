@REGRESSION_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Products by Category GraphQL

  Scenario: Validate if Products by Category are showing for the shopper
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    And connection from user to apigee endpoint happens
    When I check Products by Category event is loaded for categoryId "1_E4DD385" and for store "1561" for user
    Then I verify Products by Category shopping api responds with "Organic" products response
    And I check Products by Category event is loaded for categoryId "1_E4DD385" and for store "" for user
    Then I verify Products by Category shopping api responds with "Organic" products response

  Scenario: Validate if Products by Category are showing for the Guest
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    When I check Products by Category event is loaded for categoryId "1_E4DD385" and for store "1561" for user
    Then I verify Products by Category shopping api responds with "Organic" products response
    And I check Products by Category event is loaded for categoryId "1_E4DD385" and for store "" for user
    Then I verify Products by Category shopping api responds with "Organic" products response