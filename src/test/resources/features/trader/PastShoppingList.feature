@VEGEMITE

Feature: Verify past shop get list

  Background:
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME62 and password

  @REGRESSION_TRADER
  Scenario: Login as Shopper and verify if past shops are showing

    When customer calls past shopping list api with page number as 1 and pagesize as 25
    Then verify past shopping api responds with right response