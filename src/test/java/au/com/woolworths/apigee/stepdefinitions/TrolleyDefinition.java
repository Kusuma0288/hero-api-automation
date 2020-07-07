package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.SearchHelper;
import au.com.woolworths.apigee.helpers.TrolleyHelper;
import au.com.woolworths.apigee.model.ApigeeV3SearchResponse;
import au.com.woolworths.apigee.model.TrolleyV2Response;
import au.com.woolworths.apigee.model.TrolleyV3Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.*;
import java.util.logging.Logger;

public class TrolleyDefinition extends TrolleyHelper {
  private final static Logger logger = Logger.getLogger("TrolleyDefinition.class");
  private SearchHelper searchHelper = new SearchHelper();
  private double expectedTotalPrice = 0;
  private List<String> productNames = new ArrayList();



  @When("^I add the (.*) available products with (.*) each from the store to the V3 trolley$")
  public void iAddTheAvailableProductsFromTheStoreToTheV3Trolley(int availableProducts, int quantity) throws Throwable {
    ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
    List<String> stockCodes = new ArrayList<String>();
    for (int i = 0; i < searchResponse.getProducts().length; i++) {
      if (searchResponse.getProducts()[i].getIs().isRanged()) {
        stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
      }
      if (stockCodes.size() == availableProducts) {
        break;
      }
    }
    Assert.assertTrue("There are no products available in Store", stockCodes.size() != 0);
    TrolleyV3Response trolleyResponse = addStockCodesToTheV3Trolley(stockCodes, quantity, true);
    Assert.assertTrue("Error in Order adding to the trolley", trolleyResponse.getResults().getOrder().getHttpStatusCode() == 200);
    Assert.assertTrue("Error in Trolley Results", trolleyResponse.getResults().getTrolley().getHttpStatusCode() == 200);

  }

  @When("^I add the (.*) available products with (.*) each from the store to the V2 trolley$")
  public void iAddTheAvailableProductsFromTheStoreToTheV2Trolley(int availableProducts, int quantity) throws Throwable {
    ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
    List<String> stockCodes = new ArrayList<String>();
    for (int i = 0; i < searchResponse.getProducts().length; i++) {
      if (searchResponse.getProducts()[i].getIs().isRanged()) {
        stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
      }
      if (stockCodes.size() == availableProducts) {
        break;
      }
    }
    Assert.assertTrue("There are no products available in Store", stockCodes.size() != 0);
    TrolleyV2Response trolleyResponse = addStockCodesToTheV2Trolley(stockCodes, quantity, true);
    Assert.assertTrue("Products is not added as expected:" + availableProducts, trolleyResponse.getTotalproducts() == availableProducts);

  }

  @When("^I add some items to the (.*) trolley for (.*)$")
  public void iAddSomeItemsToTheV3Trolley(String version, String mode) throws Throwable {
    Map<String, Integer> productsToAdd = new HashMap<>();

    productsToAdd.put("milk", 2);
    productsToAdd.put("bread", 1);
    productsToAdd.put("pasta", 2);


    Map<String, Object> outputMap = addItemsToTrolley(productsToAdd, mode, version);
    expectedTotalPrice = (double) outputMap.get("expectedTotalPrice");
    productNames = (List<String>) outputMap.get("productNames");
  }

  @And("^I clear the trolley$")
  public void iClearTheTrolley() throws Throwable {
    TrolleyV2Response trolleyResponse = clearTrolley();

    Assert.assertEquals("Some items are there in trolley", 0.0, trolleyResponse.getTotaltrolleyprice());
  }

  @And("^I add the stockcode with quantity \"([^\"]*)\" to trolley$")
  public void iAddStockcodeWithSpecifiedQuantity(int quantity) throws Throwable {
    TrolleyV2Response trolleyResponse = addStockCodesToTheV2Trolley(sharedData.stockCode, quantity, true);

    //Assert if product has been added
    Assert.assertTrue("Products is not added as expected and trolley product count:" + trolleyResponse.getTotalproducts()
            , trolleyResponse.getTotalproducts() > 0);
  }

