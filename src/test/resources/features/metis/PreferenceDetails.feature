@REGRESSION_REWARDS @CLOVER
Feature: Preference Details enables a customer to toggle their preferences

  Scenario: User selects his preference details for opt out of paper
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects his preference details for "receipts"
    Then the user should see the preference details for receipts
