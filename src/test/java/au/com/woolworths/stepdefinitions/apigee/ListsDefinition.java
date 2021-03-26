package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.TrolleyHelper;
import au.com.woolworths.utils.Utilities;
import au.com.woolworths.helpers.apigee.ListHelper;
import au.com.woolworths.model.apigee.lists.GetListResponse;
import au.com.woolworths.model.apigee.lists.ListDetailsResponse;
import au.com.woolworths.model.apigee.lists.ListResponse;
import au.com.woolworths.model.apigee.lists.SwitchDefaultListResponse;
import au.com.woolworths.model.apigee.search.SearchResponseV3;
import au.com.woolworths.model.apigee.trolley.TrolleyV2Response;
import au.com.woolworths.model.apigee.trolley.TrolleyV3Response;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class ListsDefinition extends ListHelper {

  private final static Logger logger = Logger.getLogger("ListsDefinition.class");


  @When("^I create a list with exact list name as \"([^\"]*)\"$")
  public void createExactList(String listName) throws Throwable {
    createLists(listName);
  }

  @When("^I create a list with list name as \"([^\"]*)\"$")
  public void createListAs(String listName) throws Throwable {
    listName = listName + Utilities.getSaltString();
    createExactList(listName);
  }

  public void createLists(String listName) throws Throwable {
    ListResponse listResponse = createList(listName);
    sharedData.currentListId = listResponse.getUpdate().getId();
    sharedData.listTimeStamp = listResponse.getUpdate().getTimestamp();
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(listResponse.getChanges());
    Assert.assertTrue("List name added does not match the returned list name", listName.matches(listResponse.getUpdate().getTitle()));

  }

  @When("^I add free text item \"([^\"]*)\" to list$")
  public void iAddFreeTextItemToList(String freeTextItem) throws Throwable {
    ListResponse updatedResponse = addFreeTextItemToTheList(sharedData.currentListId, freeTextItem);
    Assert.assertNotNull("The Update List did not happen", updatedResponse.getUpdate());
  }

  @When("^I add free text item \"([^\"]*)\" to list and check the item to list$")
  public void iAddFreeTextItemToListAndCheckTheItem(String freeTextItem) throws Throwable {
    ListResponse updatedResponse = addFreeTextItemToTheList(sharedData.currentListId, freeTextItem, true);
    Assert.assertNotNull("Free Text is not created", updatedResponse.getUpdate().getId());
    sharedData.currentFreeTextId = updatedResponse.getUpdate().getId();
  }

  @When("^user verifies the free text item \"([^\"]*)\" is added to list$")
  public void userVerifiesTheFreeTextItemIsAddedToList(String freeTextItem) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(sharedData.currentListId, "V2");
    Assert.assertTrue("List is empty", listDetails.getCount() >= 1);
    if (listDetails.getFreeTextItems().length == 1) {
      Assert.assertTrue("Text item name is not matching with expected:" + freeTextItem, listDetails.getFreeTextItems()[0].getText().equalsIgnoreCase(freeTextItem));
    } else {
      Assert.assertTrue("There are more Free Text Items in the list: " + listDetails.getFreeTextItems().length, false);
    }
  }

  @When("^user verifies the free text item \"([^\"]*)\" is added to list and is checked$")
  public void userVerifiesTheFreeTextItemIsAddedToListAndIsChecked(String freeText) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(sharedData.currentListId, "V2");
    Assert.assertTrue("Free Text item is empty", listDetails.getFreeTextItems().length >= 1);
    boolean foundFreeText = false;
    for (int i = 0; i < listDetails.getFreeTextItems().length; i++) {
      if (Long.toString(listDetails.getFreeTextItems()[i].getId()).equals(sharedData.currentFreeTextId)) {
        foundFreeText = true;
        Assert.assertTrue("Free Text Item (" + freeText + ") is not checked as expected", listDetails.getFreeTextItems()[i].isChecked());
      }
    }
    Assert.assertTrue("Could not find the last created free text id", foundFreeText);
  }

  @And("^user verifies the free text item \"([^\"]*)\" is added to \"([^\"]*)\" is checked$")
  public void userVerifiesTheFreeTextItemIsAddedToIsChecked(String freeText, String listName) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName)), "V2");
    Assert.assertTrue("Free Text item is empty", listDetails.getFreeTextItems().length >= 1);
    boolean foundFreeText = false;
    for (int i = 0; i < listDetails.getFreeTextItems().length; i++) {
      if (Long.toString(listDetails.getFreeTextItems()[i].getId()).equals(sharedData.currentFreeTextId)) {
        foundFreeText = true;
        Assert.assertTrue("Free Text Item (" + freeText + ") is not checked as expected", listDetails.getFreeTextItems()[i].isChecked());
      }
    }
    Assert.assertTrue("Could not find the last created free text id", foundFreeText);
  }

  @And("^user verifies the free text item \"([^\"]*)\" is added to \"([^\"]*)\" is unchecked$")
  public void userVerifiesTheFreeTextItemIsAddedToIsUnchecked(String freeText, String listName) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName)), "V2");
    Assert.assertTrue("Free Text item is empty", listDetails.getFreeTextItems().length >= 1);
    boolean foundFreeText = false;
    for (int i = 0; i < listDetails.getFreeTextItems().length; i++) {
      if (Long.toString(listDetails.getFreeTextItems()[i].getId()).equals(sharedData.currentFreeTextId)) {
        foundFreeText = true;
        Assert.assertFalse("Free Text Item (" + freeText + ") is checked", listDetails.getFreeTextItems()[i].isChecked());
      }
    }
    Assert.assertTrue("Could not find the last created free text id", foundFreeText);
  }

  @When("^user un-checks the free text item \"([^\"]*)\" from the newly created list$")
  public void userUnChecksTheFreeTextItemFromTheNewlyCreatedList(String freeText) throws Throwable {
    ListResponse listUpdateResponse = updateFreeTextItemInTheList(Long.parseLong(sharedData.currentListId), Long.parseLong(sharedData.currentFreeTextId), freeText, false);
    Assert.assertTrue("Free Text Item status is not set to UPDATE", listUpdateResponse.getUpdate().getStatus().equalsIgnoreCase("UPDATE"));
    Assert.assertTrue("Free Text Item could not be updated", listUpdateResponse.getUpdate().getText().equalsIgnoreCase(freeText));
  }

  @When("^checks free text item \"([^\"]*)\" from the \"([^\"]*)\"$")
  public void checksFreeTextItemFromTheList(String freeText, String listName) throws Throwable {
    long listId = getListIdForTheUser(listName);
    ListResponse listUpdateResponse = updateFreeTextItemInTheList(listId, getFreeTextIdForTheList(freeText, listId), freeText, true);
    Assert.assertTrue("Free Text Item status is not set to UPDATE", listUpdateResponse.getUpdate().getStatus().equalsIgnoreCase("UPDATE"));
    Assert.assertTrue("Free Text Item could not be updated", listUpdateResponse.getUpdate().getText().equalsIgnoreCase(freeText));
  }

  @When("^user deletes free text item \"([^\"]*)\" from the new list$")
  public void userDeletesFreeTextItemFromTheNewList(String freeTextItem) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(sharedData.currentListId, "V2");
    boolean foundFreeTextItem = false;
    String freeTextId = "";
    int countOfFreeTextItems = listDetails.getFreeTextItems().length;
    for (int i = 0; i < countOfFreeTextItems; i++) {
      if (listDetails.getFreeTextItems()[i].getText().equalsIgnoreCase(freeTextItem)) {
        foundFreeTextItem = true;
        freeTextId = String.valueOf(listDetails.getFreeTextItems()[i].getId());
      }
    }
    Assert.assertTrue("The Free Text item is not available in the list", foundFreeTextItem);
    Assert.assertTrue("FreeText Id is BLANK", freeTextId != "");

    ListResponse response = deleteFreeTextFromList(sharedData.currentListId, freeTextId);
    Assert.assertTrue("The Free text item is not matching", response.getUpdate().getText().equals(freeTextItem));
    Assert.assertTrue("Status of Free Text is not DELETE", response.getUpdate().getStatus().equals("DELETE"));
  }

  @And("^I verify list \"([^\"]*)\" is set as default list$")
  public void verifyDefaultList(String listName) throws Throwable {

    GetListResponse apigeeListResponse = retrieveList();
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(apigeeListResponse.getLists());
    Assert.assertTrue("List " + listName + " is not set as Default List", apigeeListResponse.getLists()[0].getTitle().contains(listName));

  }

  @And("^I switch the default list to \"([^\"]*)\"$")
  public void switchDefaultList(String listName) throws Throwable {

    SwitchDefaultListResponse switchDefaultListResponse = switchToDefaultList(listName);
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertTrue("Not switched to default list", switchDefaultListResponse.getStatus().matches("OK"));

  }

  @And("^I delete the newly created list$")
  public void iDeleteTheNewlyCreatedList() throws Throwable {
    ListResponse response = deleteNewlyCreatedList(sharedData.currentListId, sharedData.listTimeStamp);
    Assert.assertTrue("List couldn't be deleted", response.getUpdate().getId().equals(sharedData.currentListId));
  }

  @When("^I delete all the list for the user$")
  public void iDeleteAllTheListForTheUser() throws Throwable {
    GetListResponse apigeeListResponse = retrieveList();
    //Please make sure to delete all list except 1 as default list is needed
    for (int i = 1; i < apigeeListResponse.getLists().length; i++) {
      ListResponse response = deleteNewlyCreatedList(apigeeListResponse.getLists()[i].getId(), Long.parseLong(apigeeListResponse.getLists()[i].getTimestamp()));
    }

  }

  @And("^I clear ALL the lists for the user$")
  public void iClearAllTheListForTheUser() throws Throwable {
    GetListResponse getListResponse = retrieveList();
    if (getListResponse.getLists().length != 0) {
      for (int i = 0; i < getListResponse.getLists().length; i++) {
        String listId = getListResponse.getLists()[i].getId();
        String timestamp = getListResponse.getLists()[i].getTimestamp();
        String listName = getListResponse.getLists()[i].getTitle();
        ListResponse deleteListResponse = deleteTheList(Long.parseLong(listId), Long.parseLong(timestamp));
        Assert.assertTrue(listId + " (List ID) Could not be deleted", deleteListResponse.getUpdate().getId().equals(listId));
        Assert.assertTrue(listName + " (List Name) Could not be deleted", deleteListResponse.getUpdate().getTitle().equals(listName));
      }

    }

  }

  @When("^I add (.*) available products with \"([^\"]*)\" each from the store to (.*) list \"([^\"]*)\"$")
  public void iAddAvailableProductsWithEachFromTheStoreToList(int prodQty, int listQty, String version, String listName) throws Throwable {
    SearchResponseV3 searchResponse = sharedData.searchProductResponse;
    List<String> stockCodes = new ArrayList<String>();
    for (int i = 0; i < searchResponse.getProducts().length; i++) {
      if (searchResponse.getProducts()[i].getIs().isRanged()) {
        addItemsToTheList(searchResponse.getProducts()[i].getArticle(), listQty, sharedData.currentListId, true, version);
        stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));

      }
      if (stockCodes.size() == prodQty) {
        break;
      }
    }

    Assert.assertTrue("There are no products available in Store", stockCodes.size() != 0);
    ListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName)), version);
    Assert.assertTrue("Error: Invalid number of items in the list", prodQty == listDetails.getCount());
  }

  @Then("^I verify that the items saved to \"([^\"]*)\" list \"([^\"]*)\" are unchecked$")
  public void iVerifyThatTheItemsSavedToListAreUnchecked(String version, String listName) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName)), version);
    Assert.assertTrue("Error:There are no products in the list", listDetails.getProducts().length >= 1);
    for (int i = 0; i < listDetails.getProducts().length; i++) {

      Assert.assertTrue("Items(" + listDetails.getProducts()[i].getArticleId() + ") is checked", listDetails.getProducts()[i].isChecked());
    }

  }

  @Then("^I add items to cart after selecting \"([^\"]*)\" for every item from \"([^\"]*)\" list \"([^\"]*)\"$")
  public void iAddItemsToCartAfterSelectingForEveryItemFromList(int quantity, String version, String listName) throws Throwable {
    ListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName)), version);
    TrolleyHelper trolleyHelper = new TrolleyHelper();
    List<String> stockCodes = new ArrayList<String>();
    for (int i = 0; i < listDetails.getProducts().length; i++) {
      stockCodes.add(String.valueOf(listDetails.getProducts()[i].getArticleId()));
    }
    if (version.equals("V2")) {
      TrolleyV2Response trolleyResponse = trolleyHelper.addStockCodesToTheV2Trolley(stockCodes, quantity, true);
      Assert.assertTrue("Products is not added as expected:" + listDetails.getProducts().length, trolleyResponse.getTotalproducts() == listDetails.getProducts().length);
      sharedData.trolleyV2Response = trolleyResponse;
    } else {
      TrolleyV3Response trolleyResponse = trolleyHelper.addStockCodesToTheV3Trolley(stockCodes, quantity, true);
      Assert.assertTrue("Products is not added as expected:" + listDetails.getProducts().length, trolleyResponse.getTrolley().getTotalProducts() == listDetails.getProducts().length);
      sharedData.trolleyV3Response = trolleyResponse;
    }

  }

  @Then("^I verify that the correct items with quantity from \"([^\"]*)\" list \"([^\"]*)\" are added to the cart$")
  public void iVerifyThatTheCorrectItemsWithQuantityAreAddedToTheCart(String version, String listName) throws Throwable {
    //Initialize a list of stock codes.
    List<String> listStockCodes = new ArrayList<String>();
    List<String> trolleyStockCodes = new ArrayList<String>();
    ListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName)), version);

    for (int i = 0; i < listDetails.getProducts().length; i++) {
      listStockCodes.add(String.valueOf(listDetails.getProducts()[i].getArticleId()));
    }

    if (version.equals("V2")) {
      TrolleyV2Response trolleyResponse = sharedData.trolleyV2Response;

      for (int i = 0; i < trolleyResponse.getItems().size(); i++) {
        trolleyStockCodes.add(trolleyResponse.getItems().get(i).getArticle().replaceFirst("^0+(?!$)", ""));
      }

    } else {

      TrolleyV3Response trolleyResponse = sharedData.trolleyV3Response;
      for (int i = 0; i < trolleyResponse.getTrolley().getItems().size(); i++) {

        trolleyStockCodes.add(trolleyResponse.getTrolley().getItems().get(i).getArticle().replaceFirst("^0+(?!$)", ""));
      }

    }
    Assert.assertTrue("Error:Item not found: Items present in the cart doesn't exsist in list", trolleyStockCodes.containsAll(listStockCodes));

  }

}