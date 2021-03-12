package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ViewReceiptHelper;
import au.com.woolworths.model.scango.menu.ViewReceiptResponse;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class ViewReceiptDefinition extends ViewReceiptHelper {


  @Then("^I verify the View receipt API for the transaction$")
  public void verifyViewReceiptAPI() throws Throwable {
    ViewReceiptResponse viewReceiptResponse = iCallViewReceiptAPI();
    sharedData.responseStatusCode = viewReceiptResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
