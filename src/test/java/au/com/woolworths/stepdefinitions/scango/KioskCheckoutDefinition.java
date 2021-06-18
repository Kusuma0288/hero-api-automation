package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.CheckoutHelper;
import au.com.woolworths.model.scango.errorresponse.KioskErrorResponse;
import au.com.woolworths.model.scango.kiosk.KioskCheckoutResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class KioskCheckoutDefinition extends CheckoutHelper {

  @When("^I call Kiosk Checkout API$")
  public void iCallKioskCheckoutApi() throws Throwable {
    KioskCheckoutResponse kioskCheckoutResponse = iClickOnKioskCheckout();
    sharedData.kioskCheckoutResponse = kioskCheckoutResponse;
    sharedData.responseStatusCode = kioskCheckoutResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

  @Then("I verify Kiosk Checkout API throws valid error response")
  public void iVerifyKioskCheckoutAPINoValidItemsErrorResponse() throws IOException {
    KioskErrorResponse kioskErrorResponse = iClickOnKioskCheckoutNoValidErrorResponse();
    Assert.assertTrue(kioskErrorResponse.getMessage().equalsIgnoreCase("NoValidItemsInCart"));
    Assert.assertTrue(kioskErrorResponse.getDescription().equalsIgnoreCase("There are no other items to purchase in this transaction. No payment at Kiosk required."));

  }
}
