package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.ShoppingAislesResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class ShoppingAislesDefinition extends ShopperHelper {

  private final static Logger logger = Logger.getLogger("ShoppingAislesDefinition.class");
  private ShoppingAislesResponse shoppingAislesResponse;


  @When("^customer calls shopping aisles api$")
  public void customerCallsShoppingAislesApi() throws Throwable {
    shoppingAislesResponse = iGetShoppingAisles();

  }

  @Then("^verify shopping aisles api responds shopping aisles and its categories$")
  public void verifyShoppingAislesApiRespondsShoppingAislesAndItsCategories() {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(shoppingAislesResponse.getVsAisles().size() > 0, "List of Aisles is not empty");
    Assert.assertTrue(shoppingAislesResponse.getVsAisles().get(0).getVsCategories().size() > 0, "Aisles Categories list cannot be empty");
    Assert.assertNotNull(shoppingAislesResponse.getVsAisles().get(0).getName(), "Product name cannot be NULL");
    Assert.assertNotNull(shoppingAislesResponse.getVsAisles().get(0).getUrlFriendlyName(), "Product friendly name cannot be NULL");
  }
}
