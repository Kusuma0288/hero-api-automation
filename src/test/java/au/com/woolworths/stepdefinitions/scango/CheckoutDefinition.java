package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.CheckoutHelper;
import au.com.woolworths.model.scango.checkout.CheckoutResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class CheckoutDefinition extends CheckoutHelper {

@When("^I call Checkout API$")
    public void i_call_Checkout_API() throws Throwable {
        CheckoutResponse checkoutResponse = iClickOnCheckout();

        sharedData.checkoutResponse = checkoutResponse;
        sharedData.responseStatusCode = checkoutResponse.getStatusCode();

        Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
        System.out.println("CheckoutResponse  file " + checkoutResponse.toString());
    }
}
