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
  private String pageNumber, pageSize;


  @When("^customer calls past shopping list api with number of pages as (.*) and pagesize as (.*)$")
  public void customerCallsShoppingAislesApi(String pageNumber, String pageSize) throws Throwable {
    this.pageNumber =pageNumber;
    this.pageSize=pageSize;
    pastShoppingListResponse = iGetShoppingAisles( pageNumber,  pageSize);
    System.out.println(pageNumber+ "  "+ pageSize);

  }

  @Then("^verify past shopping api responds with right response$")
  public void verifyShoppingAislesApiRespondsShoppingAislesAndItsCategories() {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(pastShoppingListResponse.getItems().size() > 0, "List of past shops is empty");
    Assert.assertEquals(Integer.parseInt(pastShoppingListResponse.getTotalItemCount()) , pastShoppingListResponse.getItems().size() , "count of returned list and total item count field should be same");
    Assert.assertEquals(pastShoppingListResponse.getPage(), pageNumber,"Page should be 1");
    Assert.assertEquals(pastShoppingListResponse.getPageSize(), pageSize,"Page size should be 10");
    Assert.assertNull(pastShoppingListResponse.getMessage(), "Message should be NULL");
 }
}
