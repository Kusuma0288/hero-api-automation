package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.AddressHelper;
import au.com.woolworths.model.apigee.address.AddressStoresV2;
import au.com.woolworths.model.apigee.authentication.ErrorResponseV2;
import au.com.woolworths.model.apigee.fulfilment.FulFilmentResponse;
import au.com.woolworths.model.apigee.store.Stores;
import au.com.woolworths.utils.Utilities;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PickupDefinition extends AddressHelper {
  private final static Logger logger = Logger.getLogger("PickupDefinition.class");


  @When("^I search for the pickup stores in Apigee for the postcode (.*)$")
  public void searchForThePostCode(String postCode) throws Throwable {
    postCode = Utilities.replaceMultipleandTrimSpaces(postCode);
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("postcode", postCode);
    AddressStoresV2 searchPostCodeResponse = getStore(queryParams);
    sharedData.searchPostCodeResponse = searchPostCodeResponse;

    if (searchPostCodeResponse.getStores().length > 0) {
      Assert.assertNotNull(searchPostCodeResponse.getStores()[0].getAddressText(), "AddressText is null");
      Assert.assertNotNull(searchPostCodeResponse.getStores()[0].getDescription(), "Description is null");
      Assert.assertNotNull(searchPostCodeResponse.getStores()[0].getAreaId());

    }
  }

  @Then("^I set the fulfilmentMethod to (.*) for the (.*) store$")
  public void iSetTheFulfilmentMethodToForTheStore(String fulfilmentMethod, int order) throws Throwable {
    AddressStoresV2 searchStoresResponse = sharedData.searchPostCodeResponse;
    String getFulFilmentMethod = searchStoresResponse.getStores()[order - 1].getFulfilmentMethod();
    if (getFulFilmentMethod.contains("Pickup")) {
      long storeAddressId = searchStoresResponse.getStores()[order - 1].getPickUpType()[0].getAddressId();
      FulFilmentResponse fulFilmentResponse = setTheFulfilmentForTheStore(String.valueOf(storeAddressId));
      Assert.assertTrue(fulFilmentResponse.getResults().getPickupStores().getHttpStatusCode() == 200, "Pickup Store status code is not 200");
      Assert.assertTrue(fulFilmentResponse.getResults().getPickupStores().getErrorMessage().equals("OK"), "Pickup Store status code is not OK");
      Assert.assertNotNull(fulFilmentResponse.getPickup().getStore(), "Store should not be set to null");
      Assert.assertNotNull(fulFilmentResponse.getFulfilment(), "Fulfilment is not set to null");

    } else {
      Assert.fail("The Fulfilment method is not pickup");
    }

  }

  @When("^I search for the pick up stores with Store AddressID (.*) and validate that AddressText, Description and SuburbId are not blank")
  public void storesForStoreAddressID(String StoreID) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("storeAddressId", StoreID);
    AddressStoresV2 searchStoresResponse = getStore(queryParams);
    //Assert at least 1 store is returned
    Assert.assertTrue(searchStoresResponse.getStores().length > 0, "Stores not returned for the given address id");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getAddressText().isEmpty(), "Address text is empty");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getDescription().isEmpty(), "Description is empty");
    Assert.assertTrue(searchStoresResponse.getStores()[0].getSuburbID() > 0, "Suburb ID is not valid");
  }

  @When("^I search for the pickup stores with StoreID (.*) and validate the AddressText, Description and SuburbId are not blank")
  public void storesForStoreID(String storeID) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("storeid", storeID);
    AddressStoresV2 searchStoresResponse = getStore(queryParams);
    //Assert at least 1 store is returned
    Assert.assertTrue(searchStoresResponse.getStores().length > 0, "Stores not returned for the given store id");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getAddressText().isEmpty(), "Address text is empty");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getDescription().isEmpty(), "Description is empty");
    Assert.assertTrue(searchStoresResponse.getStores()[0].getSuburbID() > 0, "Suburb ID is not valid");
  }

  @When("^I search for the pick up stores using latitude (.*) & longitude (.*) and verify if stores returned are sorted by distance by default$")
  public void storesForLatLong(String latitude, String longitude) throws Throwable {
    Map<String, String> queryParams = new HashMap<String, String>();
    latitude = Utilities.replaceMultipleandTrimSpaces(latitude);
    longitude = Utilities.replaceMultipleandTrimSpaces(longitude);
    queryParams.put("latitude", latitude);
    queryParams.put("longitude", longitude);
    AddressStoresV2 searchStoresResponse = getStore(queryParams);

    //Assert at least 1 store is returned
    Assert.assertTrue(searchStoresResponse.getStores().length > 0, "Stores not returned for the given lat & long");

    Stores[] distanceArray = searchStoresResponse.getStores();
    double[] stores = Arrays.stream(distanceArray).mapToDouble(x -> x.getDistance()).toArray();

    //Assert that the stores returned are sorted by distance
    Assert.assertTrue(Utilities.isSorted(stores), "Stores retrieved are not in order");
  }

  @When("^I search for the pick up stores with invalid Store AddressID (.*) and validate the response")
  public void storesForInvalidStoreAddressID(String invalidStoreAddressID) throws Throwable {
    ErrorResponseV2 v2AddressesErrorResponse = getStoresForInvalidParams("storeAddressId", invalidStoreAddressID);
    Assert.assertTrue(v2AddressesErrorResponse.getErrorMessage().equalsIgnoreCase("Not Found"));
  }

  @When("^I search for the pickup stores with invalid StoreID (.*) and validate the response")
  public void storesForInvalidStoreID(String invalidStoreID) throws Throwable {
    ErrorResponseV2 v2AddressesErrorResponse = getStoresForInvalidParams("storeid", invalidStoreID);
    Assert.assertTrue(v2AddressesErrorResponse.getErrorMessage().equalsIgnoreCase("Not Found"));
  }

  @Then("^I validate that the fulfilmentMethod match to (.*)$")
  public void validateFulfilmentMethod(String fulfilmentMethod) throws Throwable {
    if (sharedData.searchPostCodeResponse.getStores().length > 0) {
      sharedData.searchPostCodeResponse.getStores()[0].getFulfilmentMethod().contains(fulfilmentMethod);
    }

  }

  @Then("^I validate that the no matching results are returned for the invalid postcode$")
  public void validateInvalidPostCodeSearch() {
    Assert.assertTrue(sharedData.searchPostCodeResponse.getStores().length == 0, "Some record is being returned for the entered post code");
  }

  @When("^I search for the pickup stores with invalid postcode (.*)$")
  public void searchForTheInvalidPostCode(String postCode) throws Throwable {
    postCode = Utilities.replaceMultipleandTrimSpaces(postCode);
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("postcode", postCode);
    AddressStoresV2 searchPostCodeResponse = getStore(queryParams);
    sharedData.searchPostCodeResponse = searchPostCodeResponse;
    Assert.assertNotNull(searchPostCodeResponse.getStores(), "Pick up stores response is not null");

  }

  @When("^I set a pick up store using post code(.*)$")
  public void setPickUpStoreUsingPostCode(String postCode) throws Throwable {
    sharedData.fulfilment = "pickup";
    searchForThePostCode(postCode);
    iSetTheFulfilmentMethodToForTheStore(sharedData.fulfilment, 1);
  }

  @And("Sets a pick up store using post code {string}")
  public void setsPickUpStoreUsingPostCode(String postCode) throws Throwable {
    setPickUpStoreUsingPostCode(postCode);
  }
}
