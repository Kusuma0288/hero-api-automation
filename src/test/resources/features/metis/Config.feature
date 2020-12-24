@REGRESSION_REWARDS @CLOVER
Feature: Config checks for supported OS and App version

  Scenario Outline: User launch of app checks for supported iOS and Android Version
    Given the user launches the App with "<appVersion>" and "<osVersion>" and "<clientOS>"
    Then the user should be able to see the on-boarding screen
    Examples:
      | appVersion | osVersion | clientOS |
      | 2.10.0     | 14.0      | iOS      |
      | 2.10.0     | 7.0       | Android  |

  Scenario Outline: User launch of app checks for unsupported iOS and Android Version
    Given the user launches the App with "<appVersion>" and "<osVersion>" and "<clientOS>"
    Then the user should not be able to open the app
    Examples:
      | appVersion | osVersion | clientOS |
      | 2.10.0     | 5.0       | Android  |
      | 2.10.0     | 11.0      | iOS      |

