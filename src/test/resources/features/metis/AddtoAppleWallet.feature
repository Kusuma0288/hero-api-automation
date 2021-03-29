@REGRESSION_REWARDS @CLOVER
Feature: Adding your rewards card to the apple pay wallet

  Scenario: User selects the Apple Pay
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user adds the rewards card to Apple pay wallet
    Then the rewards card is saved to Apple Pay