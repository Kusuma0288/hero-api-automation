package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoginHelper;
import au.com.woolworths.model.scango.kiosk.KioskLoginResponse;
import au.com.woolworths.model.scango.login.RewardsTokenResponse;
import au.com.woolworths.model.scango.login.ScanGoLoginResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.io.IOException;
import java.util.logging.Logger;

public class KioskLoginDefinition extends LoginHelper{

    private final static Logger logger = Logger.getLogger("KioskLoginDefinition.class");

    @And("^I call Team member barcode API to get team member access token$")
    public void i_Call_Team_Member_Barcode_API_To_Get_Team_Member_Access_Token() throws IOException {
        KioskLoginResponse response = iCallKioskLoginAPI();
        sharedData.accessToken = response.getAccessToken();
    }
}
