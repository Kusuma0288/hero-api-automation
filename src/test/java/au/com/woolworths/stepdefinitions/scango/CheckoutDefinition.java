package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.CheckoutHelper;
import au.com.woolworths.model.scango.checkout.CheckoutResponse;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutDefinition extends CheckoutHelper {

  @When("^I call Checkout API$")
  public void iCallCheckoutAPI() throws Throwable {
    CheckoutResponse checkoutResponse = iClickOnCheckout();
    sharedData.checkoutResponse = checkoutResponse;
    sharedData.responseStatusCode = checkoutResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
