package au.com.woolworths.stepdefinitions.iris;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import io.cucumber.java.en.Then;

import java.io.InputStream;
import java.util.logging.Logger;

public class HomepagePromoTileDefinition extends BaseHelper {

  private final static Logger logger = Logger.getLogger("HomepagePromoTileDefinition.class");
  private GraphqlHelper graphqlHelper = new GraphqlHelper();

  @Then("^I search for the ProductGroup with id \"([^\"]*)\" and validate the response$")
  public void verifyProductGroupGraphQL(String groupId) throws Throwable {
    InputStream iStream = HomepagePromoTileDefinition.class.getResourceAsStream("/gqlQueries/iris/productsByProductGroup.graphql");
    variables.put("groupId", groupId);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    graphqlHelper.postGraphqlQuery(graphqlQuery);
    //Todo: Return ProductsByProductGroupResponse as java object and add assertions when automating actual tests
  }

}
