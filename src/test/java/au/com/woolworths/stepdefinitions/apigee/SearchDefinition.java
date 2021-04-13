package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.SearchHelper;
import au.com.woolworths.model.apigee.search.SearchResponseV3;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class SearchDefinition extends SearchHelper {
  private final static Logger logger = Logger.getLogger("SearchDefinition.class");


  @When("^I search for the product (.*) in (.*) mode and should see more than 1 matching results$")
  public void userSearchProduct(String searchItem, String mode) throws Throwable {
    SearchResponseV3 v3SearchResponse = getProductItems(searchItem, mode);

    Assert.assertTrue(v3SearchResponse.getProduct_count() >= 1, "Product count attribute are not more than or equal to 1");
    Assert.assertTrue(v3SearchResponse.getProducts().length >= 1, "Product Search Results are not more than or equal to 1");
  }

  @When("^I search for the product (.*) in (.*) mode and should not see any matching results$")
  public void userSearchInvalidProduct(String invalidSearch, String mode) throws Throwable {
    SearchResponseV3 v3SearchResponse = getProductItems(invalidSearch, mode);
    Assert.assertTrue(v3SearchResponse.getProducts().length == 0, "Product Search Results is not no results for invalid search");
  }

  //TODO To be cleaned up
  @When("^I search for the product (.*) in (.*) mode and store response$")
  public void userSearchProductAndStore(String searchItem, String mode) throws Throwable {
    SearchResponseV3 v3SearchResponse = getProductItems(searchItem, mode);
    Assert.assertTrue(v3SearchResponse.getProducts().length >= 1, "Product Search Results are not more than or equal to 1");
    sharedData.searchProductResponse = v3SearchResponse;
  }


  @When("^I search to add \"([^\"]*)\" \"([^\"]*)\" products to the \"([^\"]*)\" list \"([^\"]*)\"$")
  public void userSearchProductToList(String prodQuantity, String searchItem, String version, String listName) throws Throwable {
    ListsDefinition listsDefinition = new ListsDefinition();
    userSearchProductAndStore(searchItem, sharedData.fulfilment);
    listsDefinition.iAddAvailableProductsWithEachFromTheStoreToList(Integer.parseInt(prodQuantity), 2, version, listName);
  }
}
