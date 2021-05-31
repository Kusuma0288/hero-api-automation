package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.model.iris.graphql.recipeSearch.RecipeSearch;
import au.com.woolworths.model.iris.graphql.recipeSearch.RecipeSearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.InputStream;

import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.helpers.iris.graphql.RecipesSearchResponseHelper.RecipeSearchArgs.SOURCE;
import static au.com.woolworths.helpers.iris.graphql.RecipesSearchResponseHelper.RecipeSearchArgs.TAGS;
import static org.testng.AssertJUnit.assertNotNull;

public class RecipeSearchDefinition extends BaseHelper {
  ObjectMapper mapper = new ObjectMapper();
  private final GraphqlHelper graphqlHelper = new GraphqlHelper();
  private final ObjectNode variables = new ObjectMapper().createObjectNode();
  private RecipeSearch response;

  @When("user search for source: {string} and tag: {string}")
  public void userSearchForSourceAndTag(String source, String tags) throws Throwable {

    // PREPARE - GraphQL query
    InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/recipeSearch.graphql");
    variables.put(SOURCE.get(), source);
    variables.put(TAGS.get(), tags);
    String recipeSearchQuery = parseGraphql(iStream, variables);

    // CALL - GraphQL endpoint
    String recipeSearchResponseString = graphqlHelper.postGraphqlQuery(recipeSearchQuery);

    // PARSE
    response = mapper.readValue(recipeSearchResponseString, RecipeSearchResponse.class).getData().getRecipeSearch();
  }

  @Then("user can see the recipe search results")
  public void userCanSeeTheRecipeSearchResults () {

    // ASSERT
    assertNotNull(response.getTotalNumberOfRecipes());
    assertNotNull(response.getNextPage());
    assertNotNull(response.getRecipes().get(0).getTitle());
    assertNotNull(response.getRecipes().get(0).getId());
  }

}