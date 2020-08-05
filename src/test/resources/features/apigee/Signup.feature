@SIGNUPPAGE @LION @REGRESSION
Feature: Sign up scenarios


  Scenario Outline: Verify that user is able to sign-up with dob <DateOfBirth> and t&c as <AgreeToTsandCs>  .
  
    Given mobile user connect to apigee endpoint as guest
    When user successfully authenticate to apigee public api as guest
    When Customer signup for woolworths online account with date of birth "<DateOfBirth>" and t&c <AgreeToTsandCs>
    Then Customer is notified of <Notification> 

    Examples:

      | DateOfBirth | AgreeToTsandCs | Notification                    				   |
      | 01/01/1975  | true           | successfull creation of account                 |
      | 01/01/2009  | true			 | successfull creation of account                 |
      | 01/01/2009  | false			 | couldn't create account as t&c were not accepted |