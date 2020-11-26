@REGRESSION_REWARDS @CLOVER
Feature: Config checks for supported OS and App version

  Scenario Outline: User launch of app checks for supported OS and Version
    Given the user launches the App with "<appVersion>" and osversion "<osVersion>"
    Then the user should be able to see the on-boarding screen
    Examples:
      | appVersion | osVersion |
      | 2.9.0      | 14.0      |
      | 2.9.0      | 13.0      |
      | 2.8.0      | 14.0      |
      | 2.8.0      | 13.0      |


  Scenario Outline: User launch of app checks for unsupported OS and Version
    Given the user launches the App with "<appVersion>" and osversion "<osVersion>"
    Then the user should not be able to open the app
    Examples:
      | appVersion | osVersion |
      | 2.9.0      | 11.0      |
      | 2.8.0      | 11.0      |
