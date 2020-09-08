package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.SearchHelper;
import au.com.woolworths.model.trader.GetProductItems;
import au.com.woolworths.model.trader.PickupResponse;
import au.com.woolworths.model.trader.SuburbDetails;
import au.com.woolworths.model.trader.Suburbs;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class SearchDefinition extends SearchHelper {

  private static final Logger logger = Logger.getLogger("SearchDefinition.class");


  @Given("^I search for the product \"([^\"]*)\" and should see more than (\\d+) matching results$")
  public void iSearchForTheProductAndShouldSeeMoreThanMatchingResults(String productName, int minCount) throws Throwable {
    GetProductItems productItems = getProductItems(productName);
    sharedData.searchProductItemsResult = productItems;
    Assert.assertTrue(productItems.getItems().length >= minCount, "Product Search Results should be more than or equal to::" + minCount);
  }

  @When("^verify the pickup stores in the \"([^\"]*)\" suburb$")
  public void verifyThePickupStoresInTheSuburb(String suburbName) throws Throwable {
    Suburbs suburbList = getServicableSuburbs(suburbName);
    for (SuburbDetails suburbDetail : suburbList.getSuburbs()) {
      Assert.assertTrue(suburbDetail.getSuburbName().toLowerCase().contains(suburbName.toLowerCase()), "Suburb name::" + suburbName + " is not present");
      Assert.assertTrue(suburbDetail.getSuburbStateString().toLowerCase().contains(suburbName.toLowerCase()), "Suburb state name::" + suburbName + " is not present");
    }
  }

  @When("^verify the pickup stores in the postcode (\\d+)$")
  public void verifyThePickupStoresInThePostcode(int postCode) throws Throwable {
    PickupResponse[] pickupResponses = getServicablePickupStoresFromPostCode(postCode);
    for (PickupResponse response : pickupResponses) {
      Assert.assertNotNull(response.getDeliveryMethod(), "Delivery Method cannot be NULL");
      Assert.assertNotNull(response.getAddressId(), "Address ID cannot be NULL");
      Assert.assertNotNull(response.getAddressText(), "Address Text cannot be NULL");
      Assert.assertNotNull(response.getDescription(), "Description cannot be NULL");
      Assert.assertNotNull(response.getSuburb(), "Suburb cannot be NULL");
      Assert.assertNotNull(response.getTradingHours(), "Trading Hours cannot be NULL");
      Assert.assertNotNull(response.getLatitude(), "Latitude cannot be NULL");
      Assert.assertNotNull(response.getLongitude(), "Longitude cannot be NULL");
    }
  }

  @Then("^I search for the pickup stores in the postcode (.*)$")
  public void iSearchPickupStoreInPostCode(int postCode) throws Throwable {
    PickupResponse[] pickupResponses = getServicablePickupStoresFromPostCode(postCode);
    sharedData.pickupResponse = pickupResponses;
  }

}
