@VEGIMITE

Feature: Verify past shop get list

  Background:
    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME62 and password

  @REGRESSION
  Scenario: Login as Shopper and verify if past shops are showing

    When customer calls past shopping list api with number of pages as 1 and pagesize as 25
    Then verify past shopping api responds with right response