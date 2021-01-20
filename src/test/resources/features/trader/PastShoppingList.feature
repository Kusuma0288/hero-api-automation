@Vegimite

Feature: Verify past shop get list

  @IN_PROGRESS
  Scenario: Login as Shopper and verify if past shops are showing

    Given apigee connect to trader public api endpoint with login containing SHOPPER_USERNAME62 and password
    When customer calls past shopping list api
    Then verify past shopping api responds with right response