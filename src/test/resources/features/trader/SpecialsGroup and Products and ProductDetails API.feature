@PRODUCT_DETAILS @Lobsters
Feature: Verify Product details Api

  @SMOKE_TRADER @REGRESSION_TRADER
  Scenario: Login as Shopper and verify the Products details api responds with product details for any stockcode/Product Id
    Given apigee connect to trader public api endpoint with default login and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When customer calls product details api for 571487 stockcode
    Then verify product details api responds with all the details of product for this stockcode

  @ProdRegression
  Scenario Outline: Verify the product count and total record count of each special group against products API for logged in user
    Given apigee connect to trader public api endpoint with login containing <Email Address> and password
    And I get the list of specials groups and verify the product count and total record count of each group

    Examples:
      | Email Address |
      | shopapp+29    |
      | shopapp+26    |

  @ProdRegression
  Scenario: Verify the product count and total record count of each special group against products API for guest user

    Given apigee connect to trader public api endpoint as guest
    When connection from apigee to trader public api endpoint happens
    And I get the list of specials groups and verify the product count and total record count of each group

