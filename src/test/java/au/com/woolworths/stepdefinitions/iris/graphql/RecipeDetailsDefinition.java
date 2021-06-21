package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.model.iris.graphql.recipeDetails.RecipeDetails;
import au.com.woolworths.model.iris.graphql.recipeDetails.RecipeDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.io.InputStream;

import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.helpers.iris.graphql.RecipeDetailsResponseHelper.RecipeDetailsArgs.RECIPE_ID;
import static junit.framework.Assert.*;

public class RecipeDetailsDefinition {
  private final ObjectMapper mapper = new ObjectMapper();
  private final GraphqlHelper graphqlHelper = new GraphqlHelper();
  private final ObjectNode variables = new ObjectMapper().createObjectNode();
  private RecipeDetails response;
  private String recipeID;

  @And("user requests for {string} recipe details")
    public void userRequestsForRecipeDetails(String recipeId) throws Throwable {

    // PREPARE - GraphQL query
    recipeID = recipeId;
    InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/recipeDetails.graphql");
    variables.put(RECIPE_ID.get(), recipeId);
    String recipeDetailsQuery = parseGraphql(iStream, variables);

    // CALL - GraphQL endpoint
    String recipeDetailsResponseString = graphqlHelper.postGraphqlQuery(recipeDetailsQuery);

    // PARSE
    RecipeDetailsResponse recipeDetailsResponse = mapper.readValue(recipeDetailsResponseString, RecipeDetailsResponse.class);
    response = mapper.readValue(recipeDetailsResponseString, RecipeDetailsResponse.class).getData().getRecipeDetails();
  }

  @Then("user is able to see recipe details")
  public void userIsAbleToSeeRecipeDetails() {
    // ASSERT
    assertNotNull(response.getRecipeId());
    assertNotNull(response.getRecipeUrl());
    assertNotNull(response.getTitle());
    assertNotNull(response.getImage());
    assertNotNull(response.getIngredients());
    assertTrue(response.getRecipeUrl().contains(recipeID));
  }
}