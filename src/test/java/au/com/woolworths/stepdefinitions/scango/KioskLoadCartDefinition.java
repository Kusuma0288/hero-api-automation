package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoadCartHelper;
import au.com.woolworths.model.scango.kiosk.KioskLoadCartResponse;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class KioskLoadCartDefinition extends LoadCartHelper {

    @When("^I call Kiosk Load Cart API$")
    public void i_call_kiosk_Load_cart_API() throws Throwable {
        KioskLoadCartResponse kioskLoadCartResponse = iCallKioskLoadCart();

        sharedData.responseStatusCode = kioskLoadCartResponse.getStatusCode();
        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

        System.out.println("KioskLoadCartDefinition  file " + kioskLoadCartResponse.toString());
    }
}
