package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.SearchHelper;
import au.com.woolworths.apigee.model.ApigeeV3SearchResponse;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class ApigeeSearchDefinition extends SearchHelper {
  private final static Logger logger = Logger.getLogger("ApigeeSearchDefinition.class");
  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public ApigeeSearchDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^I search for the product (.*) in (.*) mode and should see more than 1 matching results$")
  public void userSearchProduct(String searchItem, String mode) throws Throwable {
    ApigeeV3SearchResponse v3SearchResponse = getProductItems(searchItem, mode, sharedData.accessToken);

    Assert.assertTrue(v3SearchResponse.getProduct_count() >= 1, "Product count attribute are not more than or equal to 1");
    Assert.assertTrue(v3SearchResponse.getProducts().length >= 1, "Product Search Results are not more than or equal to 1");
  }

  @When("^I search for the product (.*) in (.*) mode and should not see any matching results$")
  public void userSearchInvalidProduct(String invalidSearch, String mode) throws Throwable {
    ApigeeV3SearchResponse v3SearchResponse = getProductItems(invalidSearch, mode, sharedData.accessToken);
    Assert.assertTrue(v3SearchResponse.getProducts().length == 0, "Product Search Results is not no results for invalid search");
  }

  //TODO To be cleaned up
  @When("^I search for the product (.*) in (.*) mode and store response$")
  public void userSearchProductAndStore(String searchItem, String mode) throws Throwable {
    ApigeeV3SearchResponse v3SearchResponse = getProductItems(searchItem, mode, sharedData.accessToken);
    Assert.assertTrue(v3SearchResponse.getProducts().length >= 1, "Product Search Results are not more than or equal to 1");
    sharedData.searchProductResponse = v3SearchResponse;
  }


  @When("^I search to add \"([^\"]*)\" \"([^\"]*)\" products to the \"([^\"]*)\" list \"([^\"]*)\"$")
  public void userSearchProductToList(String prodQuantity,String searchItem, String version, String listName) throws Throwable {
    ApigeeListsDefinition apigeeListsDefinition = new ApigeeListsDefinition(picoContainer);
    userSearchProductAndStore(searchItem,picoContainer.fulfilment);
    apigeeListsDefinition.iAddAvailableProductsWithEachFromTheStoreToList(Integer.parseInt(prodQuantity),2,version,listName);
  }
}
