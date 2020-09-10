@PRODUCT_GROUPS @Lobsters
Feature: Verify Products by Product Group Api

  @SMOKE_TRADER
  Scenario Outline: Verify products by product group api as a logged in shopper
    Given apigee connect to trader public api endpoint with login containing <emailAddress> and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When shopper calls products by product group api with "<productGroupId>"
    Then verify products by product group api responds with all products for this productGroupId
    Examples:
      | emailAddress | productGroupId |
      | shopapp+26   | 25             |

  @SMOKE_TRADER
  Scenario Outline: Verify products by product group api as a guest shopper
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    When shopper calls products by product group api with "<productGroupId>"
    Then verify products by product group api responds with all products for this productGroupId
    Examples:
      | productGroupId |
      | 25             |

  @REGRESSION_TRADER @PROD_TRADER
  Scenario Outline: Verify products by product group api for trolley updates as a logged in shopper
    Given apigee connect to trader public api endpoint with login containing <emailAddress> and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When shopper calls products by product group api with "<productGroupId>"
    Then verify products by product group api responds with all products for this productGroupId
    When shopper adds any <productCount> products with <quantity> to trolley that are available in product group
    And shopper calls products by product group api with "<productGroupId>"
    Then trolley <quantity> and other information is updated for the products added to the trolley
    And I clear the trolley for the shopper
    Examples:
      | emailAddress | productGroupId | quantity | productCount |
      | shopapp+26   | 25             | 2        | 2            |

  @REGRESSION_TRADER @PROD_TRADER
  Scenario Outline: Verify products by product group api for trolley updates as a guest shopper
    Given apigee connect to trader public api endpoint as guest
    When apigee successfully authenticate to trader public api endpoint as guest
    When shopper calls products by product group api with "<productGroupId>"
    Then verify products by product group api responds with all products for this productGroupId
    When shopper adds any <productCount> products with <quantity> to trolley that are available in product group
    And shopper calls products by product group api with "<productGroupId>"
    Then trolley <quantity> and other information is updated for the products added to the trolley
    And I clear the trolley for the shopper
    Examples:
      | productGroupId | quantity | productCount |
      | 25             | 2        | 2            |
