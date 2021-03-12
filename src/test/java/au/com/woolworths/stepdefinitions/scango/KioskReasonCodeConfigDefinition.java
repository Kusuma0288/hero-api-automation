package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ReasonCodeConfigHelper;
import au.com.woolworths.model.scango.kiosk.KioskReasonCodeConfigResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class KioskReasonCodeConfigDefinition extends ReasonCodeConfigHelper {

  @Then("^I call Reason code API to get valid reason$")
  public void iCallReasonCodeApiToGetValidReason() throws Throwable {
    KioskReasonCodeConfigResponse kioskReasonCodeConfigResponse = iCallKioskReasonCodeConfigAPI();
    sharedData.kioskReasonCodeConfigResponse = kioskReasonCodeConfigResponse;
    sharedData.responseStatusCode = kioskReasonCodeConfigResponse.getStatusCode();
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

    System.out.println("KioskReasonCodeConfigResponse  file " + kioskReasonCodeConfigResponse.toString());
  }
}
