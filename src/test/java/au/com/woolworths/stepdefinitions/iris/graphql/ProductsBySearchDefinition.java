package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.model.iris.graphql.productList.Product;
import au.com.woolworths.model.iris.graphql.productList.ProductsBySearchResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.When;
import au.com.woolworths.graphql.parser.GraphqlParser;

import static au.com.woolworths.helpers.iris.graphql.GraphqlHelper.*;
import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.*;

import java.io.InputStream;
import java.util.List;

public class ProductsBySearchDefinition extends BaseHelper {
  InputStream iStream = ChangeMyOrderDefinition.class.getResourceAsStream("/gqlQueries/iris/productsBySearch.graphql");
  ObjectMapper mapper = new ObjectMapper();
  public GraphqlHelper graphqlHelper = new GraphqlHelper();

  @When("user requests for online \"([^\"]*)\" products by search$")
  public void getAvailableProductsFromOnlineProductsBySearch(String searchTerm) throws Throwable {
    variables.put(ProductsBySearchArgs.SEARCH_TERM.get(), searchTerm);
    variables.put(ProductsBySearchArgs.PAGE_SIZE.get(), ProductListPageSize.DEFAULT_PRODUCT_LIST_PAGE_SIZE.get());
    String productsBySearchQuery = GraphqlParser.parseGraphql(iStream, variables);
    String productsBySearchResponseString = graphqlHelper.postGraphqlQuery(productsBySearchQuery);
    ProductsBySearchResponse productsBySearchResponse = mapper.readValue(productsBySearchResponseString, ProductsBySearchResponse.class);
    List<Product> productList = productsBySearchResponse.getData().getProductsBySearch().getProducts();
    if (productList.size() == 0) {
      sharedData.productIdSource = ProductIdSource.STORED;
    } else {
      sharedData.productIdSource = ProductIdSource.RANDOM;
      sharedData.availableProducts = getAvailableProducts(productList);
    }
  }

  @When("user requests for instore \"([^\"]*)\" products by search for store \"([^\"]*)\"$")
  public void getAvailableProductsFromInstoreProductsBySearch(String searchTerm, String storeId) throws Throwable {
    variables.put(ProductsBySearchArgs.SEARCH_TERM.get(), searchTerm);
    variables.put(ProductsBySearchArgs.STORE_ID.get(), storeId);
    variables.put(ProductsBySearchArgs.PAGE_SIZE.get(), ProductListPageSize.DEFAULT_PRODUCT_LIST_PAGE_SIZE.get());
    String productsBySearchQuery = GraphqlParser.parseGraphql(iStream, variables);
    String productsBySearchResponseString = graphqlHelper.postGraphqlQuery(productsBySearchQuery);
    ProductsBySearchResponse productsBySearchResponse = mapper.readValue(productsBySearchResponseString, ProductsBySearchResponse.class);
    List<Product> productList = productsBySearchResponse.getData().getProductsBySearch().getProducts();
    if (productList.isEmpty()) {
      sharedData.productIdSource = ProductIdSource.STORED;
    } else {
      sharedData.productIdSource = ProductIdSource.RANDOM;
      sharedData.availableProducts = getAvailableProducts(productList);
    }
    sharedData.inStoreId = storeId;
  }

}