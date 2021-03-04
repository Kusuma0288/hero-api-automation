package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ProductTypesHelper;
import au.com.woolworths.model.scango.scanitems.AddItemResponse;
import cucumber.api.java.en.Given;
import org.testng.Assert;

public class ProductTypesDefinition extends ProductTypesHelper {
  @Given("^I add a \"([^\"]*)\" into cart$")
  public void iAddItemIntoCart(String type) throws Throwable {
    String productType = type;
    AddItemResponse addItemResponse = iClickOnScanItem(productType);

    Assert.assertTrue(sharedData.responseStatusCode.contains("200"), "Connection not successful::" + sharedData.responseStatusCode);
  }
}
