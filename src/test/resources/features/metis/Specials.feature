@REGRESSION @SPECIALS
Feature: Specials should return frequently bought products for the user

  Scenario: User selects specials
    Given a user has a Link session token
    And the user logs in with their authcode
    And the user should be logged into the Rewards App
    When the user selects his favourites products
    Then the user should see all his favourites product
    And if no products are present the user should see a message
    And if the partner is Woolworths it should display the action button