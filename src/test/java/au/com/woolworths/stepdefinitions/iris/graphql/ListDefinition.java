package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.helpers.iris.graphql.ListHelper;
import au.com.woolworths.model.iris.graphql.list.SyncListResponse;
import au.com.woolworths.stepdefinitions.apigee.ListsDefinition;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import java.io.IOException;
import java.io.InputStream;
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
    InputStream iStream = ListsDefinition.class.getResourceAsStream("/gqlQueries/iris/getListOfists.graphql");

    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String getListResponse = graphqlHelper.postGraphqlQuery(graphqlQuery);
    GetListOfListsResponse getListOfListsResponse = mapper.readValue(getListResponse, GetListOfListsResponse.class);
    sharedData.getListOfListsResponse = getListOfListsResponse;
  }

  @Then("I verify the lists with correct details {string} {string}")
  public void iVerifyTheListsWithCorrectDetails(String listName1, String listName2) {
    listName1 = listName1 + Utilities.getSaltString();
    listName2 = listName2 + Utilities.getSaltString();
    Assert.assertEquals("ListName1 is not matching", sharedData.getListOfListsResponse.getData().getEditList().getTitle(), sharedData.listName);
  }
}
