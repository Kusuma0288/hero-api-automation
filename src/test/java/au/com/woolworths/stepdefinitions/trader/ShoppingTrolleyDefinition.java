package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.CartItem;
import au.com.woolworths.model.trader.GetProductItems;
import au.com.woolworths.model.trader.Products;
import au.com.woolworths.model.trader.TrolleyResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ShoppingTrolleyDefinition extends ShopperHelper {

  private final static Logger logger = Logger.getLogger("ShoppingTrolleyDefinition.class");

  @Given("^I clear the trolley for the shopper$")
  public void iClearTheTrolleyForTheShopper() throws Throwable {
    TrolleyResponse trolleyResponse = clearTheTrolleyForTheShopper();
    Assert.assertTrue(trolleyResponse.getTotalProducts() == 0, "Trolley is not cleared");
    trolleyResponse = iRetrieveTheShopperTrolley();
  }

  @Given("^I update (\\d+) product quantity to the trolley with stock code \"([^\"]*)\"$")
  public void iUpdateProductQuantityToTheTrolleyWithStockCode(int quantity, String stockCode) throws Throwable {

    TrolleyResponse trolleyResponse = iAddOrUpdateTheFollowingProductsToTheTrolley(quantity, stockCode, true);
    List<Products> products = trolleyResponse.getProducts();
    Products result = products.stream().filter(x -> stockCode.equals(x.getStockcode())).findAny().orElse(null);
    Assert.assertNotNull(result, "The Product Searched could not be found in the cart::" + stockCode);
    Assert.assertTrue(result.getQuantity() == quantity, "Quantity is not saved for the Product Stockcode:: " + stockCode);
    //Adding to the sharedData for any verification
    sharedData.trolleyQuantity = quantity;
  }

  @Given("^I replace with the following products to the trolley$")
  public void iReplaceWithTheFollowingProductsToTheTrolley(List<CartItem> cartItems) throws Throwable {
    iAddOrReplaceItemsInTheTrolley(cartItems, true);
  }

  @Given("^I add the following products to the trolley$")
  public void iAddTheFollowingProductsToTheTrolley(List<CartItem> cartItems) throws Throwable {
    iAddOrReplaceItemsInTheTrolley(cartItems, false);
  }

  private void iAddOrReplaceItemsInTheTrolley(List<CartItem> cartItems, boolean isUpdate) throws Throwable {
    for (CartItem cartItem : cartItems) {

      TrolleyResponse trolleyResponse = iAddOrUpdateTheFollowingProductsToTheTrolley(cartItem.getQuantity(), cartItem.getStockCode(), isUpdate);

      if (cartItem.getStatus().equals("PASS")) {
        Assert.assertTrue(trolleyResponse.getErrors().size() == 0, "Errors while adding items to the trolley");
        List<Products> products = trolleyResponse.getProducts();
        Products result = products.stream().filter(x -> cartItem.getStockCode().equals(x.getStockcode())).findAny().orElse(null);
        Assert.assertNotNull(result, "The product was not found for this stockcode::" + cartItem.getStockCode());
        Assert.assertTrue(result.getQuantity() == cartItem.getQuantity(), "Quantity is not saved for the Product Stockcode:: " + cartItem.getStockCode());
        Assert.assertTrue(result.getName().contains(cartItem.getName()), "Shopping Cart Item name is not matching:: " + cartItem.getName());
        //Adding to the sharedData for any verification
        sharedData.trolleyQuantity = sharedData.trolleyQuantity + cartItem.getQuantity();
      } else {
        Assert.assertTrue(trolleyResponse.getErrors().size() != 0, "Errors while adding items to the trolley");
        Assert.assertTrue(trolleyResponse.getErrors().get(0).getMessage().equals("A product was not found for this stockcode."), "Not a valid error message::" + trolleyResponse.getErrors().get(0).getMessage());
        //TO-DO issue with clearing the cache
        // Assert.assertTrue(trolleyResponse2.getTotalProducts()==0);
      }

    }
  }

  @Then("^shopper trolley should have (\\d+) products$")
  public void shopperTrolleyShouldHaveProducts(int quantity) throws Throwable {
    Assert.assertTrue(sharedData.trolleyQuantity == quantity, "There is mismatch in the number of expected quantities::" + quantity + " added in cart");
  }

  @Given("^I add few restricted items to my trolley from search list$")
  public void iAddFewRestrictedItemsToMyTrolleyFromSearchList() throws Throwable {
    GetProductItems searchProductItemsResult = sharedData.searchProductItemsResult;
    Assert.assertTrue(searchProductItemsResult.getTotalItemCount() >= 1, "There are no product items available. Please make sure product is searched correctly");
    List<String> availableStockCodes = getAvailableRestrictedStockCodesFromSearchProductItemsResult(searchProductItemsResult, 3);

    //logger.info("The Stock codes are::"+availableStockCodes);
    for (String stockCode : availableStockCodes) {
      stockCode = stockCode.replaceFirst("^0+(?!$)", "");
      TrolleyResponse trolleyResponse = iAddOrUpdateTheFollowingProductsToTheTrolley(1, stockCode, true);
      Assert.assertTrue(trolleyResponse.getResponseStatus().equals("200"), "Stock Code:: " + stockCode + " couldn't be added to the trolley");
    }

  }

  //TO-DO There is an issue currently with retrieving as the Items seems to be cached
  @When("^I retrieve the V2 endpoint of the trolley$")
  public void iRetrieveTheV2EndpointOfTheTrolley() throws Throwable {
    TrolleyResponse trolleyResponse = iRetrieveTheShopperTrolley();
    Assert.assertNotNull(trolleyResponse.getTotal(), "This is not null");
    Assert.assertTrue(trolleyResponse.getTotal() >= 0, "Total is less than 0");
    Assert.assertTrue(trolleyResponse.getSavings() >= 0, "Savings is less than 0");
    Assert.assertTrue(trolleyResponse.getTotalProducts() >= 0, "Total Products is less than 0");
    Assert.assertTrue(trolleyResponse.getDeliveryFee() >= 0, "Delivery Fee is greater than 0");
  }

  @When("^I retrieve the trolley for the shopper to check for (.*) products$")
  public void iRetrieveTheTrolleyForTheShopper(int count) throws Throwable {
    TrolleyResponse trolleyResponse = iRetrieveTheShopperTrolley();
    sharedData.trolleyResponse = trolleyResponse;
    Assert.assertTrue(trolleyResponse.getTotalProducts() == count, "The Count is not matching as we have " + trolleyResponse.getTotalProducts() + " in trolley");
  }

  @When("^I verify all (\\d+) items are available in the Trolley$")
  public void iVerifyAllItemsAreAvailableInTheTrolley(int noOfItems) throws Throwable {
    TrolleyResponse trolleyResponse = sharedData.trolleyResponse;
    Assert.assertTrue(trolleyResponse.getTotalProducts() == noOfItems, "Expected Total Count in Trolley " + noOfItems + " is missing");
    Assert.assertTrue(trolleyResponse.getProducts().size() == noOfItems, "Expected Product Count in Trolley " + noOfItems + " is missing");

    int availableCount = (int) trolleyResponse.getProducts().stream().filter(x -> x.isIsRanged() == true).count();
    int notAvailableCount = (int) trolleyResponse.getProducts().stream().filter(x -> x.isIsRanged() == false).count();
  }

  @Given("^I delete (.*) product from trolley and verify it is deleted$")
  public void iDeleteProductAndVerifyTrolley(int itemsToBeDeleted) throws Throwable {
    TrolleyResponse trolleyResponse = iRetrieveTheShopperTrolley();
    Assert.assertTrue(trolleyResponse.getTotal() != 0, "Trolley is not null");
    String stockCode;
    for (int i = 0; i < itemsToBeDeleted; i++) {
      //Delete from trolley
      stockCode = trolleyResponse.getProducts().get(i).getStockcode();
      TrolleyResponse trolleyResponseForDeletedProducts = iDeleteProductFromTrolley(stockCode);
      Assert.assertTrue(trolleyResponseForDeletedProducts.getResponseStatus().equals("200"), "Stock Code:: " + stockCode + " couldn't be deleted from the trolley");
      trolleyResponse = iRetrieveTheShopperTrolley();
      for (int j = 0; j < trolleyResponse.getProducts().size(); j++) {
        //Verify the deleted item is not present in trolley
        Assert.assertTrue(!trolleyResponse.getProducts().get(j).getStockcode().equals(stockCode), "StockCode not deleted from trolley b ut should be deleted");
        //Verify the remaining products are not deleted from trolley
        Assert.assertTrue(sharedData.stockCodes.contains(trolleyResponse.getProducts().get(j).getStockcode()), "Un deleted product removed from trolley");
      }
    }
    //Making the list to null so it can be reused by following scripts
    sharedData.stockCodes = null;
    trolleyResponse = iRetrieveTheShopperTrolley();
    Assert.assertTrue(trolleyResponse.getTotalProducts() != 0, "Trolley is not null");
    sharedData.trolleyQuantity = trolleyResponse.getTotalProducts();
  }

  @Given("^I add (.*) of the searched item to my trolley with note (.*)$")
  public void iAddTheItemToMyTrolleyFromSearchList(int quantity, String note) throws Throwable {
    GetProductItems searchProductItemsResult = sharedData.searchProductItemsResult;
    Assert.assertTrue(searchProductItemsResult.getTotalItemCount() >= 1, "There are no product items available. Please make sure product is searched correctly");

    List<String> availableStockCodes = getAvailableStockCodesFromSearchProductItemsResult(searchProductItemsResult, 1);

    String stockCode = availableStockCodes.get(0).replaceFirst("^0+(?!$)", "");
    sharedData.stockCodeTrader = stockCode;
    TrolleyResponse trolleyResponse = iAddProductsToTrolleyWithNotes(quantity, stockCode, true, note);
    Assert.assertTrue(trolleyResponse.getResponseStatus().equals("200"), "Stock Code:: " + stockCode + " couldn't be added to the trolley");
    Assert.assertTrue(trolleyResponse.getProducts().stream().findFirst().get().getNote().equalsIgnoreCase(note), "Note is not added to trolley");
  }

  @Then("^verify the note (.*) is added to item in trolley$")
  public void verifyShopperTrolleyItemNote(String note) throws Throwable {
    //TODO: Need to remove wait after observation. Add wait since there is some cache issue with Trolley

    TrolleyResponse trolleyResponse = iRetrieveTheShopperTrolley();
    String noteAdded = trolleyResponse.getProducts().stream().findFirst().get().getNote();
    //Assert.assertTrue(note.equals(noteAdded), "Note added to trolley does not match" + note + " added in cart");
  }

  @Given("^I want to add (.*) of the same item again to trolley$")
  public void iAddTheSameItemAgainToTrolley(int quantity) throws Throwable {

    String stockCode = sharedData.stockCodeTrader;
    TrolleyResponse trolleyResponse = iAddOrUpdateTheFollowingProductsToTheTrolley(quantity, stockCode, true);
    Assert.assertTrue(trolleyResponse.getResponseStatus().equals("200"), "Stock Code:: " + stockCode + " couldn't be added to the trolley");

  }

  @Then("^verify no note is added when we add the same product again with no note$")
  public void verifyNoNoteAddedToTheSameItem() throws Throwable {
    //TODO: Need to remove wait after observation. Add wait since there is some cache issue with Trolley
    try {
      Thread.sleep(3000);
    } catch (Exception e) {

    }
    TrolleyResponse trolleyResponse = iRetrieveTheShopperTrolley();
    String noteAdded = trolleyResponse.getProducts().stream().findFirst().get().getNote();
    Assert.assertTrue(noteAdded.equals(""), "Note is retained in the item but should be null");

  }

  @Given("^I add (.*) items to my trolley from search list with (.*) quantity each$")
  public void iAddFewItemsToMyTrolleyFromSearchListWithSpecifiedQuantity(int itemsToBeAdded, int quantity) throws Throwable {
    GetProductItems searchProductItemsResult = sharedData.searchProductItemsResult;
    Assert.assertTrue(searchProductItemsResult.getTotalItemCount() >= 1, "There are no product items available. Please make sure product is searched correctly");

    List<String> availableStockCodes = getAvailableStockCodesFromSearchProductItemsResult(searchProductItemsResult, itemsToBeAdded - 1);

    List<String> setAvailableStockCodes = new ArrayList<>();
    TrolleyResponse trolleyResponse;
    String stockCode;
    //Replacing the trolley while adding the first stockCode since trolley clear does not work
    for (int i = 0; i < availableStockCodes.size(); i++) {
      stockCode = availableStockCodes.get(i).replaceFirst("^0+(?!$)", "");
      setAvailableStockCodes.add(stockCode);
    }
    trolleyResponse = iAddFollowingProductsToTrolley(quantity, setAvailableStockCodes, true);
    Assert.assertTrue(trolleyResponse.getResponseStatus().equals("200"), "Stock Codes::" + setAvailableStockCodes.toString() + " couldn't be added to the trolley");
        /*//Replacing the trolley while adding the first stockCode since trolley clear does not work
        stockCode = availableStockCodes.get(0).replaceFirst("^0+(?!$)", "");
        //Replacing 0 and adding to list of stockCode and storing in pico container
        setAvailableStockCodes.add(stockCode);

            trolleyResponse = i_add_or_update_the_following_products_to_the_trolley(quantity, stockCode, sharedData.authToken, false);
            Assert.assertTrue(trolleyResponse.getResponseStatus().equals("200") , "Stock Code:: "+stockCode+ " couldn't be added to the trolley");
        }
        }*/
    sharedData.stockCodes = setAvailableStockCodes;
    trolleyResponse = iRetrieveTheShopperTrolley();
    sharedData.trolleyQuantity = trolleyResponse.getTotalProducts();
  }

  @Given("^I keep on adding (.*) items to my existing trolley from search list with (.*) quantity each$")
  public void iKeepOnAddingFewItemsToMyExistingTrolleyFromSearchListWithSpecifiedQuantity(int itemsToBeAdded, int quantity) throws Throwable {
    GetProductItems searchProductItemsResult = sharedData.searchProductItemsResult;
    Assert.assertTrue(searchProductItemsResult.getTotalItemCount() >= 1, "There are no product items available. Please make sure product is searched correctly");
    List<String> availableStockCodes = getAvailableStockCodesFromSearchProductItemsResult(searchProductItemsResult, itemsToBeAdded - 1);

    List<String> setAvailableStockCodes = new ArrayList<>();
    TrolleyResponse trolleyResponse;
    String stockCode;
    //Replacing the trolley while adding the first stockCode since trolley clear does not work
    for (int i = 0; i < itemsToBeAdded; i++) {
      stockCode = availableStockCodes.get(i).replaceFirst("^0+(?!$)", "");
      setAvailableStockCodes.add(stockCode);
    }
    trolleyResponse = iAddFollowingProductsToTrolley(quantity, setAvailableStockCodes, false);
    Assert.assertTrue(trolleyResponse.getResponseStatus().equals("200"), "Stock Codes::" + setAvailableStockCodes.toString() + " couldn't be added to the trolley");
    sharedData.stockCodes = setAvailableStockCodes;
    trolleyResponse = iRetrieveTheShopperTrolley();
    sharedData.trolleyQuantity = trolleyResponse.getTotalProducts();
  }

}
