package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ProductTypesHelper;
import au.com.woolworths.model.scango.scanitems.AddItemResponse;
import cucumber.api.java.en.Given;

public class AddItemDefinition extends ProductTypesHelper {

 @Given("^I add a \"([^\"]*)\" into cart$")
    public void i_add_a_into_cart(String type) throws Throwable {
        String productType = type;

        AddItemResponse addItemResponse = iClickOnScanItem(productType);
        sharedData.lineNumber = addItemResponse.getItems().get(0).getLinenumber();

        System.out.println("AddItemResponse  file " +addItemResponse.toString());

    }
}
