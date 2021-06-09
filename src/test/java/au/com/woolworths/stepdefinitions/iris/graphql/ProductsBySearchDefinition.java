package au.com.woolworths.stepdefinitions.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.helpers.iris.graphql.GraphqlHelper;
import au.com.woolworths.helpers.iris.graphql.ListHelper;
import au.com.woolworths.helpers.iris.graphql.SearchHelper;
import au.com.woolworths.model.iris.graphql.productList.Product;
import au.com.woolworths.model.iris.graphql.productList.ProductsBySearch;
import au.com.woolworths.model.iris.graphql.productList.ProductsBySearchResponse;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import java.io.InputStream;
import java.util.*;

import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.helpers.iris.graphql.GraphqlHelper.ProductListPageSize.DEFAULT_PRODUCT_LIST_PAGE_SIZE;
import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.ProductIdSource.RANDOM;
import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.ProductIdSource.STORED;
import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.ProductsBySearchArgs.*;
import static au.com.woolworths.helpers.iris.graphql.ProductsBySearchResponseHelper.getAvailableProducts;
import static au.com.woolworths.stepdefinitions.common.ServiceHooks.restInvocationUtil;
import static au.com.woolworths.utils.URLResources.HERMES_V1_GRAPHQL;

public class ProductsBySearchDefinition extends BaseHelper {
  private InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productsBySearch.graphql");
  private final GraphqlHelper graphqlHelper = new GraphqlHelper();
  private final ArrayList<String> tempProducts = new ArrayList<>();
  private Integer totalProductCount;
  private ProductsBySearch before;
  private ProductsBySearch after;

  @When("user requests for online {string} products by search")
  public void getAvailableProductsFromOnlineProductsBySearch(String searchTerm) throws Throwable {
    variables.put(SEARCH_TERM.get(), searchTerm);
    variables.put(PAGE_SIZE.get(), DEFAULT_PRODUCT_LIST_PAGE_SIZE.get());
    String productsBySearchQuery = parseGraphql(iStream, variables);
    String productsBySearchResponseString = graphqlHelper.postGraphqlQuery(productsBySearchQuery);
    ProductsBySearchResponse productsBySearchResponse = mapper.readValue(productsBySearchResponseString, ProductsBySearchResponse.class);
    List<Product> productList = productsBySearchResponse.getData().getProductsBySearch().getProducts();
    if (productList.size() == 0) {
      sharedData.productIdSource = STORED;
    } else {
      sharedData.productIdSource = RANDOM;
      sharedData.availableProducts = getAvailableProducts(productList);
    }
  }

  @When("user requests for instore {string} products by search for store {string}")
  public void getAvailableProductsFromInstoreProductsBySearch(String searchTerm, String storeId) throws Throwable {
    variables.put(SEARCH_TERM.get(), searchTerm);
    variables.put(STORE_ID.get(), storeId);
    variables.put(PAGE_SIZE.get(), DEFAULT_PRODUCT_LIST_PAGE_SIZE.get());
    String productsBySearchQuery = parseGraphql(iStream, variables);
    String productsBySearchResponseString = graphqlHelper.postGraphqlQuery(productsBySearchQuery);
    ProductsBySearchResponse productsBySearchResponse = mapper.readValue(productsBySearchResponseString, ProductsBySearchResponse.class);
    List<Product> productList = productsBySearchResponse.getData().getProductsBySearch().getProducts();
    if (productList.isEmpty()) {
      sharedData.productIdSource = STORED;
    } else {
      sharedData.productIdSource = RANDOM;
      sharedData.availableProducts = getAvailableProducts(productList);
    }
    sharedData.inStoreId = storeId;
  }

  @When("user searches for online {string} products {int} at a time and scrolls to the end of the results")
  public void userSearchesForOnlineProductsAndScrollsToTheEndOfTheResults(String searchString, int pageSize) throws Throwable {

    // ENDPOINT
    String endPoint = HERMES_V1_GRAPHQL;

    // Nullable variable of next available page
    Integer nextPage = 1;

    // To display current product count
    Integer productCount = 0;

    // LOOP - while another page of products exists
    do {
      // REQUEST - create GraphQL
      iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productsBySearch.graphql"); // need to re-instantiate each time
      ObjectNode variables = mapper.createObjectNode()
              .put("searchTerm", searchString)
              .put("pageSize", pageSize)
              .put("pageNumber", nextPage);
      String graphqlQuery = parseGraphql(iStream, variables);

      // CALL - any errors caught within
      Map<String, String> response = restInvocationUtil.makeHttpRequest(endPoint, graphqlQuery, sharedData.accessToken);

      // PARSE
      ProductsBySearchResponse productsBySearchResponse = mapper.readValue(response.get("response"), ProductsBySearchResponse.class);

      // RECORD
      totalProductCount = productsBySearchResponse.getData().getProductsBySearch().getTotalNumberOfProducts();

      // Get products from response
      List<Product> productList = productsBySearchResponse.getData().getProductsBySearch().getProducts();

      // LOOP - to save productIds
      for (Product product : productList) {
        // DEBUG
        // System.out.println(nextPage + "::" + productCount + ": " + product.getProductId() + " " + (!product.getName().isEmpty() ? product.getName() : "No name"));
        tempProducts.add(product.getProductId());
        productCount++;
      }

      nextPage = productsBySearchResponse.getData().getProductsBySearch().getNextPage();
    } while (nextPage != null);
  }

