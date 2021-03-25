@SIGNUPPAGE @LION @REGRESSION_APIGEE
Feature: Sign up scenarios


  Scenario Outline: Verify that user is able to sign-up with DOB <DateOfBirth> and T&C as <AgreeToTsandCs>  .

    Given mobile user connect to apigee endpoint as guest
    When user successfully authenticate to apigee public api as guest
    When Customer signup for woolworths online account with DOB "<DateOfBirth>" and T&C <AgreeToTsandCs>
    Then Customer is notified of <Notification>

    Examples:

      | DateOfBirth | AgreeToTsandCs | Notification                    |
      | 01/01/1975  | true           | successfull creation of account |
      | 01/01/2009  | true           | successfull creation of account |

     # TODO Venu to fix this as this negative test breaks the login negative tests
     # | 01/01/2009  | false			 | couldn't create account as T&C were not accepted |