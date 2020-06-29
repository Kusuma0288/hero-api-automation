package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.ApigeeProductsHelper;
import au.com.woolworths.apigee.model.Products.ApigeeProductCategoriesSpecial;
import au.com.woolworths.apigee.model.Products.ApigeeProductsSpecial;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.logging.Logger;

public class ApigeeProductsDefinition extends ApigeeProductsHelper {

  private final static Logger logger = Logger.getLogger("ApigeeProductsDefinition.class");

  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public ApigeeProductsDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^I make a request to V3 categories with type as Specials$")
  public void retrieveProductCategoriesWithSpecial() throws Throwable {

    ApigeeProductCategoriesSpecial productCategoriesSpecial = iRetreiveProductCategoriesWithSpecial(sharedData.inStoreId, sharedData.accessToken);

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull("There are no Aisles", productCategoriesSpecial.getAisles()[0]);
    Assert.assertNotNull("There are no categories", productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle());

    sharedData.productAisle = productCategoriesSpecial.getAisles()[0].getTitle();
    sharedData.productCategory = productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle();

  }

  @When("^I make a request to V2 products with specials and verify the response$")
  public void retrieveProductsWithSpecial() throws Throwable {

    ApigeeProductsSpecial productsSpecial = iRetreiveProductsWithSpecial(sharedData.inStoreId, sharedData.productAisle,
        sharedData.productCategory, sharedData.accessToken);

    //Asserting sort, tobacco, disclaimer attributes
    Assert.assertNotNull(productsSpecial.getProducts()[0]);

    Assert.assertEquals("Sort option Relevance did not match", productsSpecial.getSortoptions()[0].getDescription(), "Relevance");
    Assert.assertFalse("Tobacco flag is true", productsSpecial.getProducts()[0].getIs().isTobacco());
    Assert.assertEquals("Product does not have tobacco disclaimer", productsSpecial.getProducts()[0].getDisclaimer().getTobacco(), " ");
    Assert.assertEquals("Product does not have liquor disclaimer", productsSpecial.getProducts()[0].getDisclaimer().getLiquor(), " ");
  }

  @When("^I make a request to V3 categories without type as Specials$")
  public void retrieveProductCategories() throws Throwable {

    ApigeeProductCategoriesSpecial productCategoriesSpecial = iRetreiveProductCategories(sharedData.inStoreId, sharedData.accessToken);

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull("There are no Aisles", productCategoriesSpecial.getAisles()[0]);
    Assert.assertNotNull("There are no categories", productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle());

    sharedData.productAisle = productCategoriesSpecial.getAisles()[0].getTitle();
    sharedData.productCategory = productCategoriesSpecial.getAisles()[0].getCategories()[0].getTitle();

  }

  @When("^I make a request to V2 products without specials and verify the response$")
  public void retrieveProducts() throws Throwable {

    ApigeeProductsSpecial productsSpecial = iRetreiveProducts(sharedData.inStoreId, sharedData.productAisle,
        sharedData.productCategory, sharedData.accessToken);

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

    ApigeeProductsSpecial productsSpecial = iRetreiveSpecialsProductsInStore(specialsGroup, store, sharedData.accessToken);

    //Asserting that at least 1 product has been returned
    Assert.assertNotNull("No products returned", productsSpecial.getProducts());

    //Asserting that name and article of the product is not null
    Assert.assertNotNull("Name of the product is not null", productsSpecial.getProducts()[0].getName());
    Assert.assertNotNull("Article id of the product is not null", productsSpecial.getProducts()[0].getArticle());

  }

  @When("^I make a request to Products API to filter the products based on \"([^\"]*)\" Specials group in \"([^\"]*)\" mode$")
  public void retrieveProductsWithSpecialOnlinePickupMode(int position, String mode) throws Throwable {

    String specialsGroup = sharedData.specialspageResponse.getCategories()[position].getProducts_href().replaceAll("(.*)filter=", "");

    ApigeeProductsSpecial productsSpecial = iRetreiveSpecialsProductsOnlinePickup(specialsGroup, mode, sharedData.accessToken);

    //Asserting that at least 1 product has been returned
    Assert.assertNotNull("No products returned", productsSpecial.getProducts());

    //Asserting that name and article of the product is not null
    Assert.assertNotNull("Name of the product is not null", productsSpecial.getProducts()[0].getName());
    Assert.assertNotNull("Article id of the product is not null", productsSpecial.getProducts()[0].getArticle());

    sharedData.stockCode.add(productsSpecial.getProducts()[0].getArticle());

  }

}
