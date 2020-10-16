@REGRESSION_REWARDS @CLOVER
Feature: View your campaign shore collection items

  Scenario: User selects the shore collection banner
    Given a user logs in the rewards app with card number "REWARDS_USER_DEFAULT"
    When the user selects the shore collection banner
    Then the user should see the all the collection containers details