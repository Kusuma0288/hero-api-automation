@REGRESSION_REWARDS @CLOVER
Feature: Boost and view relevant offers

  Scenario: User views offers in correct filter
    Given a user logs in the rewards app with card number "REWARDS_USER_NO_FUEL_VOUCHER"
    When the user lands on the booster screen
    Then the user should see offers in the correct filter
    And the offers should contain relevant information