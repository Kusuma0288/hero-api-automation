package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ProductsHelper;
import au.com.woolworths.model.trader.WeeklyPicksResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class WeeklyPicksProductsDefinition extends ProductsHelper {
  private final static Logger logger = Logger.getLogger("WeeklyPicksProductsDefinition.class");
  private WeeklyPicksResponse weeklyPicksResponse;


  @When("^customer calls trader weekly picks api$")
  public void customerCallsTraderWeeklyPicksApi() throws Throwable {
    weeklyPicksResponse = iGetProductsWeeklyPicks();
  }

  @Then("^verify trader weekly picks api responds successfully with list of products$")
  public void verifytraderWeeklyPicksApiRespondsWithListOfProducts() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(weeklyPicksResponse.getSuccess(), "Expected Successful response but");
    Assert.assertTrue(weeklyPicksResponse.getBundles().size() >= 0, "Bundles has List of Products");
  }
}
