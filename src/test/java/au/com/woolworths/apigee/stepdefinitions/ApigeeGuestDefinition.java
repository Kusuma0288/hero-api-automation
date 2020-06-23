package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.Utils.Utilities;
import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.ApigeeGuestHelper;
import au.com.woolworths.apigee.model.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class ApigeeGuestDefinition extends ApigeeGuestHelper {

  private ApigeeLoginReponse response = new ApigeeLoginReponse();

  private final static Logger logger = Logger.getLogger("ApigeeGuestDefinition.class");
  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public ApigeeGuestDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @Given("^mobile user connect to apigee endpoint as guest$")
  public void mobileUserConnectToApigeeAPIEndpointAsGuest() throws Throwable {
    String deviceId = Utilities.generateRandomUUIDString();
    response = mobileUserConnectToApigeeAPIEndpointAsGuestWithPossibleOptions(null, null, deviceId);

    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestResponse = response;
    sharedData.accessToken = response.getAccess_token();
  }

  @When("^connection from user to apigee endpoint happens$")
  public void connectionFromUserToApigeeAPIEndpointHappens() {
    Assert.assertNotNull(sharedData.responseStatusCode, "Connection issue::" + sharedData.responseStatusCode);
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

  @When("^connection from user to apigee endpoint fails$")
  public void connectionFromUserToApigeeAPIEndpointFails() {
    Assert.assertNotNull(sharedData.responseStatusCode, "Connection issue::" + sharedData.responseStatusCode);
    Assert.assertTrue(sharedData.responseStatusCode.contains("401"), "Connection successful::" + sharedData.responseStatusCode);
  }

  @Then("^user successfully authenticate to apigee public api as guest$")
  public void userSuccessfullyAuthenticateToApigeePublicAPIEndpointAsGuest() {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertNotNull(response.getAccess_token(), "Access Token is Null");
    Assert.assertNotEquals(response.getRefresh_token(), "Refresh Token is Null");
  }
}
