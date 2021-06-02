package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.helpers.iris.graphql.ListHelper;
import au.com.woolworths.model.iris.graphql.list.Lists;
import au.com.woolworths.model.iris.graphql.list.SyncListResponse;
import au.com.woolworths.stepdefinitions.apigee.ListsDefinition;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.UUID;

public class ListDefinition extends ListHelper {
  public GraphqlHelper graphqlHelper = new GraphqlHelper();

  ObjectMapper mapper = new ObjectMapper();

  @And("^I create a list with list name \"([^\"]*)\" and color \"([^\"]*)\"$")
  public void iCreateAListWithListNameAndFromGraphqlAndAddedFreeTextAndProduct(String listName, String color) throws IOException {
    listName = listName + Utilities.getSaltString();
    String uuidAsString = UUID.randomUUID().toString();
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/createList.graphql");

    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.LIST_NAME.get(), listName);
    variables.put(Typename.COLOR.get(), color);
    variables.put(Typename.REFERENCE_ID.get(), uuidAsString);
    sharedData.listName = listName;
    sharedData.color = color;

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    sharedData.syncListResponse = syncListResponse;
  }

  @Then("^I verify list is created with correct details$")
  public void iVerifyListIsCreatedWithCorrectDetails() {
    Assert.assertEquals("ListName is not matching", sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getTitle(), sharedData.listName);
  }

  @Then("I verify created list by listId")
  public void iVerifyCreatedListByListId() throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/getList.graphql");

    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.LIST_ID.get(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String response = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(response, SyncListResponse.class);

    Assert.assertEquals("List Name is not matching", syncListResponse.getData().getList().getTitle(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getTitle());
    Assert.assertEquals("ListId is not matching", syncListResponse.getData().getList().getId(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());
  }

  @When("^I edit a created list to list name \"([^\"]*)\" with color \"([^\"]*)\"$")
  public void iEditAListWithListNameToWithColor(String listNameNew, String color) throws IOException {
    listNameNew = listNameNew + Utilities.getSaltString();
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/EditList.graphql");

    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.ID.get(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());
    variables.put(Typename.TIMESTAMP.get(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getTimestamp());
    variables.put(Typename.LIST_NAME.get(), listNameNew);
    variables.put(Typename.COLOR.get(), color);
    sharedData.listName = listNameNew;

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    sharedData.listResponseEdited = syncListResponse;
  }

  @Then("^I verify list is Edited with correct details$")
  public void iVerifyListIsEditedWithCorrectDetails() {
    Assert.assertEquals("ListName is not matching", sharedData.listResponseEdited.getData().getSyncLists().getUpdatedLists()[0].getTitle(), sharedData.listName);
  }

  @And("^I delete the newly created and edited list$")
  public void iDeleteTheNewlyCreatedAndEditedList() throws Throwable {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/DeleteList.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.ID.get(), sharedData.listResponseEdited.getData().getSyncLists().getUpdatedLists()[0].getId());
    variables.put(Typename.TIMESTAMP.get(), sharedData.listResponseEdited.getData().getSyncLists().getUpdatedLists()[0].getTimestamp());

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    Assert.assertEquals("ListId is not matching", sharedData.listResponseEdited.getData().getSyncLists().getUpdatedLists()[0].getId(), Integer.parseInt(syncListResponse.getData().getSyncLists().getDeletedLists()[0]));
  }

  @And("^I get a list of lists$")
  public void iGetAListOfLists() throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/listOfLists.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String listOfListsResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(listOfListsResponse, SyncListResponse.class);

    Lists[] lists = syncListResponse.getData().getLists();
    Assert.assertTrue("List should not be empty", lists.length > 0);

    Boolean found = false;
    for (int i = 0; i < lists.length; i++) {
      if (lists[i].getTitle().contentEquals(sharedData.listName)) {
        found = true;
        break;
      }
    }
    Assert.assertTrue("List Name (" + sharedData.listName + ") is not found", found);
  }

  @And("I add {int} numbers of a Product from search result and free text {string} in the list")
  public void iAddProductAndFreeTextInTheList(int quantity, String freeText) throws IOException {
    String productCode = sharedData.availableProducts.get(0).getProductId();
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/createSyncListItem.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.LIST_ID.get(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());
    variables.put(Typename.TIMESTAMP.get(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getTimestamp());
    variables.put(Typename.PRODUCT_ID.get(), productCode);
    variables.put(Typename.QUANTITY.get(), quantity);
    variables.put(Typename.CHECKED.get(), false);
    variables.put(Typename.FREE_TEXT.get(), freeText);
    variables.put(Typename.REFERENCE_ID.get(), UUID.randomUUID().toString());
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    sharedData.syncListItems = syncListResponse.getData().getSyncListItems();
    sharedData.freeText = freeText;
    sharedData.quantity = quantity;
  }

  @And("I update Product quantity to {int} and free text {string} in the list")
  public void iUpdateProductQuantityToAndFreeTextInTheList(int quantity, String freeText) throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/updateSyncListItem.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.LIST_ID.get(), sharedData.syncListItems.getListId());
    variables.put(Typename.TIMESTAMP.get(), sharedData.syncListItems.getCreatedProductItems()[0].getTimestamp());
    variables.put(Typename.PRODUCT_ID.get(), String.valueOf(sharedData.syncListItems.getCreatedProductItems()[0].getProductId()));
    variables.put(Typename.QUANTITY.get(), quantity);
    variables.put(Typename.CHECKED.get(), false);
    variables.put(Typename.FREE_TEXT.get(), freeText);
    variables.put(Typename.ID_PROD.get(), sharedData.syncListItems.getCreatedProductItems()[0].getId());
    variables.put(Typename.ID_FREE.get(), sharedData.syncListItems.getCreatedFreeTextItems()[0].getId());
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    sharedData.syncListItems = syncListResponse.getData().getSyncListItems();
    sharedData.freeText = freeText;
    sharedData.quantity = quantity;
  }

  @Then("I verify Product and free text is added with correct details")
  public void iVerifyProductAndFreeTextIsAddedWithCorrectDetails() {
    Assert.assertEquals("ListId is not matching", sharedData.syncListItems.getListId(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());
    Assert.assertEquals("List free text is not matching", sharedData.syncListItems.getCreatedFreeTextItems()[0].getText(), sharedData.freeText);
    Assert.assertTrue("List Product id is not matching", sharedData.availableProducts.get(0).getProductId().contains(String.valueOf(sharedData.syncListItems.getCreatedProductItems()[0].getProductId())));
    Assert.assertEquals("List Product quantity is not matching", sharedData.syncListItems.getCreatedProductItems()[0].getQuantity(), sharedData.quantity);
  }

  @Then("I verify Product and free text is updated with correct details")
  public void iVerifyProductAndFreeTextIsUpdatedWithCorrectDetails() {
    Assert.assertEquals("ListId is not matching", sharedData.syncListItems.getListId(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());
    Assert.assertEquals("List free text is not matching", sharedData.syncListItems.getEditedFreeTextItems()[0].getText(), sharedData.freeText);
    Assert.assertTrue("List Product id is not matching", sharedData.availableProducts.get(0).getProductId().contains(String.valueOf(sharedData.syncListItems.getEditedProductItems()[0].getProductId())));
    Assert.assertEquals("List Product quantity is not matching", sharedData.syncListItems.getEditedProductItems()[0].getQuantity(), sharedData.quantity);
  }

  @Then("I delete the product added to the list")
  public void iDeleteTheProductAddedToTheList() throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/deleteSyncListItem.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.LIST_ID.get(), sharedData.syncListItems.getListId());
    variables.put(Typename.TIMESTAMP.get(), sharedData.syncListItems.getEditedProductItems()[0].getTimestamp());
    variables.put(Typename.ID.get(), sharedData.syncListItems.getEditedProductItems()[0].getId());
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    Assert.assertEquals("ListId is not matching", syncListResponse.getData().getSyncListItems().getListId(), sharedData.syncListResponse.getData().getSyncLists().getCreatedLists()[0].getId());
    Assert.assertEquals("List Product id is not matching", syncListResponse.getData().getSyncListItems().getDeletedItems()[0].getId(), sharedData.syncListItems.getEditedProductItems()[0].getId());
  }

  @When("I check pastshops event list is loaded for page number as {int} and pagesize as {int} for user")
  public void iCheckPastshopsEventListIsLoadedForPageNumberAsAndPagesizeAsForUser(int pageNumber, int pageSize) throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/pastShops.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.PAGE_NUMBER.get(), pageNumber);
    variables.put(Typename.PAGE_SIZE.get(), pageSize);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    sharedData.syncListResponse = syncListResponse;
    sharedData.pageSize = pageSize;
  }

  @Then("I verify past shopping api responds with right response")
  public void iVerifyPastShoppingApiRespondsWithRightResponse() {
    Assert.assertTrue("List of past shops is empty", sharedData.syncListResponse.getData().getPastshopEvent().getItems().size() >= sharedData.pageSize);
    Assert.assertTrue("count of returned list and total item count field should be same", sharedData.syncListResponse.getData().getPastshopEvent().getTotalItemCount() > 600);
    Assert.assertEquals("Next Page should be 2", sharedData.syncListResponse.getData().getPastshopEvent().getNextPage(), 2);
  }

  @And("I check the purchase History with pastShops items for page number as {int} and pagesize as {int} and for store {string}")
  public void iCheckThePurchaseHistoryWithPastShopsItemsForPageNumberAsAndPagesizeAsAndForStore(int pageNumber, int pageSize, String store) throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/pastShopsItems.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.PAGE_NUMBER.get(), pageNumber);
    variables.put(Typename.PAGE_SIZE.get(), pageSize);
    if (!store.equalsIgnoreCase(""))
      variables.put(Typename.STORE.get(), store);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(getListResponse, SyncListResponse.class);
    Assert.assertEquals("List of product past shops is empty", syncListResponse.getData().getPurchaseHistory().getProducts().size(), pageSize);
    Assert.assertTrue("count of returned list and total item count field should be same", syncListResponse.getData().getPurchaseHistory().getTotalNumberOfProducts() > 100);
    Assert.assertEquals("Next Page should be 2", syncListResponse.getData().getPurchaseHistory().getNextPage(), 2);
    for (int i = 0; i >= syncListResponse.getData().getPurchaseHistory().getProducts().size(); i++) {
      if (!store.equalsIgnoreCase(""))
        Assert.assertFalse("store details not available for instore mode", syncListResponse.getData().getPurchaseHistory().getProducts().get(i).getInStoreDetails().equals(null));
      else
        Assert.assertTrue("store details available for online mode", syncListResponse.getData().getPurchaseHistory().getProducts().get(i).getInStoreDetails().equals(null));
    }
  }

  @And("I check the purchase History with pastShops items for page number as {int} and pagesize as {int} and online mode")
  public void iCheckThePurchaseHistoryWithPastShopsItemsForPageNumberAsAndPagesizeAsAndOnlineMode(int pageNumber, int pageSize) throws IOException {
    iCheckThePurchaseHistoryWithPastShopsItemsForPageNumberAsAndPagesizeAsAndForStore(pageNumber, pageSize, "");
  }

  @When("I search product with {string} in instore mode {string} to get Aisle information")
  public void iSearchProductWithInInstoreMode(String productId, String stroeId) throws IOException {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/ProductByProductID.graphql");
    String[] productIds = productId.split(",");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    ArrayNode productIdsArray = variables.putArray(Typename.PRODUCT_IDS.get());
    for (String product : productIds) {
      productIdsArray.add(product);
    }
    variables.put(Typename.STORE_ID.get(), stroeId);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String response = graphqlHelper.postGraphqlQuery(graphqlQuery);
    SyncListResponse syncListResponse = mapper.readValue(response, SyncListResponse.class);

    for (int i = 0; i < productIds.length; i++) {
      if (!(syncListResponse.getData().getProductsByProductIds().getProducts()[i].getInStoreDetails() == null)) {
        Assert.assertTrue(!syncListResponse.getData().getProductsByProductIds().getProducts()[i].getInStoreDetails().getLocationType().equals(null));
      }
      Assert.assertEquals("Product Id is not matching", syncListResponse.getData().getProductsByProductIds().getProducts()[i].getProductId(), productIds[i]);
    }
  }

  @When("I search any product with {string} in online mode with no Aisle information")
  public void iSearchAnyProductWithInOnlineLmode(String productId) throws IOException {
    iSearchProductWithInInstoreMode(productId, "");
  }
}