  @Then("^I remove (.*) product from V3 trolley and verify it is deleted$")
  public void iRemoveProductFromV3TrolleyAndVerifyItIsDeleted(int itemsToBeDeleted) throws Throwable {

    TrolleyV3Response trolleyResponse = retriveV3Trolley(); //Get the latest items from trolley.
    int prodCountBefore = trolleyResponse.getTrolley().getTotalProducts(); //Check if the trolley is null
    Assert.assertTrue("Trolley is null. There are no products in Trolley for Deletion", prodCountBefore != 0);
    /*
     * Go through the list of items to be deleted. Ignores the number, if the provided count of items to be deleted is more than available items.
     *  for e.g.  if available items is 5 and user has provided 6 for deletion. It will ignore 6th item and will delete only 5 items.
     */
    for (int i = 0; i < itemsToBeDeleted && i < trolleyResponse.getTrolley().getTotalProducts(); i++) {
      trolleyResponse = retriveV3Trolley(); //Get the latest items from trolley.
      prodCountBefore = trolleyResponse.getTrolley().getTotalProducts();
      String stockCode = trolleyResponse.getTrolley().getItems().get(0).getArticle();
      trolleyResponse = delStockCodesFromV3Trolley(stockCode);
      int prodCountAfter = trolleyResponse.getTrolley().getTotalProducts();
      for (int j = 0; j < trolleyResponse.getTrolley().getTotalProducts(); j++) {
        //Verify the deleted item is not present in trolley
        Assert.assertTrue("StockCode " + stockCode + " not deleted from trolley. Error in deletion", !trolleyResponse.getTrolley().getItems().get(j).getArticle().equals(stockCode));

      }
      //Verify item count after  deletion
      Assert.assertTrue("Error in item deletion", prodCountAfter == (prodCountBefore - 1));

    }

  }

  @Then("^I remove (.*) product from V2 trolley and verify it is deleted$")
  public void iRemoveProductFromV2TrolleyAndVerifyItIsDeleted(int itemsToBeDeleted) throws Throwable {

    TrolleyV2Response trolleyResponse = retriveV2Trolley(); //Get the latest items from trolley.
    int prodCountBefore = trolleyResponse.getTotalproducts(); //Check if the trolley is null
    Assert.assertTrue("Trolley is null. There are no products in Trolley for Deletion", prodCountBefore != 0);
    /*
     *  Go through the list of items to be deleted. Ignores the number, if the provided count of items to be deleted is more than available items.
     *	for e.g.  if available items is 5 and user has provided 6 for deletion. It will ignore 6th item and will delete only 5 items.
     */
    for (int i = 0; i < itemsToBeDeleted && i < trolleyResponse.getTotalproducts(); i++) {
      trolleyResponse = retriveV2Trolley(); //Get the latest items from trolley.
      prodCountBefore = trolleyResponse.getTotalproducts();
      String stockCode = trolleyResponse.getItems().get(0).getArticle();
      trolleyResponse = delStockCodesFromV2Trolley(stockCode);
      int prodCountAfter = trolleyResponse.getTotalproducts();
      for (int j = 0; j < trolleyResponse.getTotalproducts(); j++) {
        //Verify the deleted item is not present in trolley
        Assert.assertTrue("StockCode " + stockCode + " not deleted from trolley. Error in deletion", !trolleyResponse.getItems().get(j).getArticle().equals(stockCode));

      }
      //Verify item count after  deletion
      Assert.assertTrue("Error in item deletion", prodCountAfter == (prodCountBefore - 1));

    }

  }

  @Then("^I should be able to successfully view all the items in my V2 trolley$")
  public void canViewAllItemsInV2Trolley() throws Throwable {
    TrolleyV2Response trolleyResponse = retriveV2Trolley();

    Assert.assertTrue("The number of products in the V2 trolley is incorrect", trolleyResponse.getTotalproducts() == productNames.size());
    Assert.assertTrue("Subtotal price is not correct", trolleyResponse.getTotaltrolleyprice() == (expectedTotalPrice));

    // Reverse the list first
    Collections.reverse(productNames);

    // Verify all the products are correct
    for (String productName : productNames) {
      String description = trolleyResponse.getItems().get(productNames.indexOf(productName)).getDescription();

      Assert.assertTrue("Product description is not correct (Expected - " + productName + ", but got - " + description, productName.equals(description));
    }
  }

  @Then("^I should be able to successfully view all the items in my V3 trolley$")
  public void canViewAllItemsInV3Trolley() throws Throwable {
    TrolleyV3Response trolleyResponse = retriveV3Trolley();

    Assert.assertTrue("Error in V3 Trolley Results", trolleyResponse.getResults().getTrolley().getHttpStatusCode() == 200);
    Assert.assertTrue("The number of products in the V3 trolley is incorrect", trolleyResponse.getTrolley().getTotalProducts() == productNames.size());
    Assert.assertTrue("Subtotal price is not correct", trolleyResponse.getTrolley().getSubtotalInclDelivery() == (expectedTotalPrice + trolleyResponse.getTrolley().getDeliveryFee()));

    // Reverse the list first
    Collections.reverse(productNames);

    // Verify all the products are correct
    for (String productName : productNames) {
      String description = trolleyResponse.getTrolley().getItems().get(productNames.indexOf(productName)).getDescription();

      Assert.assertTrue("Product description is not correct (Expected - " + productName + ", but got - " + description, productName.equals(description));
    }
  }

