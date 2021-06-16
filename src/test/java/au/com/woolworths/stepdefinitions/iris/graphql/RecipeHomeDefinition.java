package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.model.iris.graphql.recipeHome.RecipeHome;
import au.com.woolworths.model.iris.graphql.recipeHome.RecipeHomeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.io.InputStream;
import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class RecipeHomeDefinition extends BaseHelper {
  ObjectMapper mapper = new ObjectMapper();
  private final GraphqlHelper graphqlHelper = new GraphqlHelper();
  private final ObjectNode variables = new ObjectMapper().createObjectNode();
  private RecipeHome response;

  @And("user requests for recipe home")
  public void userRequestsForRecipeHome() throws Throwable {

    // PREPARE - GraphQL query
    InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/recipeHome.graphql");

    String recipeHomeQuery = parseGraphql(iStream, variables);

    // CALL - GraphQL endpoint
    String recipeHomeResponseString = graphqlHelper.postGraphqlQuery(recipeHomeQuery);

    // PARSE

    response = mapper.readValue(recipeHomeResponseString, RecipeHomeResponse.class).getData().getRecipeHome();
  }

  @Then("user is able to see recipe home")
  public void userIsAbleToSeeRecipeHome() {

    // ASSERT Recipe Group Tile
    assertEquals("RecipeGroupTile", response.getItems().get(0).getItems().get(0).get__typename());
    assertEquals("Dinner", response.getItems().get(0).getItems().get(0).getTitle());
    assertEquals("[Dinner]", response.getItems().get(0).getItems().get(0).getTags().toString());
    assertEquals("JO", response.getItems().get(0).getItems().get(1).getSource());
    assertNotNull(response.getItems().get(0).getItems().get(0).getTags());
    assertNotNull(response.getItems().get(0).getItems().get(0).getImage());

    // ASSERT Fresh Mag
    assertEquals("FreshMagTile", response.getItems().get(1).get__typename());
    assertEquals("Explore Fresh Ideas Magazine", response.getItems().get(1).getAltText());
    assertNotNull(response.getItems().get(1).getTextImageUrl());
    assertNotNull(response.getItems().get(1).getCoverImageUrl());

    // ASSERT HorizontalList
    assertEquals("HorizontalList", response.getItems().get(2).get__typename());
    assertEquals("Fresh Ideas", response.getItems().get(2).getTitle());
    assertEquals("See All", response.getItems().get(2).getActionTitle());
    assertEquals("FI", response.getItems().get(2).getActionMetadata().get(0).getSource());

    // ASSERT Recipe Summary Card
    assertEquals("RecipeSummaryCard", response.getItems().get(2).getItems().get(0).get__typename());
    assertNotNull(response.getItems().get(2).getItems().get(0).getTitle());
    assertNotNull(response.getItems().get(2).getItems().get(0).getImage());
    assertNotNull(response.getItems().get(2).getItems().get(0).getId());
  }
}