Feature: Online product details graphql

  Scenario: Guest user queries online products details for an available product
    Given mobile user connect to apigee endpoint as guest
    And connection from user to apigee endpoint happens
    When user requests for online "Milk" products by search
    And user selects a "RANDOM" product to get online product details
    Then online product details for the available product are satisfactory for user display