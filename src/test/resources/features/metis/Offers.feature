@REGRESSION_REWARDS @CLOVER
Feature: Boost and view relevant offers

  Scenario: User views offers in correct filter
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When the user lands on the booster screen
    Then the user should see offers in the correct filter
    And the offers should contain relevant information

  Scenario: User should see empty state message when there are no offers
    Given a user logs in the rewards app with card number "REWARDS_USER_NO_FUEL_VOUCHER_OR_OFFERS"
    When the user lands on the booster screen
    And the user does not have any offers
    Then the user should see the empty state messages