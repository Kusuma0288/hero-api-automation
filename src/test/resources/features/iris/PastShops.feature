@REGRESSION_APIGEE @LIST @SMOKE @VEGEMITE
Feature: Verify GraphQl pastShops scenarios for User

  Background:
    Given user continue to connect to apigee with login username as "SHOPPER_USERNAME6"
    And connection from user to apigee endpoint happens

  Scenario: Validate if past shops are showing for the shopper
    When I check pastshops event list is loaded for page number as 1 and pagesize as 25 for user
    Then I verify past shopping api responds with right response
    And I check the purchase History with pastShops items for page number as 1 and pagesize as 25 and for store "1422"
    And I check the purchase History with pastShops items for page number as 1 and pagesize as 25 and online mode