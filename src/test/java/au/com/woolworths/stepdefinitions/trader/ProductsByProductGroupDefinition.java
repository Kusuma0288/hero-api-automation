package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ProductsHelper;
import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.GroupProducts;
import au.com.woolworths.model.trader.TrolleyResponse;
import au.com.woolworths.model.trader.product.productGroup.ProductsByProductGroup;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class ProductsByProductGroupDefinition extends ProductsHelper {

  private static final Logger logger = Logger.getLogger("ProductsByProductGroupDefinition.class");
  private final ShopperHelper shopperHelper;

  public ProductsByProductGroupDefinition() {
    this.shopperHelper = new ShopperHelper();
  }

  @When("^shopper calls products by product group api with \"([^\"]*)\"$")
  public void shopper_calls_products_by_product_group_api_with(String productGroupId) throws Throwable {
    sharedData.productsByProductGroup = iGetProductsByProductsGroup(productGroupId);
  }

  @Then("^verify products by product group api responds with all products for this productGroupId$")
  public void verify_products_by_product_group_api_responds_with_all_products_for_this_productGroupId() {

    Assert.assertTrue(sharedData.recentCompleteResponse.get("statusCode").equals("200"),
        "Expected Status Code is 200 but found::" + sharedData.recentCompleteResponse.get("statusCode"));

    ProductsByProductGroup response = sharedData.productsByProductGroup;

    Assert.assertTrue(response.getTotalProductCount() > 0, "No products returned for product group");

    GroupProducts firstAvailableProduct = null;

    try {
      firstAvailableProduct = response.getProducts().stream().filter(p -> p.isIsAvailable() == true).findFirst().get();
    } catch (NoSuchElementException e) {
      logger.info("None of the products in the product group are available");
    }

    Assert.assertNotNull(firstAvailableProduct.getName(), "Name of available product is null");
    Assert.assertNotNull(firstAvailableProduct.getUrlFriendlyName(), "Name for URL of available product is null");
    Assert.assertNotNull(firstAvailableProduct.getDescription(), "Description of available product is null");
    Assert.assertNotNull(firstAvailableProduct.getUnit(), "Unit of available product is null");
    Assert.assertTrue(firstAvailableProduct.getPrice() > 0, "Price of available product is less than or equal to 0");
  }

  @Then("^shopper adds any (.*) products with (.*) to trolley that are available in product group$")
  public void shopper_adds_some_products_to_trolley_that_are_available_in_product_group(int productCount, int quantityInTrolley) throws Throwable {
    List<String> stockCodes = new ArrayList<>();

    sharedData.productsByProductGroup.getProducts().stream()
        .filter(p -> p.isIsAvailable() == true).limit(productCount).forEach(s -> stockCodes.add(s.getStockcode()));

    sharedData.stockCodes = stockCodes;

    TrolleyResponse trolleyResponse = shopperHelper.iAddFollowingProductsToTrolley(
        quantityInTrolley, stockCodes,  false);

    sharedData.trolleyResponse = trolleyResponse;
  }

  @Then("^trolley (.*) and other information is updated for the products added to the trolley$")
  public void trolley_information_is_updated_for_the_products_added_to_the_trolley(int quantityInTrolley) {
    ProductsByProductGroup response = sharedData.productsByProductGroup;
    TrolleyResponse trolleyResponse = sharedData.trolleyResponse;

    for (String stock : sharedData.stockCodes) {
      Assert.assertTrue(response.getProducts()
          .stream().filter(product -> product.getStockcode().equalsIgnoreCase(stock))
          .findFirst().get().getQuantityInTrolley() == quantityInTrolley);

      Assert.assertTrue(response.getProducts()
          .stream().filter(product -> product.getStockcode().equalsIgnoreCase(stock))
          .findFirst().get().isIsInTrolley() == true);

      Assert.assertTrue(trolleyResponse.getProducts()
          .stream().filter(trolleyProduct -> trolleyProduct.getStockcode()
              .equalsIgnoreCase(stock.replaceFirst("^0+(?!$)", "")))
          .findFirst().get().getQuantity() == quantityInTrolley);

      Assert.assertTrue(trolleyResponse.getProducts()
          .stream().filter(trolleyProduct -> trolleyProduct.getStockcode()
              .equalsIgnoreCase(stock.replaceFirst("^0+(?!$)", "")))
          .findFirst().get().isIsAvailable() == true);
    }
  }
}


