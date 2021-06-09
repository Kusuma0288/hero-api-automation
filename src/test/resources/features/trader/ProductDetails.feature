@PRODUCT_DETAILS @REGRESSION_TRADER @PROD_TRADER @Wolves
Feature: Verify Product details API

  @SMOKE_TRADER
  Scenario: Login as Shopper and verify the Products details api responds with product details for any stockcode/Product Id
    Given apigee connect to trader public api endpoint with default login and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When customer calls product details api for 571487 stockcode
    Then verify product details api responds with all the details of product for this stockcode

  Scenario: Login as Shopper and verify the Products details api for any invalid stockcode/Product Id
    Given apigee connect to trader public api endpoint with default login and password
    And apigee successfully authenticate to trader public api endpoint as shopper with all session details
    When customer calls product details api for 8989878798 stockcode
    Then verify product details api responds with invalid details of product for this stockcode