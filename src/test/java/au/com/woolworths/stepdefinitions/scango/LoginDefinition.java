package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoginHelper;
import au.com.woolworths.model.scango.login.RewardsTokenResponse;
import au.com.woolworths.model.scango.login.ScanGoLoginResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class LoginDefinition extends LoginHelper {

  private final static Logger logger = Logger.getLogger("LoginDefinition.class");

  @Given("^a user enter valid username and password in the rewards portal$")
  public void userEnterValidUsernameAndPassword() throws Throwable {
    String authCode = iLoginWithValidRewardsCredentials();
    sharedData.rewardsAuthCode = authCode;
  }

  @Given("^a user calls the Rewards API with valid Authcode in the header$")
  public void callRewardsAPIWithValidAuthCode() throws Throwable {
    RewardsTokenResponse response = iCallRewardsTokenAPI();
    sharedData.accessToken = response.getAccess_token();
  }


  @Given("^a user calls the Login API with valid AccessToken in the header$")
  public void callLoginAPIWithValidAccessToken() throws Throwable {
    ScanGoLoginResponse response = userLoginIntoScanGo();

    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.scanGoLoginResponse = response;
    sharedData.accessToken = response.getAccess_token();

    Assert.assertNotNull("Access token is missing", response.getAccess_token());
    Assert.assertNotNull("Refresh token is missing", response.getRefresh_token());
  }

  @When("user successfully logged in")
  public void userSuccessfullyLoggedIn() {
    Assert.assertNotNull("Connection issue::" + sharedData.responseStatusCode, sharedData.responseStatusCode);
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }


}
