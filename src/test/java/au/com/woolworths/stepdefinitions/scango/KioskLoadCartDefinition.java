package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoadCartHelper;
import au.com.woolworths.model.scango.kiosk.KioskLoadCartResponse;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class KioskLoadCartDefinition extends LoadCartHelper {

  @When("^I call Kiosk Load Cart API$")
  public void iCallKioskLoadCartApi() throws Throwable {
    KioskLoadCartResponse kioskLoadCartResponse = iCallKioskLoadCart();
    sharedData.responseStatusCode = kioskLoadCartResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
