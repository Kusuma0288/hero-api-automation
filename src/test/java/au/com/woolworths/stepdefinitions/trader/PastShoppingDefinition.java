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
  private String noOfPage, pageSize;


  @When("^customer calls past shopping list api with number of pages as (.*) and pagesize as (.*)$")
  public void customerCallsShoppingAislesApi(String noOfPage, String pageSize) throws Throwable {
    this.noOfPage=noOfPage;
    this.pageSize=pageSize;
    pastShoppingListResponse = iGetShoppingAisles( noOfPage,  pageSize);
    System.out.println(noOfPage+ "  "+ pageSize);

  }

  @Then("^verify past shopping api responds with right response$")
  public void verifyShoppingAislesApiRespondsShoppingAislesAndItsCategories() {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(pastShoppingListResponse.getItems().size() > 0, "List of past shops is empty");
    Assert.assertEquals(Integer.parseInt(pastShoppingListResponse.getTotalItemCount()) , pastShoppingListResponse.getItems().size() , "count of returned list and total item count field should be same");
    Assert.assertEquals(pastShoppingListResponse.getPage(), noOfPage,"Page should be 1");
    Assert.assertEquals(pastShoppingListResponse.getPageSize(), pageSize,"Page size should be 10");
    Assert.assertNull(pastShoppingListResponse.getMessage(), "Message should be NULL");
 }
}
