package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoginHelper;
import au.com.woolworths.model.scango.login.PreAuthResponse;
import au.com.woolworths.model.scango.login.RewardsTokenResponse;
import au.com.woolworths.model.scango.login.ScanGoLoginResponse;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

// TODO: below are latest cucumber api's and framework were using old apis
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.testng.Assert;


import java.io.IOException;
import java.util.logging.Logger;

public class LoginDefinition extends LoginHelper{

    private final static Logger logger = Logger.getLogger("LoginDefinition.class");

    @Given("^a user calls the PreAuth API$")
    public void a_user_calls_the_PreAuth_API() throws Throwable {
        PreAuthResponse response = iCallPreAuthAPI();

       // sharedData.responseStatusCode = response.getStatusCode();
        sharedData.preAuthResponse = response;
        sharedData.rewardsUrl = "https://uat-accounts.woolworthsrewards.com.au/code/ios?referrer=scan_n_go&state=dfdsfdfdfddddfd"; //response.getRewards_url();
        System.out.println("PreAuthResponse Definition file " +response.toString());
    }

    @Given("^a user enter valid username and password in the rewards portal$")
    public void a_user_enter_valid_username_and_password_in_the_rewards_portal() throws Throwable {
        String authCode = iLoginWithValidRewardsCredentials();
        sharedData.rewardsAuthCode = authCode;
    }

    @Given("^a user calls the Rewards API with valid Authcode in the header$")
    public void a_user_calls_the_Rewards_API_with_valid_Authcode_in_the_header() throws Throwable {
        RewardsTokenResponse response = iCallRewardsTokenAPI();
        sharedData.accessToken = response.getAccess_token();
        System.out.println("RewardsTokenResponse Definition file " +response.toString());
    }


    @Given("^a user calls the Login API with valid AccessToken in the header$")
    public void a_user_calls_the_Login_API_with_valid_AccessToken_in_the_header() throws Throwable {
        ScanGoLoginResponse response = userLoginIntoScanGo();

        sharedData.responseStatusCode = response.getStatusCode();
        sharedData.scanGoLoginResponse = response;
        sharedData.accessToken = response.getAccess_token();

        System.out.println("Login Definition file " +response.toString());
    }

    @When("user successfully logged in")
    public void user_successfully_logged_in() {
        Assert.assertNotNull(sharedData.responseStatusCode, "Connection issue::" + sharedData.responseStatusCode);
        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
    }


}
