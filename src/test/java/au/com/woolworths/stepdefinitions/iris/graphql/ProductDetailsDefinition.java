package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.graphql.parser.GraphqlParser;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.model.iris.graphql.productDetails.ProductDetailsResponse;
import au.com.woolworths.utils.TestProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.io.InputStream;

import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.*;
import static au.com.woolworths.helpers.iris.graphql.ProductDetailsResponseHelper.*;

public class ProductDetailsDefinition extends BaseHelper {
  InputStream iStream = ChangeMyOrderDefinition.class.getResourceAsStream("/gqlQueries/iris/productDetails.graphql");
  ObjectMapper mapper = new ObjectMapper();
  public GraphqlHelper graphqlHelper = new GraphqlHelper();


  @And("user selects a product to get \"([^\"]*)\" product details$")
  public void selectProductToGetProductDetails(String mode) {
    if (sharedData.productIdSource == ProductIdSource.RANDOM) {
      sharedData.availableProduct = sharedData.availableProducts.get(0);
      sharedData.availableProductId = sharedData.availableProducts.get(0).getProductId();
    } else {
      sharedData.availableProductId = TestProperties.get(mode + "_PRODUCT_ID");
    }
  }

  @Then("online product details are available$")
  public void verifyOnlineProductDetailsResponse() throws Throwable {
    variables.put(ProductDetailsArgs.PRODUCT_ID.get(), sharedData.availableProductId);
    String productDetailsQuery = GraphqlParser.parseGraphql(iStream, variables);
    String productDetailsResponseString = graphqlHelper.postGraphqlQuery(productDetailsQuery);
    ProductDetailsResponse productDetailsResponse = mapper.readValue(productDetailsResponseString, ProductDetailsResponse.class);
    assertProductDetails(productDetailsResponse);
  }

  @Then("instore product details are available$")
  public void verifyInStoreProductDetailsResponse() throws Throwable {
    variables.put(ProductDetailsArgs.PRODUCT_ID.get(), sharedData.availableProductId);
    variables.put(ProductDetailsArgs.STORE_ID.get(), sharedData.inStoreId);
    String productDetailsQuery = GraphqlParser.parseGraphql(iStream, variables);
    String productDetailsResponseString = graphqlHelper.postGraphqlQuery(productDetailsQuery);
    ProductDetailsResponse productDetailsResponse = mapper.readValue(productDetailsResponseString, ProductDetailsResponse.class);
    assertProductDetails(productDetailsResponse);
  }
}