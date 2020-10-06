package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.helpers.iris.graphql.ProductListResponseHelper;
import au.com.woolworths.model.iris.graphql.productList.Product;
import au.com.woolworths.model.iris.graphql.productList.ProductsBySearchResponse;
import au.com.woolworths.utils.SharedData;
import cucumber.api.java.en.When;
import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.iris.graphql.GraphqlQueryHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import static au.com.woolworths.helpers.iris.graphql.GraphqlQueryHelper.*;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ProductsBySearchDefinition {
  private final static Logger logger = Logger.getLogger("ProductsBySearchDefinition.class");
  private GraphqlQueryHelper queryHelper = new GraphqlQueryHelper();
  protected SharedData sharedData = ApplicationContext.getSharedData();
  protected ObjectMapper mapper = new ObjectMapper();

  @When("user requests for online \"([^\"]*)\" products by search$")
  public void getAvailableProductsFromOnlineProductsBySearch(String searchTerm) throws Throwable {
    InputStream iStream = ProductsBySearchDefinition.class.getResourceAsStream("/gqlQueries/iris/productsBySearch.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put("searchTerm", searchTerm);
    variables.put("mode", String.valueOf((Mode.ONLINE)));
    variables.put("pageSize", queryHelper.defaultProductListPageSize);
    String productsBySearchQuery = GraphqlParser.parseGraphql(iStream, variables);
    String productsBySearchResponseString = queryHelper.postGraphqlQuery(productsBySearchQuery);
    ProductsBySearchResponse productsBySearchResponse = mapper.readValue(productsBySearchResponseString, ProductsBySearchResponse.class);
    List<Product> productList = productsBySearchResponse.getData().getProductsBySearch().getProducts();
    sharedData.availableProducts = ProductListResponseHelper.getAvailableProducts(productList);
  }

}