  @When("^I store (.*) products with quantity (.*) to the V(.*) trolley\\.$")
  public void iStoreProductsWithQuantityToTheVTrolley(int productQty, int quantity, String version) throws Throwable {

    if (version.equals("V3")) {
      ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
      List<String> stockCodes = new ArrayList<String>();
      for (int i = 0; i < searchResponse.getProducts().length; i++) {

        if (searchResponse.getProducts()[i].getIs().isRanged()) {
          stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
        }
        if (stockCodes.size() == productQty) {
          break;
        }
      }
      Assert.assertTrue("There are no products available in Store", stockCodes.size() != 0);
      TrolleyV3Response trolleyResponse = addStockCodesToTheV3Trolley(stockCodes, quantity, true);
      Assert.assertTrue("Error in Order adding to the trolley", trolleyResponse.getResults().getOrder().getHttpStatusCode() == 200);
      Assert.assertTrue("Error in Trolley Results", trolleyResponse.getResults().getTrolley().getHttpStatusCode() == 200);
    } else {
      ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
      List<String> stockCodes = new ArrayList<String>();
      for (int i = 0; i < searchResponse.getProducts().length; i++) {
        if (searchResponse.getProducts()[i].getIs().isRanged()) {
          stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));
        }
        if (stockCodes.size() == productQty) {
          break;
        }
      }
      Assert.assertTrue("There are no products available in Store", stockCodes.size() != 0);
      TrolleyV2Response trolleyResponse = addStockCodesToTheV2Trolley(stockCodes, quantity, true);
      Assert.assertTrue("Products is not added as expected:" + productQty, trolleyResponse.getTotalproducts() == productQty);
    }
  }

  @Then("^I am able to successfully view (.*) items in my V(.*) trolley$")
  public void iAmAbleToSuccessfullyViewItemsInMyVTrolley(int productQty, String version) throws Throwable {

    if (version.equals("V3")) {
      TrolleyV3Response trolleyResponse = retriveV3Trolley();
      Assert.assertTrue("Products is not added as expected:" + productQty, trolleyResponse.getTrolley().getTotalProducts() == productQty);

    } else {
      TrolleyV2Response trolleyResponse = retriveV2Trolley();
      Assert.assertTrue("Products is not added as expected:" + productQty, trolleyResponse.getTotalproducts() == productQty);
    }
  }

  @Then("^I update the quantity for every item in V(.*) cart to new value (.*)$")
  public void iUpdateTheQuantityForEveryItemInVCartToNewValue(String version, int quantity) throws Throwable {

    List<String> stockCodes = new ArrayList<String>();
    if (version.equals("V3")) {
      TrolleyV3Response trolleyResponse = retriveV3Trolley();
      Assert.assertTrue("There are no products in Trolley:", trolleyResponse.getTrolley().getTotalProducts() > 0);

      for (int i = 0; i < trolleyResponse.getTrolley().getTotalProducts(); i++) {
        stockCodes.add(trolleyResponse.getTrolley().getItems().get(0).getArticle());
      }
      //Update the items in trolley with new quantity.
      trolleyResponse = addStockCodesToTheV3Trolley(stockCodes, quantity, true);
    } else {

      TrolleyV2Response trolleyResponse = retriveV2Trolley();
      Assert.assertTrue("There are no products in Trolley:", trolleyResponse.getTotalproducts() > 0);

      for (int i = 0; i < trolleyResponse.getTotalproducts(); i++) {
        stockCodes.add(trolleyResponse.getItems().get(i).getArticle());
      }
      //Update the items in trolley with new quantity.
      trolleyResponse = addStockCodesToTheV2Trolley(stockCodes, quantity, true);
    }
  }

  @Then("^I verify that every item in V(.*) cart is updated with correct quantity (.*)$")
  public void iVerifyThatEveryItemInVCartIsUpdatedWithCorrectQuantity(String version, int quantity) throws Throwable {

    if (version.equals("V3")) {
      TrolleyV3Response trolleyResponse = retriveV3Trolley();
      Assert.assertTrue("There are no products in Trolley:", trolleyResponse.getTrolley().getTotalProducts() > 0);

      for (int i = 0; i < trolleyResponse.getTrolley().getTotalProducts(); i++) {
        Assert.assertTrue("Items not updated with correct quantity:", trolleyResponse.getTrolley().getItems().get(i).getItemquantityintrolley() == quantity);
      }

    } else {

      TrolleyV2Response trolleyResponse = retriveV2Trolley();
      Assert.assertTrue("There are no products in Trolley:", trolleyResponse.getTotalproducts() > 0);

      for (int i = 0; i < trolleyResponse.getTotalproducts(); i++) {
        Assert.assertTrue("Items not updated with correct quantity:", trolleyResponse.getItems().get(i).getItemquantityintrolley() == quantity);
      }
    }
  }
}
