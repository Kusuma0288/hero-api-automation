package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.Utilities;
import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.GuestLoginResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.logging.Logger;

public class GuestLoginDefinition extends ShopperHelper {

  private static final Logger logger = Logger.getLogger("GuestLoginDefinition.class");
  private GuestLoginResponse response = new GuestLoginResponse();
  private String deviceId;

    
  @Given("^apigee connect to trader public api endpoint as guest in store (\\d+)$") 
  public void apigeeConnectToTraderPublicAPIEndpointAsGuestInStore(int storeId) throws Throwable { 
    deviceId = Utilities.generateRandomUUIDString();
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptions(deviceId);
  
    sharedData.deviceId = deviceId; sharedData.responseStatusCode =
    response.getStatusCode(); sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();
    Assert.assertTrue(response.getStatusCode().equals("200")); 
  
  }
     

  @Given("^apigee connect to trader public api endpoint as guest$")
  public void apigeeConnectToTraderPublicAPIEndpointAsGuest() throws Throwable {
    deviceId = Utilities.generateRandomUUIDString();
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptions(deviceId);
    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();
    Assert.assertTrue(response.getStatusCode().equals("200"));
  }

  @Given("^apigee connect to trader public api endpoint as guest with fulfilment store id (\\d+)$")
  public void apigeeConnectToTraderPublicAPIEndpointAsGuestWithFulfilmentStoreId(int expectedFulfilmentStoreId)throws Throwable {
    deviceId = Utilities.generateRandomUUIDString();
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptions(deviceId);

    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();
    sharedData.deliveryAddressId = response.getSession().getDeliveryAddressID();
    Assert.assertTrue(response.getStatusCode().equals("200"));
  }

  @Given("^apigee connect to trader public api endpoint as guest with only device id$")
  public void apigeeConnectToTraderPublicAPIEndpointAsGuestWithOnlyDeviceId() throws Throwable {
    deviceId = Utilities.generateRandomUUIDString();
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptions(deviceId);

    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();
  }

  @When("^apigee connect to trader public endpoint as guest with saved device id$")
  public void apigeeConnectToTraderPublicEndpointAsGuestWithSavedDeviceId() throws Throwable {
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptions(sharedData.deviceId);

    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();
  }

  @Given("^apigee connect to the trader public V2 endpoint as guest with fulfilment store id (\\d+)$")
  public void apigeeConnectToTheTraderPublicV2EndpointAsAGuestWithFulfilmentStoreId(int expectedFulfilmentStoreId)
        throws Throwable {
    deviceId = Utilities.generateRandomUUIDString();
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptionsV2(deviceId);
    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();

    if (response.getStatusCode().equals("200")) {
      Assert.assertTrue(response.getSession().getFulfilmentStoreID() == expectedFulfilmentStoreId, "Fulfilment Store Id is not matching with expected in V2 endpoint:: " + expectedFulfilmentStoreId);
    }
  }

  @Given("^apigee connect to the trader public V2 endpoint as guest with postcode (\\d+)$")
  public void apigeeConnectToTheTraderPublicV2EndpointAsAGuestWithPostcode(int postCode) throws Throwable {
    deviceId = Utilities.generateRandomUUIDString();
    response = apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptionsV2(deviceId);

    sharedData.deviceId = deviceId;
    sharedData.responseStatusCode = response.getStatusCode();
    sharedData.guestLoginResponse = response;
    sharedData.authToken = response.getAuthToken();
  }

  @When("^connection from apigee to trader public api endpoint happens$")
  public void connectionFromApigeeToTraderPublicApiEndpointHappens() throws Throwable {
    Assert.assertNotNull(sharedData.responseStatusCode, "Connection issue::" + sharedData.responseStatusCode);
  }

  @Then("^apigee successfully authenticate to trader public api endpoint as guest$")
  public void apigeeSuccessfullyAuthenticateToTraderPublicAPIEndpointAsGuest() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertNotNull(response.getAuthToken());
    Assert.assertTrue(response.getLoginResult().equals("Success"), "Some issue with the login");
    Assert.assertTrue(response.getLoginMessage().equals("Login Success"), "Some issue with the login message");
  }

  @Then("^I successfully authenticate to trader public api endpoint as guest with all session fields$")
  public void iSuccessfullyAuthenticateToTraderPublicAPIEndpointAsGuestWithAllSessionFields() throws Throwable {
    apigeeSuccessfullyAuthenticateToTraderPublicAPIEndpointAsGuestWithAllSessionFields();
  }

  @Then("^apigee successfully authenticate to trader public api endpoint as guest with all session fields$")
  public void apigeeSuccessfullyAuthenticateToTraderPublicAPIEndpointAsGuestWithAllSessionFields() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("200"), "Expected Status Code is 200 but found::" + sharedData.responseStatusCode);
    Assert.assertNotNull(response.getAuthToken());
    Assert.assertNotNull(response.getSession());
    Assert.assertNotNull(response.getSession().getFulfilmentStoreID());
    Assert.assertNotNull(response.getSession().getFulfilmentMethod());
    Assert.assertNotNull(response.getSession().getDeliveryMethod());
    Assert.assertNotNull(response.getSession().getDeliveryAddressCity());
    Assert.assertNotNull(response.getSession().getDeliveryAddressCountry());
    Assert.assertNotNull(response.getSession().getDeliveryAddressSuburb());
    Assert.assertTrue(response.getLoginResult().equals("Success"), "Some issue with the login");
    Assert.assertTrue(response.getLoginMessage().equals("Login Success"), "Some issue with the login message");
  }

  @Then("^apigee failed to authenticate to trader public api endpoint as guest$")
  public void apigeeFailedToAuthenticateToTraderPublicAPIEndpointAsGuest() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("404"), "Status Code expected is 404 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(response.getCode().equals("STORE_NOT_FOUND"), "Error Code is::" + response.getCode());
    Assert.assertTrue(response.getMessage().contains("Store with address"), "Error Message is::" + response.getMessage());
  }

  @Then("^apigee failed to authenticate to trader public api V2 endpoint$")
  public void apigeeFailedToAuthenticateToTraderPublicAPIV2Endpoint() throws Throwable {
    Assert.assertTrue(sharedData.responseStatusCode.equals("500"),
            "Status Code expected is 500 but found::" + sharedData.responseStatusCode);
    Assert.assertTrue(response.getMessage().contains("WowApi Error"), "Error Message is::" + response.getMessage());
  }

}
