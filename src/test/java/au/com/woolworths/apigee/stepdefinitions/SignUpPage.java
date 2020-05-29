package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.*;
import au.com.woolworths.apigee.model.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class SignUpPage extends SignuppageHelper {

  private final static Logger logger = Logger.getLogger("SignUpPage.class");
  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  private ApigeeLoginReponse apigeeLoginReponse;

  public SignUpPage(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^Customer signup for woolworths online account with date of birth \"([^\"]*)\"$")
  public void customerSignupForWoolworthsOnlineAccountWithDateOfBirth(String Date_of_Birth) throws Throwable {
    apigeeLoginReponse = iCompleteSignUPWithDOB(sharedData.accessToken, Date_of_Birth);
  }

  @Then("^Customer is able to successfully create an account$")
  public void customerIsAbleToSuccessfullyCreateAnAccount() throws Throwable {
    //Assert Response is not Null
    Assert.assertNotNull("APIGEE_V2_SIGNUP Failed", apigeeLoginReponse.getShopperid());
  }
}
