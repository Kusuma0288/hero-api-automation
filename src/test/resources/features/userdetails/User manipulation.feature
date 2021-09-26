@REGRESSION
Feature: Verify User Manipulations

  Scenario: Verify the user token
    Given user wants to verify his token
    Then verify the token is not expired

  Scenario Outline: Verify user manipulations
    Given customer creates a new user with username "<username>" and score "<score>"
    Then customer retrieves the user details and verifies the newly created user details
    And customer modifies the created user with new score as "100"
    Then verify the created user is modified
    Examples:
      | username          | score |
      | Hero              | 65    |




