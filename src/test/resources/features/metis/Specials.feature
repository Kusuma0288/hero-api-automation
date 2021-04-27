@REGRESSION_REWARDS @CLOVER
Feature: Specials should return frequently bought products for the user

  Scenario: User selects specials
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects his favourites products
    Then the user should see all his favourites product
    And if no products are present the user should see a message
    And if the partner is Woolworths it should display the action button
    And the disclaimer should be returned

  Scenario Outline: User sorts specials
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user sorts his favourites products by <Sort Option>
    Then the product should be sorted by Sort Option
    Examples:
      | Sort Option       |
      | Price High to Low |
      | Price Low to High |
      | Name A to Z       |
      | Name Z to A       |