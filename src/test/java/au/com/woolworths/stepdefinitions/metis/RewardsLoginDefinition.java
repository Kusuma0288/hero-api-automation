package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.utils.Utilities;
import au.com.woolworths.helpers.metis.LoginHelper;
import au.com.woolworths.model.metis.authentication.LinkResponse;
import au.com.woolworths.model.metis.authentication.LoginResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class RewardsLoginDefinition extends LoginHelper {

  private final static Logger logger = Logger.getLogger("RewardsLoginDefinition.class");
  private LoginResponse loginResponse;

  @Given("^a user has a Link session token$")
  public void hasLinkSessionToken() throws Throwable {
    LinkResponse linkResponse = getLinkSession();
    sharedData.sessionToken = linkResponse.getSessionToken();
    logger.info("Showing the Session Token Response: " + sharedData.sessionToken);
  }

  @When("^the user logs in with their authcode$")
  public void logsInWithAuthcode() throws Throwable {
    String deviceId = Utilities.generateRandomUUIDString();
    sharedData.deviceId = deviceId;
    String sessionToken = sharedData.sessionToken;
    loginResponse = postLoginUsingAuthCode(deviceId, sessionToken);
    sharedData.accessToken = loginResponse.getAuth().getAccessToken();
  }

  @Then("^the user should be logged into the Rewards App$")
  public void shouldBeLoggedIn() {
    Assert.assertEquals("Login was unsuccessful", "RewardsLoginSuccess", loginResponse.getType());
    Assert.assertEquals("Login response is missing a valid auth access token", 28, loginResponse.getAuth().getAccessToken().length());
    Assert.assertEquals("Login response doesn't include a successful auth object", "RewardsAccessTokenSuccess", loginResponse.getAuth().getType());
    Assert.assertEquals("Login response doesn't include a valid auth access token expiry value", "1199", loginResponse.getAuth().getAccessTokenExpiresIn().toString());
    Assert.assertEquals("Login response doesn't include a valid auth refresh token expiry value", "31535999", loginResponse.getAuth().getRefreshTokenExpiresIn().toString());
    Assert.assertEquals("Login response is missing a valid rewards card number", 13, loginResponse.getRewardsCard().getCardNumber().length());
    Assert.assertEquals("Login response is missing a valid display rewards card number", 15, loginResponse.getRewardsCard().getDisplayCardNumber().length());
    Assert.assertEquals("Login response doesn't include a successful rewards card object", "PrimaryRewardsCard", loginResponse.getRewardsCard().getType());
    Assert.assertEquals("Login response doesn't include a successful analytics object", "AnalyticsData", loginResponse.getRewardsAnalytics().getType());
  }
}