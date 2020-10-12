package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.iris.graphql.GraphqlQueryHelper;
import au.com.woolworths.model.iris.graphql.productDetails.Feed;
import au.com.woolworths.model.iris.graphql.productDetails.ProductDetailsResponse;
import au.com.woolworths.utils.SharedData;
import au.com.woolworths.utils.TestProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import au.com.woolworths.helpers.iris.graphql.ProductListResponseHelper.*;
import org.junit.Assert;

import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Logger;

public class ProductDetailsDefinition {

  private final static Logger logger = Logger.getLogger("ProductDetailsDefinition.class");
  private GraphqlQueryHelper queryHelper = new GraphqlQueryHelper();
  protected SharedData sharedData = ApplicationContext.getSharedData();
  protected ObjectMapper mapper = new ObjectMapper();

  @And("user selects a \"([^\"]*)\" product to get online product details$")
  public void selectProductToGetOnlineProductDetails(ProductIdSource productIdSource) throws Throwable {
    if (productIdSource == ProductIdSource.RANDOM) {
      sharedData.availableProduct = sharedData.availableProducts.get(0);
      sharedData.availableProductId = sharedData.availableProducts.get(0).getProductId();
    } else {
      sharedData.availableProductId = TestProperties.get("ONLINE_PRODUCT_ID");
    }
  }

  @Then("online product details for the available product are satisfactory for user display$")
  public void verifyOnlineProductDetailsResponse() throws Throwable {
    InputStream iStream = ProductDetailsDefinition.class.getResourceAsStream("/gqlQueries/iris/productDetails.graphql");
    ObjectNode variables = new ObjectMapper().createObjectNode();
    variables.put("productId", sharedData.availableProductId);
    String productDetailsQuery = GraphqlParser.parseGraphql(iStream, variables);
    String productDetailsResponseString = queryHelper.postGraphqlQuery(productDetailsQuery);
    ProductDetailsResponse productDetailsResponse = mapper.readValue(productDetailsResponseString, ProductDetailsResponse.class);

    Optional<Feed> productCard = productDetailsResponse.getData().getProductDetails()
        .getFeed()
        .stream()
        .filter(feed -> feed.getProductId() != null).findFirst();
    Assert.assertNotNull(productCard.get().getPromotionValue());
    Assert.assertNotNull(productCard.get().getName());
    Assert.assertNotNull(productCard.get().getPrice());
    Assert.assertNotNull(productCard.get().getWasPrice());
  }
}
