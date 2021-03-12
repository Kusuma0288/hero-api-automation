package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ProductTypesHelper;
import au.com.woolworths.model.scango.scanitems.AddItemResponse;
import cucumber.api.java.en.Given;

public class ProductTypesDefinition extends ProductTypesHelper {

  @Given("^I add a \"([^\"]*)\" into cart$")
  public void iAddItemIntoCart(String type) throws Throwable {
    String productType = type;

    AddItemResponse addItemResponse = iClickOnScanItem(productType);
    sharedData.lineNumber = addItemResponse.getItems().get(0).getLinenumber();

    System.out.println("AddItemResponse  file " + addItemResponse.toString());

  }
}
