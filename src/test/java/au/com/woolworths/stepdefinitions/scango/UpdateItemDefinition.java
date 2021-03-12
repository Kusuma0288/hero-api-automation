package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.UpdateItemHelper;
import au.com.woolworths.model.scango.scanitems.UpdateItemResponse;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.io.IOException;

public class UpdateItemDefinition extends UpdateItemHelper {


  @When("^I call Update Weight API$")
  public void callUpdateWeightAPI() throws IOException {
    UpdateItemResponse updateItemResponse = iCallUpdateWeightAPI();

    sharedData.responseStatusCode = updateItemResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }

  @When("^I call Update Quantity API$")
  public void callUpdateQuantityAPI() throws IOException {
    UpdateItemResponse updateItemResponse = iCallUpdateQuantityAPI();

    sharedData.responseStatusCode = updateItemResponse.getStatusCode();

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
