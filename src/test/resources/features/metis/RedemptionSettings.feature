@REGRESSION
Feature: Redemption settings provides the option to choose how a user is rewarded

  Scenario: User selects redemption settings
    Given a user has a Link session token
    And the user logs in with their authcode
    And the user should be logged into the Rewards App
    When the user makes a request for the redemption settings
    Then the redemption setting should return "3" reward options
    And  the step up url should be returned
    And the user should be able to see his redemption options