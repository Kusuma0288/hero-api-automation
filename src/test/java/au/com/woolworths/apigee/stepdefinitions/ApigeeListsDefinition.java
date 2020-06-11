package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.helpers.TrolleyHelper;
import au.com.woolworths.Utils.Utilities;
import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.ApigeeListHelper;
import au.com.woolworths.apigee.model.AddProdListDetailsResponse;
import au.com.woolworths.apigee.model.AddProductsToListResponse;
import au.com.woolworths.apigee.model.ApigeeGetListResponse;
import au.com.woolworths.apigee.model.ApigeeListDetailsResponse;
import au.com.woolworths.apigee.model.ApigeeListResponse;
import au.com.woolworths.apigee.model.ApigeeSwitchDefaultListResponse;
import au.com.woolworths.apigee.model.ApigeeV3SearchResponse;
import au.com.woolworths.apigee.model.TrolleyV2Response;
import au.com.woolworths.apigee.model.TrolleyV3Response;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ApigeeListsDefinition extends ApigeeListHelper {

  private final static Logger logger = Logger.getLogger("ApigeeListsDefinition.class");

  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public ApigeeListsDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^I create a list with exact list name as \"([^\"]*)\"$")
  public void createExactList(String listName) throws Throwable {
    createList(listName);
  }

  @When("^I create a list with list name as \"([^\"]*)\"$")
  public void createListAs(String listName) throws Throwable {
    listName = listName + Utilities.getSaltString();
    createList(listName);
  }

  public void createList(String listName) throws Throwable {
    ApigeeListResponse apigeeListResponse = createList(listName, sharedData.accessToken);
    picoContainer.currentListId = apigeeListResponse.getUpdate().getId();
    picoContainer.listTimeStamp = apigeeListResponse.getUpdate().getTimestamp();
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(apigeeListResponse.getChanges());
    Assert.assertTrue("List name added does not match the returned list name", listName.matches(apigeeListResponse.getUpdate().getTitle()));

  }

  @When("^I add free text item \"([^\"]*)\" to list$")
  public void i_add_free_text_item_to_list(String freeTextItem) throws Throwable {
    ApigeeListResponse updatedResponse = addFreeTextItemToTheList(picoContainer.currentListId, freeTextItem, sharedData.accessToken);
    Assert.assertNotNull("The Update List did not happen", updatedResponse.getUpdate());
  }

  @When("^I add free text item \"([^\"]*)\" to list and check the item to list$")
  public void i_add_free_text_item_to_list_and_check_the_item(String freeTextItem) throws Throwable {
    ApigeeListResponse updatedResponse = addFreeTextItemToTheList(picoContainer.currentListId, freeTextItem, true, sharedData.accessToken);
    Assert.assertNotNull("Free Text is not created", updatedResponse.getUpdate().getId());
    picoContainer.currentFreeTextId = updatedResponse.getUpdate().getId();
  }

  @When("^user verifies the free text item \"([^\"]*)\" is added to list$")
  public void user_verifies_the_free_text_item_is_added_to_list(String freeTextItem) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(picoContainer.currentListId, sharedData.accessToken, "V2");
    Assert.assertTrue("List is empty", listDetails.getCount() >= 1);
    if (listDetails.getFreeTextItems().length == 1) {
      Assert.assertTrue("Text item name is not matching with expected:" + freeTextItem, listDetails.getFreeTextItems()[0].getText().equalsIgnoreCase(freeTextItem));
    } else {
      Assert.assertTrue("There are more Free Text Items in the list: " + listDetails.getFreeTextItems().length, false);
    }
  }

  @When("^user verifies the free text item \"([^\"]*)\" is added to list and is checked$")
  public void user_verifies_the_free_text_item_is_added_to_list_and_is_checked(String freeText) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(picoContainer.currentListId, sharedData.accessToken, "V2");
    Assert.assertTrue("Free Text item is empty", listDetails.getFreeTextItems().length >= 1);
    boolean foundFreeText = false;
    for (int i = 0; i < listDetails.getFreeTextItems().length; i++) {
      if (Long.toString(listDetails.getFreeTextItems()[i].getId()).equals(picoContainer.currentFreeTextId)) {
        foundFreeText = true;
        Assert.assertTrue("Free Text Item (" + freeText + ") is not checked as expected", listDetails.getFreeTextItems()[i].isChecked());
      }
    }
    Assert.assertTrue("Could not find the last created free text id", foundFreeText);
  }

  @And("^user verifies the free text item \"([^\"]*)\" is added to \"([^\"]*)\" is checked$")
  public void user_verifies_the_free_text_item_is_added_to_is_checked(String freeText, String listName) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName, sharedData.accessToken)), sharedData.accessToken, "V2");
    Assert.assertTrue("Free Text item is empty", listDetails.getFreeTextItems().length >= 1);
    boolean foundFreeText = false;
    for (int i = 0; i < listDetails.getFreeTextItems().length; i++) {
      if (Long.toString(listDetails.getFreeTextItems()[i].getId()).equals(picoContainer.currentFreeTextId)) {
        foundFreeText = true;
        Assert.assertTrue("Free Text Item (" + freeText + ") is not checked as expected", listDetails.getFreeTextItems()[i].isChecked());
      }
    }
    Assert.assertTrue("Could not find the last created free text id", foundFreeText);
  }

  @And("^user verifies the free text item \"([^\"]*)\" is added to \"([^\"]*)\" is unchecked$")
  public void user_verifies_the_free_text_item_is_added_to_is_unchecked(String freeText, String listName) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName, sharedData.accessToken)), sharedData.accessToken, "V2");
    Assert.assertTrue("Free Text item is empty", listDetails.getFreeTextItems().length >= 1);
    boolean foundFreeText = false;
    for (int i = 0; i < listDetails.getFreeTextItems().length; i++) {
      if (Long.toString(listDetails.getFreeTextItems()[i].getId()).equals(picoContainer.currentFreeTextId)) {
        foundFreeText = true;
        Assert.assertFalse("Free Text Item (" + freeText + ") is checked", listDetails.getFreeTextItems()[i].isChecked());
      }
    }
    Assert.assertTrue("Could not find the last created free text id", foundFreeText);
  }

  @When("^user un-checks the free text item \"([^\"]*)\" from the newly created list$")
  public void user_un_checks_the_free_text_item_from_the_newly_created_list(String freeText) throws Throwable {
    ApigeeListResponse listUpdateResponse = updateFreeTextItemInTheList(Long.parseLong(picoContainer.currentListId), Long.parseLong(picoContainer.currentFreeTextId), freeText, false, sharedData.accessToken);
    Assert.assertTrue("Free Text Item status is not set to UPDATE", listUpdateResponse.getUpdate().getStatus().equalsIgnoreCase("UPDATE"));
    Assert.assertTrue("Free Text Item could not be updated", listUpdateResponse.getUpdate().getText().equalsIgnoreCase(freeText));
  }

  @When("^checks free text item \"([^\"]*)\" from the \"([^\"]*)\"$")
  public void checks_free_text_item_from_the_list(String freeText, String listName) throws Throwable {
    long listId = getListIdForTheUser(listName, sharedData.accessToken);
    ApigeeListResponse listUpdateResponse = updateFreeTextItemInTheList(listId, getFreeTextIdForTheList(freeText, listId, sharedData.accessToken), freeText, true, sharedData.accessToken);
    Assert.assertTrue("Free Text Item status is not set to UPDATE", listUpdateResponse.getUpdate().getStatus().equalsIgnoreCase("UPDATE"));
    Assert.assertTrue("Free Text Item could not be updated", listUpdateResponse.getUpdate().getText().equalsIgnoreCase(freeText));
  }

  @When("^user deletes free text item \"([^\"]*)\" from the new list$")
  public void user_deletes_free_text_item_from_the_new_list(String freeTextItem) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(picoContainer.currentListId, sharedData.accessToken, "V2");
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

    ApigeeListResponse response = deleteFreeTextFromList(picoContainer.currentListId, freeTextId, sharedData.accessToken);
    Assert.assertTrue("The Free text item is not matching", response.getUpdate().getText().equals(freeTextItem));
    Assert.assertTrue("Status of Free Text is not DELETE", response.getUpdate().getStatus().equals("DELETE"));
  }

  @And("^I verify list \"([^\"]*)\" is set as default list$")
  public void verifyDefaultList(String listName) throws Throwable {

    ApigeeGetListResponse apigeeListResponse = retrieveList(sharedData.accessToken);
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(apigeeListResponse.getLists());
    Assert.assertTrue("List " + listName + " is not set as Default List", apigeeListResponse.getLists()[0].getTitle().contains(listName));

  }

  @And("^I switch the default list to \"([^\"]*)\"$")
  public void switchDefaultList(String listName) throws Throwable {

    ApigeeSwitchDefaultListResponse switchDefaultListResponse = switchToDefaultList(listName, sharedData.accessToken);
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertTrue("Not switched to default list", switchDefaultListResponse.getStatus().matches("OK"));

  }

  @And("^I delete the newly created list$")
  public void i_delete_the_newly_created_list() throws Throwable {
    ApigeeListResponse response = deleteNewlyCreatedList(picoContainer.currentListId, picoContainer.listTimeStamp, sharedData.accessToken);
    Assert.assertTrue("List couldn't be deleted", response.getUpdate().getId().equals(picoContainer.currentListId));
  }

  @When("^I delete all the list for the user$")
  public void i_delete_all_the_list_for_the_user() throws Throwable {
    ApigeeGetListResponse apigeeListResponse = retrieveList(sharedData.accessToken);
    //Please make sure to delete all list except 1 as default list is needed
    for (int i = 1; i < apigeeListResponse.getLists().length; i++) {
      ApigeeListResponse response = deleteNewlyCreatedList(apigeeListResponse.getLists()[i].getId(), Long.parseLong(apigeeListResponse.getLists()[i].getTimestamp()), sharedData.accessToken);
    }

  }

  @And("^I clear ALL the list for the user$")
  public void i_clear_all_the_list_for_the_user() throws Throwable {
    ApigeeGetListResponse getListResponse = getAllListForTheUser(sharedData.accessToken);
    if (getListResponse.getLists().length != 0) {
      for (int i = 0; i < getListResponse.getLists().length; i++) {
        String listId = getListResponse.getLists()[i].getId();
        String timestamp = getListResponse.getLists()[i].getTimestamp();
        String listName = getListResponse.getLists()[i].getTitle();
        ApigeeListResponse deleteListResponse = deleteTheList(Long.parseLong(listId), Long.parseLong(timestamp), sharedData.accessToken);
        Assert.assertTrue(listId + " (List ID) Could not be deleted", deleteListResponse.getUpdate().getId().equals(listId));
        Assert.assertTrue(listName + " (List Name) Could not be deleted", deleteListResponse.getUpdate().getTitle().equals(listName));
      }

    }

  }

  @When("^I add (.*) available products with \"([^\"]*)\" each from the store to (.*) list \"([^\"]*)\"$")
  public void i_add_available_products_with_each_from_the_store_to_list(int prodQty, int listQty, String version, String listName) throws Throwable {
    ApigeeV3SearchResponse searchResponse = sharedData.searchProductResponse;
    List<String> stockCodes = new ArrayList<String>();
    for (int i = 0; i < searchResponse.getProducts().length; i++) {
      if (searchResponse.getProducts()[i].getIs().isRanged()) {
        addItemsToTheList(searchResponse.getProducts()[i].getArticle(), listQty, picoContainer.currentListId, true, sharedData.accessToken, version);
        stockCodes.add(searchResponse.getProducts()[i].getArticle().replaceFirst("^0+(?!$)", ""));

      }
      if (stockCodes.size() == prodQty) {
        break;
      }
    }

    Assert.assertTrue("There are no products available in Store", stockCodes.size() != 0);
    ApigeeListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName, sharedData.accessToken)), sharedData.accessToken, version);
    Assert.assertTrue("Error: Invalid number of items in the list", prodQty == listDetails.getCount());
  }

  @Then("^I verify that the items saved to \"([^\"]*)\" list \"([^\"]*)\" are unchecked$")
  public void i_verify_that_the_items_saved_to_list_are_unchecked(String version, String listName) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName, sharedData.accessToken)), sharedData.accessToken, version);
    Assert.assertTrue("Error:There are no products in the list", listDetails.getProducts().length >= 1);
    for (int i = 0; i < listDetails.getProducts().length; i++) {

      Assert.assertTrue("Items(" + listDetails.getProducts()[i].getArticleId() + ") is checked", listDetails.getProducts()[i].isChecked());
    }

  }

  @Then("^I add items to cart after selecting \"([^\"]*)\" for every item from \"([^\"]*)\" list \"([^\"]*)\"$")
  public void i_add_items_to_cart_after_selecting_for_every_item_from_list(int quantity, String version, String listName) throws Throwable {
    ApigeeListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName, sharedData.accessToken)), sharedData.accessToken, version);
    TrolleyHelper trolleyHelper = new TrolleyHelper();
    List<String> stockCodes = new ArrayList<String>();
    for (int i = 0; i < listDetails.getProducts().length; i++) {
      stockCodes.add(String.valueOf(listDetails.getProducts()[i].getArticleId()));
    }
    if (version.equals("V2")) {
      TrolleyV2Response trolleyResponse = trolleyHelper.addStockCodesToTheV2Trolley(stockCodes, quantity, true, sharedData.accessToken);
      Assert.assertTrue("Products is not added as expected:" + listDetails.getProducts().length, trolleyResponse.getTotalproducts() == listDetails.getProducts().length);
      sharedData.trolleyV2Response = trolleyResponse;
    } else {
      TrolleyV3Response trolleyResponse = trolleyHelper.addStockCodesToTheV3Trolley(stockCodes, quantity, true, sharedData.accessToken);
      Assert.assertTrue("Products is not added as expected:" + listDetails.getProducts().length, trolleyResponse.getTrolley().getTotalProducts() == listDetails.getProducts().length);
      sharedData.trolleyV3Response = trolleyResponse;
    }

  }

  @Then("^I verify that the correct items with quantity from \"([^\"]*)\" list \"([^\"]*)\" are added to the cart$")
  public void i_verify_that_the_correct_items_with_quantity_are_added_to_the_cart(String version, String listName) throws Throwable {
    //Initialize a list of stock codes.
    List<String> listStockCodes = new ArrayList<String>();
    List<String> trolleyStockCodes = new ArrayList<String>();
    ApigeeListDetailsResponse listDetails = getListDetails(String.valueOf(getListIdForTheUser(listName, sharedData.accessToken)), sharedData.accessToken, version);

    for (int i = 0; i < listDetails.getProducts().length; i++) {
      listStockCodes.add(String.valueOf(listDetails.getProducts()[i].getArticleId()));
    }

    if (version.equals("V2")) {
      TrolleyV2Response trolleyResponse = sharedData.trolleyV2Response;

      for (int i = 0; i < trolleyResponse.getItems().size(); i++) {
        trolleyStockCodes.add(trolleyResponse.getItems().get(i).getArticle().replaceFirst("^0+(?!$)", ""));
      }

    }else {

      TrolleyV3Response trolleyResponse = sharedData.trolleyV3Response;
      for (int i = 0; i < trolleyResponse.getTrolley().getTrolleyitemsListResp().size(); i++) {

        trolleyStockCodes.add(trolleyResponse.getTrolley().getTrolleyitemsListResp().get(i).getArticle().replaceFirst("^0+(?!$)", ""));
      }

    }
    Assert.assertTrue("Error:Item not found: Items present in the cart doesn't exsist in list", trolleyStockCodes.containsAll(listStockCodes));

  }
}