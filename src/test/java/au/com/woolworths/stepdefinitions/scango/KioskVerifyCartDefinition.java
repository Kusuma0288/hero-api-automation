package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.KioskVerifyCartHelper;
import au.com.woolworths.model.scango.kiosk.VerifyCartResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class KioskVerifyCartDefinition extends KioskVerifyCartHelper {


    @Then("^I call Verify Cart API$")
    public void i_call_Verify_Cart_API() throws Throwable {
        VerifyCartResponse verifyCartResponse = iCallVerifyCartAPI();
        sharedData.responseStatusCode = verifyCartResponse.getStatusCode();

        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
    }
}
