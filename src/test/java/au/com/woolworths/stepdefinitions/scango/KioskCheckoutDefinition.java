package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.CheckoutHelper;
import au.com.woolworths.model.scango.kiosk.KioskCheckoutResponse;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class KioskCheckoutDefinition extends CheckoutHelper {

  @When("^I call Kiosk Checkout API$")
  public void iCallKioskCheckoutApi() throws Throwable {
    KioskCheckoutResponse kioskCheckoutResponse = iClickOnKioskCheckout();
    sharedData.kioskCheckoutResponse = kioskCheckoutResponse;
    sharedData.responseStatusCode = kioskCheckoutResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
