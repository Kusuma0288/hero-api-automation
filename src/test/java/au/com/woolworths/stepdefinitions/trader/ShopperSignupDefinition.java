package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.utils.Utilities;
import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.Errors;
import au.com.woolworths.model.trader.ShopperLoginResponseV2;
import au.com.woolworths.model.trader.UserDetail;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ShopperSignupDefinition extends ShopperHelper {
  private final static Logger logger = Logger.getLogger("ShopperSignupDefinition.class");


  @Then("^I use the following details for signing up as a new user in same device$")
  public void iUseTheFollowingDetailsForSigningUpAsANewUserInSameDevice(List<UserDetail> userDetails) throws Throwable {
    ShopperLoginResponseV2 signupResponse = iUseTheFollowingDetailsForSigningUpAsANewUser(userDetails, sharedData.deviceId);
    if (signupResponse.getStatusCode().equals("201")) {
      sharedData.signedUpEmail = signupResponse.getSession().getEmail();
    }
    sharedData.signupResponseStatusCode = signupResponse.getStatusCode();
    sharedData.signupLoginResponseV2 = signupResponse;
    sharedData.signupAuthToken = signupResponse.getAuthToken();
    sharedData.authToken = signupResponse.getAuthToken();
  }

  @Then("^I use the following details for signing up as a new user$")
  public void iUseTheFollowingDetailsForSigningUpAsANewUser(List<UserDetail> userDetails) throws Throwable {
    String deviceId = Utilities.generateRandomUUIDString();
    ShopperLoginResponseV2 signupResponse = iUseTheFollowingDetailsForSigningUpAsANewUser(userDetails, deviceId);
    if (signupResponse.getStatusCode().equals("201")) {
      sharedData.signedUpEmail = signupResponse.getSession().getEmail();
    }
    sharedData.signupResponseStatusCode = signupResponse.getStatusCode();
    sharedData.signupLoginResponseV2 = signupResponse;
    sharedData.signupAuthToken = signupResponse.getAuthToken();
    sharedData.deviceId = deviceId;
  }

  @When("^connection from apigee to trader public api signup endpoint happens$")
  public void connectionFromApigeeToTraderPublicAPISignupEndpointHappens() throws Throwable {
    Assert.assertNotNull(sharedData.signupResponseStatusCode, "Signup Connection issue::" + sharedData.signupResponseStatusCode);
  }

  @Given("^I use the exact details for signing up as a new shopper$")
  public void iUseTheExactDetailsForSigningUpAsANewShopper(List<UserDetail> userDetails) throws Throwable {
    String deviceId = Utilities.generateRandomUUIDString();
    ShopperLoginResponseV2 signupResponse = iUseTheExactDetailsForSigningUpAsANewUser(userDetails, deviceId);
    sharedData.signupLoginResponseV2 = signupResponse;
    sharedData.signupResponseStatusCode = signupResponse.getStatusCode();
  }

  @Then("^trader signup api responds with the response status \"(.*)\" and following error fields$")
  public void traderSignupAPIRespondsWithTheResponseStatusAndFollowingErrorFields(String statusCode, DataTable fields) throws Throwable {
    Assert.assertTrue(sharedData.signupResponseStatusCode.equals(statusCode), "Status Code is::" + sharedData.signupResponseStatusCode + " expected::" + statusCode);
    Assert.assertTrue(sharedData.signupLoginResponseV2.getResponseStatus().getErrorCode().equals("ValidationError"), "Validation Error status is not present");
    //logger.info("Total Error::"+sharedData.signupLoginResponseV2.getResponseStatus().getErrors().size());
    for (Map<String, String> errors : fields.asMaps(String.class, String.class)) {
      Errors errorField = sharedData.signupLoginResponseV2.getResponseStatus().getErrors().stream().filter(x -> x.getFieldName().contains(errors.get("fieldName"))).findAny().orElse(null);
      if (errorField != null) {
        boolean descriptionPresent = sharedData.signupLoginResponseV2.getResponseStatus().getErrors().stream().filter(x -> x.getMessage().contains(errors.get("errorDescription"))).findAny().isPresent();
        Assert.assertTrue(descriptionPresent, "Expected error description is missing::" + errors.get("errorDescription"));
      } else {
        Assert.assertTrue(false, "Expected error field is missing::" + errors.get("fieldName"));
      }

    }
  }

  @Then("^apigee successfully authenticate to trader public api endpoint with signedup user session details$")
  public void apigeeSuccessfullyAuthenticateToTraderPublicAPIEndpointAsShopperWithSignedUpUserSessionDetails() throws Throwable {
    Assert.assertTrue(sharedData.signupResponseStatusCode.equals("201"), "Status Code is::" + sharedData.signupResponseStatusCode);
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getAuthToken());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getRefreshToken());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getSession());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getIdmAccessToken());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getIdmAccessTokenExpiresIn());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getIdmRefreshToken());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getSession().getEmail());
    Assert.assertNotNull(sharedData.signupLoginResponseV2.getSession().getShopperID());
  }

  @When("^connection from apigee to trader public signup api endpoint happens$")
  public void connection_from_apigee_to_trader_public_api_endpoint_happens() throws Throwable {
    Assert.assertNotNull(sharedData.signupResponseStatusCode, "Connection issue::" + sharedData.signupResponseStatusCode);
  }

  @Then("^I should see the following (.*) captured with status code as (.*) having (.*)$")
  public void i_should_see_the_following_captured_with_status_code_as(String status, String statusCode, String expectedErrorFields) throws Throwable {
    ShopperLoginResponseV2 shopperResponse = sharedData.signupLoginResponseV2;
    List<String> expectedErrorFieldsList = Arrays.asList(expectedErrorFields.split(";"));
    Assert.assertTrue(sharedData.signupResponseStatusCode.equals(statusCode), "Status Code::" + sharedData.signupResponseStatusCode + " is not matching with expected status code::" + statusCode);

    if (sharedData.signupResponseStatusCode.equals("201")) {
      Assert.assertNotNull(shopperResponse.getAuthToken());
      Assert.assertNotNull(shopperResponse.getRefreshToken());
      Assert.assertNotNull(shopperResponse.getSession());
      Assert.assertNotNull(shopperResponse.getIdmAccessToken());
      Assert.assertNotNull(shopperResponse.getIdmAccessTokenExpiresIn());
      Assert.assertNotNull(shopperResponse.getIdmRefreshToken());
      Assert.assertNotNull(shopperResponse.getSession().getEmail());
      Assert.assertNotNull(shopperResponse.getSession().getShopperID());
    } else {
      Assert.assertTrue(sharedData.signupResponseStatusCode.equals("400"), "Expected Status Code (400) but received::" + sharedData.signupResponseStatusCode);
      Assert.assertNull(shopperResponse.getAuthToken());
      Assert.assertNull(shopperResponse.getRefreshToken());
      Assert.assertNull(shopperResponse.getSession());
      Assert.assertNull(shopperResponse.getIdmAccessToken());
      Assert.assertNotNull(shopperResponse.getResponseStatus(), "No errors present in Response Status");
      if (status.contains("ERROR")) {
        Assert.assertTrue(shopperResponse.getResponseStatus().getErrors().size() > 0, "There are no errors present");
      }
      Assert.assertTrue(shopperResponse.getResponseStatus().getErrorCode().equalsIgnoreCase("ValidationError"), "Expected ErrorCode as ValidationError");
      Assert.assertNull(shopperResponse.getResponseStatus().getMessage());
      Assert.assertNull(shopperResponse.getResponseStatus().getStackTrace());
      Assert.assertNotNull(shopperResponse.getResponseStatus().getErrors());

      Object[] responseErrorFields = shopperResponse.getResponseStatus().getErrors().stream().map(s -> String.valueOf(s.getFieldName())).collect(Collectors.toList()).toArray();
      Object[] responseErrorMessages = shopperResponse.getResponseStatus().getErrors().stream().map(s -> String.valueOf(s.getMessage())).collect(Collectors.toList()).toArray();
      List<?> actualErrorFieldsList = new ArrayList<String>();
      List<?> actualErrorMessagesList = new ArrayList<String>();

      if (responseErrorFields.getClass().isArray()) {
        actualErrorFieldsList = Arrays.asList(responseErrorFields);
        actualErrorMessagesList = Arrays.asList(responseErrorMessages);
      }

      Comparator reverseOrder = Collections.reverseOrder();
      Collections.sort(actualErrorFieldsList, reverseOrder);
      Collections.sort(expectedErrorFieldsList, reverseOrder);
      Collections.sort(actualErrorMessagesList, reverseOrder);

      //Get the List of expected error messages
      List<String> expectedErrorMessagesList = new ArrayList<>();

      Assert.assertTrue(actualErrorFieldsList.size() == expectedErrorFieldsList.size(), "Expected error fields::" + expectedErrorFieldsList + " but actual are::" + actualErrorFieldsList);

      for (int i = 0; i < expectedErrorFieldsList.size(); i++) {
        String expectedField = expectedErrorFieldsList.get(i);
        String actualField = actualErrorFieldsList.get(i).toString();
        if (signupValidationMap.containsKey(expectedField)) {
          if (actualErrorFieldsList.contains(expectedField) || expectedField.contains(actualField)) {
            expectedErrorMessagesList.add(signupValidationMap.get(expectedField));
          }
        }
      }

      Collections.sort(expectedErrorMessagesList, reverseOrder);
      Assert.assertTrue(actualErrorMessagesList.equals(expectedErrorMessagesList), "Expected error messages::" + expectedErrorMessagesList + " but actual are::" + actualErrorMessagesList);
    }
  }
}
