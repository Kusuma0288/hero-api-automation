package au.com.woolworths.stepdefinitions.iris;

import java.io.InputStream;
import java.util.logging.Logger;

import cucumber.api.java.en.Then;
import com.fasterxml.jackson.databind.ObjectMapper;
import au.com.woolworths.graphql.parser.GraphqlParser;
import com.fasterxml.jackson.databind.node.ObjectNode;
import au.com.woolworths.helpers.iris.graphql.GraphqlQueryHelper;

public class HomepagePromoTileDefinition {

  private final static Logger logger = Logger.getLogger("HomepagePromoTileDefinition.class");
  private GraphqlQueryHelper graphqlQueryHelper = new GraphqlQueryHelper();

  @Then("^I search for the ProductGroup with id \"([^\"]*)\" and validate the response$")
  public void verifyProductGroupGraphQL(String groupId) throws Throwable {
    InputStream iStream = HomepagePromoTileDefinition.class.getResourceAsStream("/gqlQueries/iris/productsByProductGroup.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put("groupId", groupId);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    graphqlQueryHelper.postGraphqlQuery(graphqlQuery);
    //Todo: Return ProductsByProductGroupResponse as java object and add assertions when automating actual tests
  }

}
