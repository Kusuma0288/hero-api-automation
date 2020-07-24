package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.ProductsHelper;
import au.com.woolworths.model.apigee.products.ProductCategoriesSpecial;
import au.com.woolworths.model.apigee.products.ProductsSpecial;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ProductsDefinition extends ProductsHelper {

  private final static Logger logger = Logger.getLogger("ProductsDefinition.class");


  @When("^I make a request to V3 categories with type as Specials$")
  public void retrieveProductCategoriesWithSpecial() throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", sharedData.inStoreId);
    queryParams.put("type", "specials");
    ProductCategoriesSpecial productCategoriesSpecial = iRetreiveProductCategories(queryParams);

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull("There are no Aisles", productCategoriesSpecial.getAisles()[0]);
    Assert.assertNotNull("There are no categories", productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle());

    sharedData.productAisle = productCategoriesSpecial.getAisles()[0].getTitle();
    sharedData.productCategory = productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle();

  }

  @When("^I make a request to V2 products with specials and verify the response$")
  public void retrieveProductsWithSpecial() throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", sharedData.inStoreId);
    queryParams.put("aisle", sharedData.productAisle);
    queryParams.put("category", sharedData.productCategory);
    queryParams.put("type", "specials");
    ProductsSpecial productsSpecial = iRetreiveProducts(queryParams);

    //Asserting sort, tobacco, disclaimer attributes
    Assert.assertNotNull(productsSpecial.getProducts()[0]);

    Assert.assertEquals("Sort option Relevance did not match", productsSpecial.getSortoptions()[0].getDescription(), "Relevance");
    Assert.assertFalse("Tobacco flag is true", productsSpecial.getProducts()[0].getIs().isTobacco());
    Assert.assertEquals("Product does not have tobacco disclaimer", productsSpecial.getProducts()[0].getDisclaimer().getTobacco(), " ");
    Assert.assertEquals("Product does not have liquor disclaimer", productsSpecial.getProducts()[0].getDisclaimer().getLiquor(), " ");
  }

  @When("^I make a request to V3 categories without type as Specials$")
  public void retrieveProductCategories() throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", sharedData.inStoreId);
    ProductCategoriesSpecial productCategoriesSpecial = iRetreiveProductCategories(queryParams);

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull("There are no Aisles", productCategoriesSpecial.getAisles()[0]);
    Assert.assertNotNull("There are no categories", productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle());

    sharedData.productAisle = productCategoriesSpecial.getAisles()[0].getTitle();
    sharedData.productCategory = productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle();

  }

  @When("^I make a request to V2 products without specials and verify the response$")
  public void retrieveProducts() throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", sharedData.inStoreId);
    queryParams.put("aisle", sharedData.productAisle);
    queryParams.put("category", sharedData.productCategory);
    ProductsSpecial productsSpecial = iRetreiveProducts(queryParams);

    //Asserting sort, tobacco, disclaimer attributes
    Assert.assertNotNull(productsSpecial.getProducts()[0]);
    Assert.assertEquals("Sort option Relevance did not match", productsSpecial.getSortoptions()[0].getDescription(), "Relevance");
    Assert.assertFalse("Tobacco flag is true", productsSpecial.getProducts()[0].getIs().isTobacco());
    Assert.assertEquals("Product does not have tobacco disclaimer", productsSpecial.getProducts()[0].getDisclaimer().getTobacco(), " ");
    Assert.assertEquals("Product does not have liquor disclaimer", productsSpecial.getProducts()[0].getDisclaimer().getLiquor(), " ");
  }

  @When("^I make a request to Products API to filter the products based on \"([^\"]*)\" Specials group for store \"([^\"]*)\"$")
  public void retrieveProductsWithSpecialInStoreMode(int position, String store) throws Throwable {

    String specialsGroup = sharedData.specialspageResponse.getCategories()[position].getProducts_href().replaceAll("(.*)filter=", "");
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("filter", specialsGroup);
    queryParams.put("type", "specials");
    queryParams.put("store", store);
    ProductsSpecial productsSpecial = iRetreiveProducts(queryParams);

    //Asserting that at least 1 product has been returned
    Assert.assertNotNull("No products returned", productsSpecial.getProducts());

    //Asserting that name and article of the product is not null
    Assert.assertNotNull("Name of the product is not null", productsSpecial.getProducts()[0].getName());
    Assert.assertNotNull("Article id of the product is not null", productsSpecial.getProducts()[0].getArticle());

  }

  @When("^I make a request to Products API to filter the products based on \"([^\"]*)\" Specials group in \"([^\"]*)\" mode$")
  public void retrieveProductsWithSpecialOnlinePickupMode(int position, String mode) throws Throwable {

    String specialsGroup = sharedData.specialspageResponse.getCategories()[position].getProducts_href().replaceAll("(.*)filter=", "");
    Map<String, String> queryParams = new HashMap<String, String>();

    queryParams.put("filter", specialsGroup);
    queryParams.put("type", "specials");
    queryParams.put("mode", mode);
    ProductsSpecial productsSpecial = iRetreiveProducts(queryParams);

    //Asserting that at least 1 product has been returned
    Assert.assertNotNull("No products returned", productsSpecial.getProducts());

    //Asserting that name and article of the product is not null
    Assert.assertNotNull("Name of the product is not null", productsSpecial.getProducts()[0].getName());
    Assert.assertNotNull("Article id of the product is not null", productsSpecial.getProducts()[0].getArticle());

    sharedData.stockCode.add(productsSpecial.getProducts()[0].getArticle());

  }

}
