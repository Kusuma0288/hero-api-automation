@REGRESSION_REWARDS @NUTELLA @WALLET
Feature: Checkout safely and securely using your preferred payment method

  Scenario: Add a new scheme card
    Given a user logs in the rewards app with card number "REWARDS_USER_NO_CARD"
    When the user goes to the card screen
    Then the user should see the wallet is empty
    And the user should be able to add a new card

  Scenario: Remove a scheme card
    Given a user logs in the rewards app with card number "REWARDS_USER_PLZ_DO_NOT_DELETE_CARD"
    When the user goes to the card screen
    Then the user should see the wallet has a card
    And the user should be able to remove a card