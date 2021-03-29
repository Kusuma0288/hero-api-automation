package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.model.apigee.authentication.GuestLoginRequest;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import au.com.woolworths.model.iris.graphql.productListByProductGroup.Product;
import au.com.woolworths.model.iris.graphql.productListByProductGroup.ProductsByProductGroup;
import au.com.woolworths.model.iris.graphql.productListByProductGroup.ProductsByProductGroupResponse;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.asserts.SoftAssert;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static au.com.woolworths.graphql.parser.GraphqlParser.parseGraphql;
import static au.com.woolworths.stepdefinitions.common.ServiceHooks.restInvocationUtil;
import static au.com.woolworths.utils.Utilities.generateRandomUUIDString;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class ProductsByProductGroupDefinitions {

  private final ObjectMapper mapper = new ObjectMapper(); // Jackson databind class
  private final Map<String, String> webServiceResponseMap = new HashMap<>(); // Var to store json result - statusCode/responseBody/contentType
  private Header accessToken = new Header("Key", "Value"); // Overwritten later
  private Response response;

  // TODO: We need to use/refactor existing request methods defined on framework level
  //  being used by both rewards and shop app bff tests.
  private void makeHttpRequest(String endPoint, String requestStr) {

    // Clear any previous request's history
    webServiceResponseMap.clear();

    try {
      // ENDPOINT
      RestAssured.baseURI = TestProperties.get("BASE_URI_APIGEE");

      // CALL
      response =
              given()
                      .header("Content-Type", "application/json")
                      .header("Accept", "application/json")
                      .header("x-api-key", TestProperties.get("x-api-key"))
                      .header("user-agent", TestProperties.get("user-agent"))
                      .header(accessToken)
                      .body(requestStr)
                      //.log().all() // DEBUG - request log
                      .when()
                      .post(endPoint)
                      .then()
                      //.log().all() // DEBUG - response log
                      .extract().response();

      // CATCH - any GraphQL errors - throws exception if found
      response.then().body("errors", Matchers.nullValue());

      // SAVE - response for future steps
      webServiceResponseMap.put("response", response.getBody().asString());
      webServiceResponseMap.put("statusCode", Integer.toString(response.getStatusCode()));
    } catch (Exception e) {
      fail("Endpoint: " + endPoint + ", Request payload: " + requestStr + ", Error: " + e.getMessage());
    } finally {
      // SAVE - endpoint/request/response strings for Cluecumber report
      restInvocationUtil.endPoints.add(endPoint);
      restInvocationUtil.requests.add(requestStr);
      restInvocationUtil.responses.add(response);
    }
  }

  @Given("^I connect to apigee endpoint as a mobile, guest user$")
  public void mobileUserConnectToApigeeAPIEndpointAsGuest() throws Throwable {

    // ENDPOINT
    String endPoint = URLResources.APIGEE_V2_GUEST_LOGIN;

    // REQUEST - set up unique deviceId
    GuestLoginRequest loginRequest = new GuestLoginRequest();
    loginRequest.setDevice_auth_token(generateRandomUUIDString());
    String requestStr = mapper.writeValueAsString(loginRequest);

    // CALL
    makeHttpRequest(endPoint, requestStr);

    // RESPONSE
    LoginReponse response = mapper.readValue(webServiceResponseMap.get("response"), LoginReponse.class);

    // ASSERT
    assertEquals(webServiceResponseMap.get("statusCode"), "200", "statusCode == 200 but was: " + webServiceResponseMap.get("statusCode"));

    // Update token for subsequent calls
    accessToken = new Header("Authorization", "Bearer " + response.getAccess_token());
  }

  @Given("^I request product group \"([^\"]*)\"$")
  public void userRequestsAProductGroup(String groupId) throws Throwable {

    // ENDPOINT
    String endPoint = URLResources.HERMES_V1_GRAPHQL;

    // REQUEST - add 'groupId' inside 'variable' json object
    InputStream iStream = this.getClass().getResourceAsStream("/gqlQueries/iris/productsByProductGroup.graphql");
    ObjectNode variables = mapper.createObjectNode().put("groupId", groupId);
    String graphqlQuery = parseGraphql(iStream, variables);

    // CALL - any errors caught within
    makeHttpRequest(endPoint, graphqlQuery);
  }

  @Then("^I can see the product group with products listed$")
  public void productGroupIsAvailable() throws Throwable {

    // Assertion that only fails once all asserts are complete - better reporting
    SoftAssert softAssert = new SoftAssert();

    // Deserialize and parse product list
    ProductsByProductGroup products = mapper.readValue(webServiceResponseMap.get("response"), ProductsByProductGroupResponse.class).getData().getProductsByProductGroup();

    // ASSERT - sort options - returns 53/54
    softAssert.assertTrue(products.getSortOptions().size() >= 53, "products.sortOptions.count >= " + 53 + " but was: " + products.getSortOptions().size());

    // ASSERT - product count - env(TEST) sometimes returns 0 products
    softAssert.assertNotEquals(products.getTotalNumberOfProducts(), 0, "totalNumberOfProducts != 0 but was: " + products.getTotalNumberOfProducts());
    softAssert.assertTrue(products.getProducts().size() > 0, "products.count > 0 but was: " + products.getProducts().size());

    // ASSERT - 1st product with "isAvailable":true - if existing
    Product firstAvailableProduct = products.getProducts().stream().filter(Product::isAvailable).findFirst().orElse(null);

    if (firstAvailableProduct != null) {
      softAssert.assertNotNull(firstAvailableProduct.getName(), "Available products[0].name != null but was: " + firstAvailableProduct.getName());
      softAssert.assertTrue(firstAvailableProduct.getProductImage().contains("cdn0.woolworths.media/content/wowproductimages/medium/"), "Available products[0].productImage contains 'https://uatcdn0.woolworths.media/content/wowproductimages/medium/' but was: " + firstAvailableProduct.getProductImage());
      softAssert.assertNotNull(firstAvailableProduct.getPrice(), "Available products[0].price =! null but was: " + firstAvailableProduct.getPrice());
      softAssert.assertEquals(firstAvailableProduct.getTrolley().getButtonState(), "ADD", "Available products[0].trolley.buttonState == 'ADD' but was: " + firstAvailableProduct.getTrolley().getButtonState());
      softAssert.assertNotNull(firstAvailableProduct.getList(), "Available products[0].list != null but was: " + firstAvailableProduct.getList());
    }

    // ASSERT - 1st product with "isAvailable":false - if existing
    Product firstUnavailableProduct = products.getProducts().stream().filter((i) -> !i.isAvailable).findFirst().orElse(null);

    if (firstUnavailableProduct != null) {
      softAssert.assertNotNull(firstUnavailableProduct.getName(), "Unavailable products[0].name != null but was: " + firstUnavailableProduct.getName());
      softAssert.assertTrue(firstUnavailableProduct.getProductImage().contains("cdn0.woolworths.media/content/wowproductimages/medium/"), "Unavailable products[0].productImage contains 'https://uatcdn0.woolworths.media/content/wowproductimages/medium/' but was: " + firstUnavailableProduct.getProductImage());
      softAssert.assertTrue(firstUnavailableProduct.getInlineLabels().size() == 0, "Unavailable products[0].inlineLabels[].size == 0 but was: " + firstUnavailableProduct.getInlineLabels().size());
      softAssert.assertEquals(firstUnavailableProduct.getTrolley().getButtonState(), "DISABLED", "Unavailable products[0].trolley.buttonState == 'DISABLED' but was: " + firstUnavailableProduct.getTrolley().getButtonState());
      softAssert.assertNotNull(firstUnavailableProduct.getList(), "Unavailable products[0].list != null but was: " + firstUnavailableProduct.getList());
    }

    // Finally parse assert results
    softAssert.assertAll();
  }
}
