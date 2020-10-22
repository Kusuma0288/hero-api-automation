@REGRESSION_REWARDS @NUTELLA @WALLET
Feature: Checkout safely and securely using your preferred payment method

  @IN_PROGRESS
  Scenario: Add a new scheme card
    Given a user logs in the rewards app with card number "REWARDS_USER_NO_CARD"
    When the user goes to the card screen
    Then the user should see the wallet is empty
    And the user should be able to add a new card

  Scenario: Remove a scheme card
    Given a user logs in the rewards app with card number "REWARDS_USER_PLZ_DO_NOT_DELETE_CARD_1"
    When the user goes to the card screen
    Then the user should see the wallet has a card
    And the user should be able to remove a card

  Scenario: Update a scheme card
    Given a user logs in the rewards app with card number "REWARDS_USER_PLZ_DO_NOT_DELETE_CARD_3"
    When the user goes to the card screen
    Then the user should see the wallet has a card
    And the user should be able to update a card

  Scenario: View an existing card in the wallet
    Given a user logs in the rewards app with card number "REWARDS_USER_PLZ_DO_NOT_DELETE_CARD_2"
    When the user goes to the card screen
    Then the user should see the wallet has a card
    And the user should be able to view the card details
  @IN_PROGRESS
  Scenario: View user preference
    Given a user logs in the rewards app with card number "REWARDS_USER_PLZ_DO_NOT_DELETE_CARD_1"
    When the user goes to the account screen
    Then the user should see a badge for new user

