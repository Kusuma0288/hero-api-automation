package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.SignupHelper;
import au.com.woolworths.model.apigee.LoginReponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class SignUpPage extends SignupHelper {

  private final static Logger logger = Logger.getLogger("SignUpPage.class");
  private LoginReponse loginReponse;


  @When("^Customer signup for woolworths online account with date of birth \"([^\"]*)\"$")
  public void customerSignupForWoolworthsOnlineAccountWithDateOfBirth(String Date_of_Birth) throws Throwable {
    loginReponse = iCompleteSignUPWithDOB(Date_of_Birth);
  }

  @Then("^Customer is able to successfully create an account$")
  public void customerIsAbleToSuccessfullyCreateAnAccount() throws Throwable {
    //Assert Response is not Null
    Assert.assertNotNull("APIGEE_V2_SIGNUP Failed", loginReponse.getShopperid());
  }
}
