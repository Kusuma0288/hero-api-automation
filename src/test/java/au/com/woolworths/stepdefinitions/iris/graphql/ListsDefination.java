package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.helpers.iris.graphql.ListHelper;
import au.com.woolworths.model.iris.graphql.list.CreateListResponse;
import au.com.woolworths.stepdefinitions.apigee.ListsDefinition;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class ListsDefination extends ListHelper {
  public GraphqlHelper graphqlHelper = new GraphqlHelper();

  ObjectMapper mapper = new ObjectMapper();


  @And("^I create a list with list name \"([^\"]*)\" and color \"([^\"]*)\" and added FreeText \"([^\"]*)\" and Product \"([^\"]*)\" \"([^\"]*)\"$")
  public void iCreateAListWithListNameAndFromGraphqlAndAddedFreeTextAndProduct(String listName, String color, String freeText, long product, int quantity) throws IOException {
    listName = listName + Utilities.getSaltString();
    Random rand = new Random();
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/createList.graphql");

    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.LIST_NAME.get(), listName);
    variables.put(Typename.COLOR.get(), color);
    variables.put(Typename.REFERENCE_ID.get(), rand.nextInt());
    variables.put(Typename.FREE_TEXT.get(), freeText);
    variables.put(Typename.PRODUCT_ID.get(), product);
    variables.put(Typename.QUANTITY.get(), quantity);
    variables.put(Typename.CHECKED.get(), false);
    sharedData.listName = listName;
    sharedData.currentFreeTextId = freeText;
    sharedData.productId = product;
    sharedData.quantity = quantity;

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    CreateListResponse changeMyOrderResponse = mapper.readValue(getListResponse, CreateListResponse.class);
    sharedData.listResponse = changeMyOrderResponse;
  }

  @Then("^I verify list is created with correct details$")
  public void iVerifyListIsCreatedWithCorrectDetails() {
    Assert.assertEquals("ListName is not matching", sharedData.listResponse.getData().getCreateList().getTitle(), sharedData.listName);
    Assert.assertEquals("freeText in list is not matching", sharedData.listResponse.getData().getCreateList().getFreeTextItems()[0].getText(), sharedData.currentFreeTextId);
    Assert.assertEquals("productId is not matching", sharedData.listResponse.getData().getCreateList().getProductItems()[0].getProductId(), sharedData.productId);
    Assert.assertEquals("productId is not matching", sharedData.listResponse.getData().getCreateList().getProductItems()[0].getQuantity(), sharedData.quantity);
  }

  @When("^I edit a created list to list name \"([^\"]*)\" with color \"([^\"]*)\"$")
  public void iEditAListWithListNameToWithColor(String listNameNew, String color) throws IOException {
    listNameNew = listNameNew + Utilities.getSaltString();
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/EditList.graphql");

    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.ID.get(), sharedData.listResponse.getData().getCreateList().getId());
    variables.put(Typename.TIMESTAMP.get(), sharedData.listResponse.getData().getCreateList().getTimestamp());
    variables.put(Typename.LAST_SYNC.get(), sharedData.listResponse.getData().getCreateList().getTimestamp());
    variables.put(Typename.LIST_NAME.get(), listNameNew);
    variables.put(Typename.COLOR.get(), color);
    sharedData.listName = listNameNew;

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    CreateListResponse changeMyOrderResponse = mapper.readValue(getListResponse, CreateListResponse.class);
    sharedData.listResponseEdited = changeMyOrderResponse;
  }

  @Then("^I verify list is Edited with correct details$")
  public void iVerifyListIsEditedWithCorrectDetails() {
    Assert.assertEquals("ListName is not matching", sharedData.listResponseEdited.getData().getEditList().getTitle(), sharedData.listName);
  }
  @And("^I delete the newly created and edited list$")
  public void iDeleteTheNewlyCreatedAndEditedList() throws Throwable {
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/DeleteList.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put(Typename.ID.get(), sharedData.listResponse.getData().getCreateList().getId());

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    CreateListResponse changeMyOrderResponse = mapper.readValue(getListResponse, CreateListResponse.class);
    Assert.assertEquals("ListName is not matching", sharedData.listResponse.getData().getCreateList().getId(), changeMyOrderResponse.getData().getDeleteList());
  }
}
