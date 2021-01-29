@REGRESSION_REWARDS @CLOVER
Feature: View the partner store locator details

  Scenario Outline: User views the partner store details
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects "<partner>" details with "<store>"
    Then the user should be able to see the partner details
    Examples:
      | partner          | store    |
      | SUPERMARKETS     | 1956     |



