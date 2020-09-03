package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ProductsHelper;
import au.com.woolworths.model.trader.ProductDetailsResponse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class ProductDetailsDefinition extends ProductsHelper {
  private final static Logger logger = Logger.getLogger("WeeklyPicksProductsDefinition.class");
  private ProductDetailsResponse productDetailsResponse;


  @When("^customer calls product details api for (.*) stockcode$")
  public void customerCallsTraderProductDetails(String stockcode) throws Throwable {
    sharedData.productDetailsResponse = iGetProductDetails(stockcode);
  }

  @Then("^verify product details api responds with all the details of product for this stockcode$")
  public void verifytraderProductDetailsApiRespondsWithDetailsOfProduct() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    productDetailsResponse = sharedData.productDetailsResponse;
    Assert.assertNotNull(productDetailsResponse.getProduct().getDescription(), "Product Description cannot be NULL");
    Assert.assertNotNull(productDetailsResponse.getProduct().getName(), "Product name cannot be NULL");
    Assert.assertTrue(productDetailsResponse.getProduct().getNutritionalInformation().size() > 0, "Nutritional Information has list of this product details");
  }

  @Then("^verify product details api responds with invalid details of product for this stockcode$")
  public void verifytraderProductDetailsApiRespondsWithInvalidDetailsOfProduct() throws Throwable {
    productDetailsResponse = sharedData.productDetailsResponse;
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertNull(productDetailsResponse.getProduct(), "Product Description Should be NULL");
    Assert.assertNotNull(productDetailsResponse.getErrors().length > 0, "Error should be present");
    Assert.assertTrue(productDetailsResponse.getErrors()[0].getMessage().contains("Oops! No product with that barcode was found"), "Error message is not as expected ");
    Assert.assertNotNull(productDetailsResponse.getErrors()[0].getStockCode(), "Stock Code is empty in error list");
  }
}

