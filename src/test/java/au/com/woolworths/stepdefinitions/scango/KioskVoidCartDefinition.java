package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.DeleteCartHelper;
import au.com.woolworths.model.scango.kiosk.KioskDeleteCartResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

public class KioskVoidCartDefinition extends DeleteCartHelper {

  @Then("^I verify transaction is voided through Kiosk Void Transaction API$")
  public void verifyKioskVoidTransactionApi() throws IOException {

    KioskDeleteCartResponse kioskDeleteCartResponse = iClickOnKioskDeleteCartAPI();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
