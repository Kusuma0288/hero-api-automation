package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.Utils.Utilities;
import au.com.woolworths.helpers.apigee.ApigeeShopperHelper;
import au.com.woolworths.model.apigee.ApigeeLoginReponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class ApigeeShopperDefinition extends ApigeeShopperHelper {

  private final static Logger logger = Logger.getLogger("ApigeeGuestDefinition.class");


  @When("^user continue to connect to apigee with default login and password$")
  public void userContinueToConnectToApigeeWithDefaultLoginAndPassword() throws Throwable {
    ApigeeLoginReponse response = userToConnectApigeewithLoginAndPassword(TestProperties.get("SHOPPER_USERNAME"), TestProperties.get("SHOPPER_PASSWORD"), sharedData.deviceId);

    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperResponse = response;
    sharedData.accessToken = response.getAccess_token();
  }

  @When("^user continue to connect to apigee with login (.*) and password (.*)$")
  public void userContinueToConnectToApigeeWithLoginNouserAutomationComAndPassword(String userName, String password) throws Throwable {
    ApigeeLoginReponse response = userToConnectApigeewithLoginAndPassword(userName, password, sharedData.deviceId);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperResponse = response;
  }

  @Then("^user fails to authenticate as shopper$")
  public void userFailsToAuthenticateAsShopper() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("401"), "Status Code is::" + sharedData.responseStatusCode);
    Assert.assertTrue(sharedData.shopperResponse.getError().equals("The email address & password combination you have entered is incorrect.  Please try again or click the forgotten password link below to reset your password."), "Error Message is::" + sharedData.shopperResponse.getError());
    Assert.assertNull(sharedData.shopperResponse.getAccess_token(), "Access Token is NOT NULL");
    Assert.assertNull(sharedData.shopperResponse.getRefresh_token(), "Refresh Token is NOT NULL");
  }

  @Then("^user is successfully authenticated with all session details$")
  public void userIsSuccessfullyAuthenticatedWithAllSessionDetails() throws Throwable {
    Assert.assertTrue(sharedData.shopperResponse.getStatusCode().equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertNotNull(sharedData.shopperResponse.getAccess_token(), "Access Token is Null");
    Assert.assertNotNull(sharedData.shopperResponse.getRefresh_token(), "Refresh Token is Null");

  }

  @Given("^user continue to connect to apigee with default login and password using apikey (.*)$")
  public void userContinueToConnectToApigeeWithDefaultLoginAndPasswordUsingAPIKey(String apiKey) throws Throwable {
    ApigeeLoginReponse response = userToConnectApigeewithLoginAndPasswordWithAPIKey(TestProperties.get("SHOPPER_USERNAME"), TestProperties.get("SHOPPER_PASSWORD"), Utilities.generateRandomUUIDString(), apiKey);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperResponse = response;
  }

  @Then("^user fails to authenticate as apikey is invalid$")
  public void userFailsToAuthenticateAsAPIKeyIsInvalid() throws Throwable {
    Assert.assertTrue(sharedData.shopperResponse.getStatusCode().equals("401"), "Expected Status Code is 401 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(sharedData.shopperResponse.getError().equals("invalid_client"), "API error is not coming as expected (invalid_client");
    Assert.assertNull(sharedData.shopperResponse.getAccess_token(), "Access Token is Not Null");
    Assert.assertNull(sharedData.shopperResponse.getRefresh_token(), "Refresh Token is Not Null");
  }

  @When("^user continue to connect to apigee with login containing (.*)$")
  public void userContinueToConnectToApigeeWithLoginContainingUsername(String userName) throws Throwable {
    ApigeeLoginReponse response = userToConnectApigeewithLoginAndPassword(userName + "@" + TestProperties.get("EMAIL_DOMAINNAME"), TestProperties.get("SHOPPER_PASSWORD"), sharedData.deviceId);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperResponse = response;
    sharedData.accessToken = response.getAccess_token();

  }

  @When("^user continue to connect to apigee with login username as \"([^\"]*)\"$")
  public void userContinueToConnectToApigeeWithSpecifiedUsername(String username) throws Throwable {
    ApigeeLoginReponse response = userToConnectApigeewithLoginAndPassword(TestProperties.get(username), TestProperties.get("SHOPPER_PASSWORD"), sharedData.deviceId);

    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperResponse = response;
    sharedData.accessToken = response.getAccess_token();
  }

}
