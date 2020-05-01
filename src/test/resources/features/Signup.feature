@SIGNUPPAGE @LOL @REGRESSION
Feature: Verify that Customer is able to sign-up


  Scenario Outline: Verify that user is able to sign-up for a customer with Date Of Birth > 18 and < 18

    Given mobile user connect to apigee endpoint as guest
    When user successfully authenticate to apigee public api as guest
    When Customer signup for woolworths online account with date of birth "<DateOfBirth>"
    Then Customer is able to successfully create an account

    Examples:

      | DateOfBirth |
      | 01/01/1975  |
      | 01/01/1993  |