  @Then("no duplicate results are returned")
  public void noDuplicateResultsAreReturned() {
    Set<String> s = new HashSet<>();
    for (String p : tempProducts) {
      softAssert.assertTrue(s.add(p), p + " is duplicated!");
    }
  }

  @And("the product total count matches the actual number of products returned")
  public void theProductTotalCountMatchesTheActualNumberOfProductsReturned() {
    softAssert.assertEquals(totalProductCount.intValue(), tempProducts.size(), "User was told " + totalProductCount + " items would be displayed but " + tempProducts.size() + " were.");
  }

  /*
   NOTE: This query requests a response from Adobe Target so the search keyword "Coke" must return any Google ad card
   */
  @When("user searches for {string} with productsFeed {string}")
  public void userSearchesForWithProductsFeed(String searchString, String productsFeed) throws Throwable {
    // ENDPOINT
    String endPoint = HERMES_V1_GRAPHQL;

    // REQUEST
    iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productsBySearchProductsFeed.graphql");
    ObjectNode variables = mapper.createObjectNode()
            .put("searchTerm", searchString)
            .put("productsFeed", productsFeed.equals("true"));
    String graphqlQuery = parseGraphql(iStream, variables);

    // CALL - any errors caught within
    Map<String, String> response = restInvocationUtil.makeHttpRequest(endPoint, graphqlQuery, sharedData.accessToken);

    // PARSE & SAVE
    ProductsBySearch result = mapper.readValue(response.get("response"), ProductsBySearchResponse.class).getData().getProductsBySearch();
    if (!productsFeed.equals("true")) {
      before = result;
    } else {
      after = result;
    }
  }

  @When("user searches for {string} as the search product with sort option {string} and filter by {string} and by brand {string}")
  public void userSearchesForAsTheSearchProduct(String searchString, String sortOption, String filterType, String value) throws Throwable {
    // REQUEST
    iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productsBySearchProductsFilters.graphql");
    String[] values = value.split(",");
    ObjectNode variables = mapper.createObjectNode();
    variables.put(SearchHelper.Typename.SEARCH_TERM.get(), searchString);
    variables.put(SearchHelper.Typename.SORT_OPTION.get(), sortOption);
    variables.put(SearchHelper.Typename.PRODUCTS_FEED.get(), false);
    variables.put(SearchHelper.Typename.FILTER_OPTIONS_TYPE.get(), filterType);
    ArrayNode productIdsArray = variables.putArray(SearchHelper.Typename.FILTER_OPTIONS_VALUES.get());
    for (String valu : values) {
      productIdsArray.add(valu);
    }
    String graphqlQuery = parseGraphql(iStream, variables);

    // CALL - any errors caught within
    Map<String, String> response = restInvocationUtil.makeHttpRequest(HERMES_V1_GRAPHQL, graphqlQuery, sharedData.accessToken);

    // PARSE & SAVE
    boolean productNotMatching = false;
    ProductsBySearch result = mapper.readValue(response.get("response"), ProductsBySearchResponse.class).getData().getProductsBySearch();
    for(int i = 0; i <=result.getFilters().size(); i++) {
      if(result.getFilters().get(i).getHeaderTitle().equals(filterType))
      {
        for(int j =0; j <=result.getFilters().get(i).getFilterItems().size(); j++) {
          if (result.getFilters().get(i).getFilterItems().get(j).getIsApplied().equals(true)) {
            productNotMatching = true;
            break;
          }
        }
      }
      if(productNotMatching)
      {
        break;
      }
    }
    Assert.assertTrue(productNotMatching);
  }


  /*
   The only differences between the responses should be:
   - the name of the array holding the products (products vs. productsFeed)
   - the GoogleAdBanner in place of the first product in productsFeed
   */
  @Then("the products by search responses are identical")
  public void theProductsBySearchResponsesAreIdentical() {
    after.getProductsFeed().remove(0); // Remove GoogleAdBanner from productList

    softAssert.assertTrue(before.equals(after), "totalNumberOfProducts, nextPage, filters or sortOptions differed");
    softAssert.assertTrue(before.getProducts().equals(after.getProductsFeed()), "List<Product> differed between responses");
  }
}