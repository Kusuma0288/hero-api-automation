@REGRESSION_REWARDS @CLOVER
Feature: Activity provides information about transaction activities

  Scenario: User selects the Rewards Activity
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When the user selects an activity
    Then the user should be able to see the activity details
