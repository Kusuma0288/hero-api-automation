@REGRESSION_REWARDS @CLOVER
Feature: Discover enables a customer to find stores nearby for Woolworths and partners

  Scenario Outline: User selects discover page
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects the discover section with latitude "<latitude>" and longitude "<longitude>"
    Then the user should see information on stores nearby
    Examples:
      | latitude    | longitude   |
      | -33.8862486 | 151.2112155 |
      | -20.8862486 | 171.311155  |
      | -10.8862486 | 181.311155   |
