package au.com.woolworths.stepdefinitions.metis;

import au.com.woolworths.model.helios.login.AuthCode;
import au.com.woolworths.utils.TestProperties;
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
    Assert.assertEquals("Status code for Link unsuccessful", "200", sharedData.responseStatusCode);
    logger.info("Showing the Session Token: " + sharedData.sessionToken);
  }

  @Given("^I get the authcode of card number \"([^\"]*)\" of an \"([^\"]*)\" user$")
  public void getTheAuthcodeOfCardNumberOfAnUser(String rewardUser, String clientOS) throws Throwable {
    AuthCode authCode = getAuthCode(rewardUser, clientOS);
    Assert.assertNotNull("AuthCode is not returned", authCode);
    Assert.assertEquals("Status code for AuthCode unsuccessful", "200", sharedData.responseStatusCode);
    sharedData.authCode = authCode.getData().getAuthCode();
    logger.info("Showing the AuthCode: " + sharedData.authCode);

  }

  @When("^the user logs in with their authcode$")
  public void logsInWithAuthcode() throws Throwable {
    sharedData.deviceId = Utilities.generateRandomUUIDString();
    loginResponse = postLoginUsingAuthCode(sharedData.deviceId, sharedData.sessionToken);
    Assert.assertEquals("Status code for Login unsuccessful", "200", sharedData.responseStatusCode);
    sharedData.accessToken = loginResponse.getData().getAuth().getData().getAccessToken();
    logger.info("Showing the Access Token: " + sharedData.accessToken);

  }

  @Then("^the user should be logged into the Rewards App$")
  public void shouldBeLoggedIn() {
    Assert.assertEquals("Login was unsuccessful", "RewardsLoginSuccess", loginResponse.getType());
    Assert.assertEquals("Login response is missing a valid auth access token", 28, loginResponse.getData().getAuth().getData().getAccessToken().length());
    Assert.assertEquals("Login response doesn't include a successful auth object", "RewardsAccessTokenSuccess", loginResponse.getData().getAuth().getType());
    Assert.assertEquals("Login response doesn't include a valid auth access token expiry value", "1199", loginResponse.getData().getAuth().getData().getAccessTokenExpiresIn().toString());
    Assert.assertEquals("Login response doesn't include a valid auth refresh token expiry value", "31535999", loginResponse.getData().getAuth().getData().getRefreshTokenExpiresIn().toString());
    Assert.assertEquals("Login response is missing a valid rewards card number", 13, loginResponse.getData().getRewardsCard().getData().getCardNumber().length());
    Assert.assertEquals("Login response is missing a valid display rewards card number", 15, loginResponse.getData().getRewardsCard().getData().getDisplayCardNumber().length());
    Assert.assertEquals("Login response doesn't include a successful rewards card object", "PrimaryRewardsCard", loginResponse.getData().getRewardsCard().getType());
    Assert.assertEquals("Login response doesn't include a successful analytics object", "AnalyticsData", loginResponse.getData().getAnalytics().getType());
  }


  @Given("^a user logs in the rewards app with card number \"([^\"]*)\"$")
  public void aUserLogsInTheRewardsWithCardNumber(String rewardUser) throws Throwable {
    hasLinkSessionToken();
    getTheAuthcodeOfCardNumberOfAnUser(TestProperties.get(rewardUser), "iOS");
    logsInWithAuthcode();
    shouldBeLoggedIn();
  }

}