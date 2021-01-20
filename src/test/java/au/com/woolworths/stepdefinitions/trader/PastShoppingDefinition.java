package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.PastShopHelper;
import au.com.woolworths.model.trader.PastShoppingListResponse;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class PastShoppingDefinition extends PastShopHelper {

  private final static Logger logger = Logger.getLogger("PastShoppingDefinition.class");
  private PastShoppingListResponse pastShoppingListResponse;


  @When("^customer calls past shopping list api$")
  public void customerCallsShoppingAislesApi() throws Throwable {
    pastShoppingListResponse = iGetShoppingAisles();

  }

  @Then("^verify past shopping api responds with right response$")
  public void verifyShoppingAislesApiRespondsShoppingAislesAndItsCategories() {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(pastShoppingListResponse.getItems().size() > 0, "List of past shops is empty");
    Assert.assertEquals(Integer.parseInt(pastShoppingListResponse.getTotalItemCount()) , pastShoppingListResponse.getItems().size() , "count of returned list and total item count field should be same");
    Assert.assertEquals(pastShoppingListResponse.getPage(), "1","Page should be 1");
    Assert.assertEquals(pastShoppingListResponse.getPageSize(), "25","Page size should be 10");
    Assert.assertNull(pastShoppingListResponse.getMessage(), "Message should be NULL");
 }
}
