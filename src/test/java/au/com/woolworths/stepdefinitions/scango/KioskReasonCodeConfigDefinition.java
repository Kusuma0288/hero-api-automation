package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ReasonCodeConfigHelper;
import au.com.woolworths.model.scango.kiosk.KioskReasonCodeConfigResponse;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class KioskReasonCodeConfigDefinition extends ReasonCodeConfigHelper {

  @Then("^I call Reason code API to get valid reason$")
  public void iCallReasonCodeApiToGetValidReason() throws Throwable {
    KioskReasonCodeConfigResponse kioskReasonCodeConfigResponse = iCallKioskReasonCodeConfigAPI();
    sharedData.responseStatusCode = kioskReasonCodeConfigResponse.getStatusCode();
    sharedData.voidTransactionReasonCode = kioskReasonCodeConfigResponse.getVoidtransaction().get(0);

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
