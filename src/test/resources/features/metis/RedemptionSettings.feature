@REGRESSION_REWARDS @CLOVER
Feature: Redemption settings provides the option to choose how a user is rewarded

  Scenario: User selects redemption settings
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When the user makes a request for the redemption settings
    Then the redemption setting should return "3" reward options
    And  the step up url should be returned
    And the user should be able to see his redemption options