package au.com.woolworths.stepdefinitions.iris.graphql;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.model.iris.graphql.productCategories.Categories;
import au.com.woolworths.model.iris.graphql.productCategories.ProductCategoriesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.InputStream;
import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.helpers.iris.graphql.ProductCategoriesResponseHelper.ProductsByCategoriesArgs.*;
import static junit.framework.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;
public class ProductCategoriesDefinition {
  private final ObjectMapper mapper = new ObjectMapper();
  private final GraphqlHelper graphqlHelper = new GraphqlHelper();
  private final ObjectNode variables = new ObjectMapper().createObjectNode();
  private String subcategory = "";
  private boolean isFinalLevel = false;
  @When("^user requests for \"([^\"]*)\" subcategories for store: \"([^\"]*)\"$")
  public void getAvailableProductCategories(String categoryType, String storeId) throws Throwable {
    // PREPARE - GraphQL query
    InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productCategories.graphql");
    variables.put(CATEGORY_ID.get(), subcategory);
    variables.put(CATEGORIES_TYPE.get(), categoryType);
    variables.put(STORE_ID.get(), storeId);
    String productsByCategoriesQuery = parseGraphql(iStream, variables);
    // CALL - GraphQL endpoint
    String productsByCategoriesResponseString = graphqlHelper.postGraphqlQuery(productsByCategoriesQuery);
    // PARSE
    Categories response = mapper.readValue(productsByCategoriesResponseString, ProductCategoriesResponse.class).getData().getProductCategories().getCategories().get(0);
    // ASSERT
    assertNotNull(response.getTitle());
    assertNotNull(response.getIsFinalLevel());
    assertNotNull(response.getCategoryId());
    // SAVE - for future calls
    subcategory = response.getCategoryId();
    isFinalLevel = response.getIsFinalLevel();
  }
  @Then("^user reaches the final subcategory$")
  public void userReachesTheFinalSubcategory() {
    assertTrue(isFinalLevel);
  }
  @Then("^user has not yet reached the final subcategory$")
  public void userHasNotYetReachedTheFinalSubcategory() {
    assertFalse(isFinalLevel);
  }
}