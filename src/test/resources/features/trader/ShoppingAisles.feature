@SHOPPING_AISLES @REGRESSION @ProdRegression @Lobsters

Feature: Verify Shopping Aisles Api

  @SMOKE
  Scenario: Login as Shopper and verify the shopping aisles api responds with shopping aisles and its categories

    Given apigee connect to trader public api endpoint with default login and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When customer calls shopping aisles api
    Then verify shopping aisles api responds shopping aisles and its categories
