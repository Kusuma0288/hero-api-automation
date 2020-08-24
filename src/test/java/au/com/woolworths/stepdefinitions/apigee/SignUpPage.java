package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.SignupHelper;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class SignUpPage extends SignupHelper {

  private final static Logger logger = Logger.getLogger("SignUpPage.class");
  private LoginReponse loginReponse;

  @When("^Customer signup for woolworths online account with DOB \"([^\"]*)\" and T&C (.*)$")
  public void customerSignupForWoolworthsOnlineAccountWithDateOfBirth(String Date_of_Birth, boolean AgreeToTsAndCs) throws Throwable {
      /* 
       * Parameters
       * @AgreeToTsAndCs{TRUE, FALSE}
       *   -TRUE successful sign up and skips IF condition
       *   -FALSE failed sign up response. Flows through IF condition
      */
    loginReponse = iCompleteSignUPWithDOB(Date_of_Birth, AgreeToTsAndCs); 
    if ((AgreeToTsAndCs == false) && ((loginReponse.getError()).equals("To sign up you must accept the above terms")) && (loginReponse.getStatusCode()).equals("400")) {
      loginReponse.setShopperid("termsAndCondition");
    }
  }

  @Then("^Customer is notified of (.*)$")
  public void customerIsAbleToSuccessfullyCreateAnAccount(String message) throws Throwable {
    //Assert Response is not Null   
    Assert.assertNotNull("APIGEE_V2_SIGNUP Failed", loginReponse.getShopperid());
  }
}
