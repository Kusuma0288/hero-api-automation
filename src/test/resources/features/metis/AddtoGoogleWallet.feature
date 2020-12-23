@REGRESSION_REWARDS @CLOVER
Feature: Adding your rewards card to the google pay wallet

  Scenario: User selects the Google Pay
    Given a user logs in the rewards app as an "Android" user with card number "REWARDS_USER_DEFAULT"
    When the user adds the rewards card to Google pay wallet
    Then the rewards card is saved to Google Pay