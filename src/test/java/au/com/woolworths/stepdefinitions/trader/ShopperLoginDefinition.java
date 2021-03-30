package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.CheckoutAddressResponse;
import au.com.woolworths.model.trader.ShopperLoginResponseV2;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.Utilities;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.Random;
import java.util.logging.Logger;

public class ShopperLoginDefinition extends ShopperHelper {
  private static final Logger logger = Logger.getLogger("ShopperLoginDefinition.class");
  private ShopperLoginResponseV2 response = new ShopperLoginResponseV2();


  @Then("^apigee continue to connect to trader public api endpoint with default login and password$")
  public void apigeeContinueToConnectToTraderPublicAPIEndpointwithDefaultLoginAndPassword() throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(TestProperties.get("SHOPPER_USERNAME"), TestProperties.get("SHOPPER_PASSWORD"), sharedData.deviceId);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
  }

  @Then("^apigee connect to trader public api endpoint with default login and password$")
  public void apigeeConnectToTraderPublicAPIEndpointwithDefaultLoginAndPassword() throws Throwable {
    Random rand = new Random();
    int num = rand.nextInt(4) + 1;
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(TestProperties.get("SHOPPER_USERNAME" + num), TestProperties.get("SHOPPER_PASSWORD"), Utilities.generateRandomUUIDString());
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
  }

  @Given("^apigee connect to trader public api endpoint with default login and password using apikey (.*)$")
  public void apigeeConnectToTraderPublicAPIEndpointWithDefaultLoginAndPasswordUsingAPIkey(String apiKey) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPasswordUsingAPIkey(TestProperties.get("SHOPPER_USERNAME"), TestProperties.get("SHOPPER_PASSWORD"), Utilities.generateRandomUUIDString(), apiKey);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
  }

  @Given("^apigee connect to trader public api endpoint with login \"(.*)\" and password \"(.*)\" using apikey \"(.*)\"$")
  public void apigeeConnectToTraderPublicAPIEndpointWithLoginAndPasswordUsingAPIkey(String userName, String password, String apiKey) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPasswordUsingAPIkey(userName, password, Utilities.generateRandomUUIDString(), apiKey);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
  }

  @Given("^apigee connect to trader public api endpoint with login containing (.*) and password$")
  public void apigeeConnectToTraderPublicApiEndpointWithLoginContainingAndPassword(String loginName) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(TestProperties.get(loginName), TestProperties.get("SHOPPER_PASSWORD"), Utilities.generateRandomUUIDString());
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();

  }

  @Given("^apigee connect to trader public api endpoint from guest to logged in user with username (.*) and password$")
  public void loginContainingAndPasswordFromGuest(String loginName) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(TestProperties.get(loginName), TestProperties.get("SHOPPER_PASSWORD"), sharedData.deviceId);
    Assert.assertNotNull(response.getAuthToken(), "User was not authenticated");
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
    resetTraderHeaderList();
    if (response.getAuthToken() != null) {
      sharedData.deliveryAddressIdContainer = response.getSession().getDeliveryAddressID();
    }

  }

  @Then("^apigee connect to trader public api endpoint with login (.*) and password (.*)$")
  public void apigeeConnectToTraderPublicAPIEndpointWithLoginAndPassword(String userName, String password) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(userName, password, Utilities.generateRandomUUIDString());
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
    if (response.getAuthToken() != null) {
      sharedData.deliveryAddressId = response.getSession().getDeliveryAddressID();
    }

  }

  @Then("^apigee continue to connect to trader public api endpoint with login (.*) and password (.*)$")
  public void apigeeContinueToConnectToTraderPublicAPIEndpointwithLoginAndPassword(String userName, String password) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(userName, password, sharedData.deviceId);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
  }

  @When("^apigee continue to connect to trader public api endpoint with login containing (.*) and password$")
  public void apigeeContinueToConnectToTraderPublicApiEndpointWithLoginContainingAndPassword(String loginName) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(TestProperties.get(loginName), TestProperties.get("SHOPPER_PASSWORD"), sharedData.deviceId);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
    if (response.getAuthToken() != null) {
      sharedData.deliveryAddressIdContainer = response.getSession().getDeliveryAddressID();
    }

  }

  @When("^apigee connect to trader public api endpoint with newly created login and password \"(.*)\"$")
  public void apigeeConnectToTraderPublicAPIEndpointWithNewlyCreatedLoginAndPassword(String password) throws Throwable {
    response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(sharedData.signedUpEmail, password, sharedData.deviceId);
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
    if (response.getAuthToken() != null) {
      sharedData.deliveryAddressId = response.getSession().getDeliveryAddressID();
    }
  }

  @When("^apigee try to connect to trader public api endpoint with newly created login and invalid password for (\\d+) attempts$")
  public void apigeeTryToConnectToTraderPublicAPIEndpointWithNewlyCreatedLoginAndPasswordForAttempt(int noOfAttempt) throws Throwable {
    for (int i = 1; i <= noOfAttempt; i++) {
      response = apigeeToTraderPublicAPIEndpointwithLoginAndPassword(sharedData.signedUpEmail, Utilities.getSaltString(), sharedData.deviceId);
      if (response.getStatusCode().equals("500")) {
        //TO-DO This is a known issue and not always reproducible
        continue;
      }
      if (i <= 3) {
        Assert.assertTrue(response.getLoginResult().equals("Invalid"), "The Login Result should be invalid");
        Assert.assertTrue(invalidLoginValidationMap.get("Invalid").equals(response.getLoginMessage()), "The Message is not showing as expected::" + invalidLoginValidationMap.get("Invalid"));
      } else if (i == 4) {
        Assert.assertTrue(response.getLoginResult().equals("RedirectPasswordRemainder"), "Redirect password reminder message is not showing");
        Assert.assertTrue(invalidLoginValidationMap.get("RedirectPasswordRemainder").equals(response.getLoginMessage()), "The Message is not showing as expected::" + invalidLoginValidationMap.get("RedirectPasswordRemainder"));
      } else if (i == 5) {
        Assert.assertTrue(response.getLoginResult().equals("LockedOut"), "Locked out message is not showing");
        Assert.assertTrue(invalidLoginValidationMap.get("LockedOut").equals(response.getLoginMessage()), "The Message is not showing as expected::" + invalidLoginValidationMap.get("LockedOut"));
      } else if (i >= 6) {
        Assert.assertTrue(response.getLoginResult().equals("LoginIsLocked"), "Login Locked out message is not showing");
        Assert.assertTrue(invalidLoginValidationMap.get("LoginIsLocked").equals(response.getLoginMessage()), "The Message is not showing as expected::" + invalidLoginValidationMap.get("LoginIsLocked"));
      }

    }
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.shopperLoginResponseV2 = response;
    sharedData.authToken = response.getAuthToken();
  }

  @When("^connection from apigee to trader public api shopper endpoint happens$")
  public void connectionFromApigeeToTraderPublicApiEndpointHappens() throws Throwable {
    Assert.assertNotNull(sharedData.responseStatusCode, "Connection issue::" + sharedData.responseStatusCode);
  }

  @Then("^apigee successfully authenticate to trader public api endpoint as logged in user$")
  public void apigeeSuccessfullyAuthenticateToTraderPublicAPIEndpointAsLoggedInUser() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Status Code is::" + sharedData.responseStatusCode);
    Assert.assertNotNull(response.getAuthToken());
    Assert.assertTrue(response.getLoginMessage().equals("Login Success"), "Some issue with the login message");
  }

  @Then("^apigee successfully authenticate to trader public api endpoint as shopper with all session details$")
  public void apigeeSuccessfullyAuthenticateToTraderPublicAPIEndpointAsShopperWithAllSessionDetails() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Status Code is::" + sharedData.responseStatusCode);
  }

  @Then("^apigee failed to authenticate to trader public api endpoint as shopper$")
  public void apigeeFailedToAuthenticateToTraderPublicAPIEndpointAsShopper() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("401"), "Status Code is::" + sharedData.responseStatusCode);
    //  Assert.assertTrue(response.getLoginResult().equals("Invalid"),"Login Result is not matching with \'Invalid\'");
    Assert.assertTrue(response.getLoginMessage().equals("The email address & password combination you have entered is incorrect.  Please try again or click the forgotten password link below to reset your password."), "Error Message is::" + response.getLoginMessage());
    Assert.assertNull(response.getAuthToken());
    Assert.assertNull(response.getRefreshToken());
    Assert.assertNull(response.getSession());
    Assert.assertNotNull(response.getResponseStatus());
  }

  @Then("^apigee failed to authenticate apikey to trader public api endpoint$")
  public void apigeeFailedToAuthenticateAPIkeyToTraderPublicAPIEndpoint() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("400"), "Status Code is::" + sharedData.responseStatusCode + "Expected::400");
    Assert.assertNotNull(response.getResponseStatus());
    Assert.assertTrue(response.getResponseStatus().getErrorCode().equals("InvalidData"), "Error Code is not matching " + response.getResponseStatus().getErrorCode() + " as expected: InvalidData");
    Assert.assertTrue(response.getResponseStatus().getMessage().matches("Your api key (.*) is invalid."), "Error Message is not matching " + response.getResponseStatus().getMessage() + " as expected: Your apikey {apikey} is invalid");
  }

  @Then("^apigee failed to authenticate to trader public api endpoint as shopper and is locked out$")
  public void apigeeFailedToAuthenticateToTraderPublicAPIEndpointAsShopperAndIsLockedOut() throws Throwable {
    //Assert.assertTrue(sharedData.responseStatusCode.equals("401"), "Status Code is::" + sharedData.responseStatusCode);
    //Assert.assertTrue(response.getLoginResult().equals("LoginIsLocked"),"Login Result is not matching with \'LoginIsLocked\'");
    //Assert.assertTrue(response.getLoginMessage().equals("Your account has been locked. Please contact our Customer Service Team on 1800 000 610 to unlock your account."), "Error Message is::" + response.getLoginMessage());
    Assert.assertNull(response.getAuthToken());
    Assert.assertNull(response.getRefreshToken());
    Assert.assertNull(response.getSession());
    Assert.assertNotNull(response.getResponseStatus());
  }

  @When("^apigee set the fulfilment store id to \"([^\"]*)\"$")
  public void apigeeSetTheFulfilmentStoreIdTo(int fulfilmentStoreId) throws Throwable {
    CheckoutAddressResponse checkoutAddressResponse = iSetTheFulfilmentStoreId(fulfilmentStoreId);
    Assert.assertTrue(checkoutAddressResponse.isIsSuccessful());
    sharedData.fulfilmentStoreId = fulfilmentStoreId;

  }

  @Then("^I verify the login to check the fulfilment store id is set$")
  public void iVerifyTheLoginToCheckTheFulfilmentStoreIdIsSet() throws Throwable {
    Assert.assertTrue(sharedData.shopperLoginResponseV2.getSession().getFulfilmentStoreID() == sharedData.fulfilmentStoreId, "Fulfilment Store Id found::" + sharedData.shopperLoginResponseV2.getSession().getFulfilmentStoreID() + " is not set correctly as expected::" + sharedData.fulfilmentStoreId);
  }

  @Then("^I check the delivery method to be \"(.*)\"$")
  public void iCheckTheDeliveryMethodToBe(String deliveryMethod) throws Throwable {
    Assert.assertTrue(sharedData.shopperLoginResponseV2.getSession().getDeliveryMethod().equals(deliveryMethod), "Delivery Method is not matching");
  }
}
