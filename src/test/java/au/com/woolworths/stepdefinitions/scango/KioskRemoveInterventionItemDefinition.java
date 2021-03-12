package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.RemoveItemHelper;
import au.com.woolworths.model.scango.kiosk.KioskRemoveInterventionResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

import java.io.IOException;

public class KioskRemoveInterventionItemDefinition extends RemoveItemHelper {

  @Then("^I verify Intervention items are removed through Kiosk Remove Intervention API$")
  public void iCallRemoveInterventionItemAPI() throws IOException {
    KioskRemoveInterventionResponse kioskRemoveInterventionResponse = iClickOnKioskRemoveInterventionAPI();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

}
