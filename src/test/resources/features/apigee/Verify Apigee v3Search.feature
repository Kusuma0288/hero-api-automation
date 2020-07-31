@REGRESSION @PROD @Wolves
Feature: Verify Apigee v3 Search functionality

  Scenario Outline: Verify that user is able to search an item and matching products are returned
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user successfully authenticate to apigee public api as guest
    And user continue to connect to apigee with default login and password
    When I search for the product <searchItem> in <mode> mode and should see more than 1 matching results
    Examples:
      | searchItem | mode   |
      | Apple      | online |
      | Milk       | pickup |

  Scenario Outline: Verify that is user searches for an inValidSearch item no matching products are returned
    Given mobile user connect to apigee endpoint as guest
    When connection from user to apigee endpoint happens
    And user successfully authenticate to apigee public api as guest
    And  user continue to connect to apigee with login username as "SHOPPER_USERNAME8"
    When I search for the product <inValidSearchItem> in <mode> mode and should not see any matching results
    Examples:
      | inValidSearchItem | mode   |
      | Ba$$EEE0          | pickup |
      | BayuEE            | online  |

