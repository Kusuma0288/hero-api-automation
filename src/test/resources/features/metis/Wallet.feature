@REGRESSION_REWARDS @Nutella @Wallet
Feature: Checkout safely and securely using your preferred payment method

  Scenario: User selects specials
    Given a user logs in the rewards app with card number "REWARDS_USER_NO_CARD"
    When the user goes to the card screen
    Then the user should see the wallet is empty
    And the user should be able to add a new card