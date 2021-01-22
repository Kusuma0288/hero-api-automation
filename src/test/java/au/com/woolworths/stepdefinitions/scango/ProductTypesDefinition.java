package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.ProductTypesHelper;
import au.com.woolworths.model.scango.scanitems.AddItemResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductTypesDefinition extends ProductTypesHelper {

//    @Then("^verify user can able to add Simple product$")
//    public void verify_user_can_able_to_add_Simple_product() throws Throwable {
//
//        AddItemResponse addItemResponse = iClickOnScanItem();
//
//        System.out.println("AddItemResponse  file " +addItemResponse.toString());
//    }

    @Given("^I add a \"([^\"]*)\" into cart$")
    public void i_add_a_into_cart(String type) throws Throwable {
        System.out.println("type  def " +type);
        String productType = type;

        AddItemResponse addItemResponse = iClickOnScanItem(productType);

        System.out.println("AddItemResponse  file " +addItemResponse.toString());

    }
}
