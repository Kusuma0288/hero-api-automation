@REGRESSION_REWARDS @CLOVER
Feature: View all the partner locations using  lat/long

  Scenario Outline: User views the partner locator in ios
    Given a user logs in the rewards app as an "iOS" user with card number "REWARDS_USER_DEFAULT"
    When the user selects "<partner>" details with "<latitude>" and "<longitude>"
    Then the user should be able to see the partner location "<partner>"
    Examples:
      | partner        | latitude              | longitude  |
      | WOOLWORTHS     | -33.8086990832999     | 150.97555090962393  |
      | BWS            | -33.8086990832999     | 150.97555090962393  |
      | CALTEX         | -33.8086990832999     | 150.97555090962393  |
      | BIGW           | -33.8086990832999     | 150.97555090962393  |

  Scenario Outline: User views the partner locator in android
    Given a user logs in the rewards app as an "Android" user with card number "REWARDS_USER_DEFAULT"
    When the user selects "<partner>" details with "<latitude>" and "<longitude>"
    Then the user should be able to see the partner location "<partner>"
    Examples:
      | partner        | latitude              | longitude  |
      | WOOLWORTHS     | -33.8086990832999     | 150.97555090962393  |
      | BWS            | -33.8086990832999     | 150.97555090962393  |
      | CALTEX         | -33.8086990832999     | 150.97555090962393  |
      | BIGW           | -33.8086990832999     | 150.97555090962393  |


