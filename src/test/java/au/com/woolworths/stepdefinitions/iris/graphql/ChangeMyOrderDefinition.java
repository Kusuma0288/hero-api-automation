package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.model.iris.graphql.changeMyOrder.ChangeMyOrderResponse;
import au.com.woolworths.utils.SharedData;
import cucumber.api.java.en.Then;
import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.InputStream;
import java.util.logging.Logger;
public class ChangeMyOrderDefinition {

  private final static Logger logger = Logger.getLogger("ProductDetailsDefinition.class");
  private GraphqlHelper graphqlHelper = new GraphqlHelper();
  protected SharedData sharedData = ApplicationContext.getSharedData();
  protected ObjectMapper mapper = new ObjectMapper();

  @Then("^I check my order details in order summary page$")
  public void verifyCMOGraphQL() throws Throwable {

    InputStream iStream = ChangeMyOrderDefinition.class.getResourceAsStream("/gqlQueries/iris/changeMyOrder.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put("orderId", sharedData.orderId);
    String graphqlQuery = GraphqlParser.parseGraphql(iStream, variables);
    String changeMyOrderResponseString = graphqlHelper.postGraphqlQuery(graphqlQuery);
    ChangeMyOrderResponse changeMyOrderResponse = mapper.readValue(changeMyOrderResponseString, ChangeMyOrderResponse.class);

  }

}
