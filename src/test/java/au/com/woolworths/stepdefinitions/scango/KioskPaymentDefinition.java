package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.PaymentHelper;
import au.com.woolworths.model.scango.kiosk.KioskPaymentResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class KioskPaymentDefinition extends PaymentHelper {

  @Then("^I verify payment is successfully completed through Kiosk Payment API$")
  public void verifyKioskPaymentIsSuccessful() throws Throwable {
    Double balanceDue = sharedData.kioskCheckoutResponse.getBalancedue();
    KioskPaymentResponse kioskPaymentResponse = iCallKioskPaymentAPI(balanceDue);

    sharedData.responseStatusCode = kioskPaymentResponse.getStatusCode();
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

}
