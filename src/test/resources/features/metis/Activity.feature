@REGRESSION_REWARDS @CLOVER
Feature: Activity provides information about transaction activities

  Scenario: User selects the Rewards Activity
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects an activity
    Then the user should be able to see the activity details

  Scenario: User selects an e-receipt with BWS
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_BWS_COUPON"
    When the user selects an activity
    And the user selects the receipt with a "BWS_RECEIPT_ID"
    Then the user should be able see a BWS coupon in his receipt