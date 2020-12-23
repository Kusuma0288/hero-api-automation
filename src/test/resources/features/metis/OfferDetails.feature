@REGRESSION_REWARDS @CLOVER
Feature: Offer details provides information about individual offer

  Scenario: User selects the Offer Details
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects an individual offer on the Points screen with "REWARDS_OFFER_ID"
    Then the user should be able to see the offer details for "REWARDS_OFFER_ID"
