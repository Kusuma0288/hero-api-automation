package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.LoadCartHelper;
import au.com.woolworths.model.scango.scanitems.LoadCartResponse;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class LoadCartDefinition extends LoadCartHelper {

  @When("^I call Load cart API$")
  public void iCallLoadCartAPI() throws Throwable {
    LoadCartResponse loadCartResponse = iCallLoadCart();

    sharedData.responseStatusCode = loadCartResponse.getStatusCode();
    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);

    System.out.println("LoadCartDefinition  file " + loadCartResponse.toString());
  }
}
