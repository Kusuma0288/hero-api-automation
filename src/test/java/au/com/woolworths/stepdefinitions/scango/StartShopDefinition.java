package au.com.woolworths.stepdefinitions.scango;

import au.com.woolworths.helpers.scango.StartShopHelper;
import au.com.woolworths.model.scango.startshop.StartShopResponse;
import cucumber.api.java.en.When;

public class StartShopDefinition extends StartShopHelper {

  @When("^user calls the Start Shop API$")
  public void callStartShopAPI() throws Throwable {
    StartShopResponse startShopResponse = iClickOnStartShopAPI();

    sharedData.storeID = startShopResponse.getStoreid();
    sharedData.cartID = startShopResponse.getCartid();
  }
}
