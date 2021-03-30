package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.RefreshTokenHelper;
import au.com.woolworths.model.scango.login.ScanGoRefreshTokenResponse;
import cucumber.api.java.en.And;
import org.testng.Assert;

public class RefreshTokenDefinition extends RefreshTokenHelper {

  @And("^a user calls the Refresh Token API to get new Access Token$")
  public void iCallRefreshTokenAPI() throws Throwable {
    ScanGoRefreshTokenResponse response = refreshTokenAPI();
    sharedData.accessToken = response.getAccess_token();
    sharedData.refreshToken = response.getRefresh_token();

    Assert.assertNotNull("Access token is missing", response.getAccess_token());
    Assert.assertNotNull("Refresh token is missing", response.getRefresh_token());
  }
}