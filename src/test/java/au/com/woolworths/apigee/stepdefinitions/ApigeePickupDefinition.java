package au.com.woolworths.apigee.stepdefinitions;

import java.util.*;
import java.util.logging.Logger;

import au.com.woolworths.Utils.Utilities;
import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.ApigeeAddressHelper;
import au.com.woolworths.apigee.model.ApigeeStores;
import au.com.woolworths.apigee.model.ApigeeV2AddressStores;
import au.com.woolworths.apigee.model.AddressesV2ErrorResponse;
import au.com.woolworths.apigee.model.FulFilmentResponse;
import cucumber.api.java.en.*;
import org.testng.Assert;

public class ApigeePickupDefinition extends ApigeeAddressHelper {
  private final static Logger logger = Logger.getLogger("ApigeePickupDefinition.class");

  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public ApigeePickupDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^I search for the pickup stores in the (.*)$")
  public void searchForThePostCode(String postCode) throws Throwable {
    postCode = Utilities.replaceMultipleandTrimSpaces(postCode);

    ApigeeV2AddressStores searchPostCodeResponse = iSearchForThePostCode(postCode, sharedData.accessToken);
    sharedData.searchPostCodeResponse = searchPostCodeResponse;

    if (searchPostCodeResponse.getStores().length > 0) {
      Assert.assertNotNull(searchPostCodeResponse.getStores()[0].getAddressText(), "AddressText is null");
      Assert.assertNotNull(searchPostCodeResponse.getStores()[0].getDescription(), "Description is null");
      Assert.assertNotNull(searchPostCodeResponse.getStores()[0].getAreaId());

    }
  }

  @Then("^I set the fulfilmentMethod to (.*) for the (.*) store$")
  public void iSetTheFulfilmentMethodToForTheStore(String fulfilmentMethod, int order) throws Throwable {
    ApigeeV2AddressStores searchStoresResponse = sharedData.searchPostCodeResponse;
    String getFulFilmentMethod = searchStoresResponse.getStores()[order - 1].getFulfilmentMethod();
    if (getFulFilmentMethod.contains("Pickup")) {
      long storeAddressId = searchStoresResponse.getStores()[order - 1].getPickUpType()[0].getAddressId();
      FulFilmentResponse fulFilmentResponse = setTheFulfilmentForTheStore(String.valueOf(storeAddressId), sharedData.accessToken);
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
    ApigeeV2AddressStores searchStoresResponse = getStoresByAddressID(StoreID, sharedData.accessToken);
    //Assert at least 1 store is returned
    Assert.assertTrue(searchStoresResponse.getStores().length > 0, "Stores not returned for the given address id");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getAddressText().isEmpty(), "Address text is empty");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getDescription().isEmpty(), "Description is empty");
    Assert.assertTrue(searchStoresResponse.getStores()[0].getSuburbID() > 0, "Suburb ID is not valid");
  }

  @When("^I search for the pickup stores with StoreID (.*) and validate the AddressText, Description and SuburbId are not blank")
  public void storesForStoreID(String storeID) throws Throwable {
    ApigeeV2AddressStores searchStoresResponse = getStoresByStoreID(storeID, sharedData.accessToken);
    //Assert at least 1 store is returned
    Assert.assertTrue(searchStoresResponse.getStores().length > 0, "Stores not returned for the given store id");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getAddressText().isEmpty(), "Address text is empty");
    Assert.assertTrue(!searchStoresResponse.getStores()[0].getDescription().isEmpty(), "Description is empty");
    Assert.assertTrue(searchStoresResponse.getStores()[0].getSuburbID() > 0, "Suburb ID is not valid");
  }

  @When("^I search for the pick up stores using latitude (.*) & longitude (.*) and verify if stores returned are sorted by distance by default$")
  public void storesForLatLong(String latitude, String longitude) throws Throwable {
    latitude = Utilities.replaceMultipleandTrimSpaces(latitude);
    longitude = Utilities.replaceMultipleandTrimSpaces(longitude);

    ApigeeV2AddressStores searchStoresResponse = getStoresForLatLong(latitude, longitude, sharedData.accessToken);

    //Assert at least 1 store is returned
    Assert.assertTrue(searchStoresResponse.getStores().length > 0, "Stores not returned for the given lat & long");

    ApigeeStores[] distanceArray = searchStoresResponse.getStores();
    double[] stores = Arrays.stream(distanceArray).mapToDouble(x -> x.getDistance()).toArray();

    //Assert that the stores returned are sorted by distance
    Assert.assertTrue(Utilities.isSorted(stores), "Stores retrieved are not in order");
  }

  @When("^I search for the pick up stores with invalid Store AddressID (.*) and validate the response")
  public void storesForInvalidStoreAddressID(String invalidStoreAddressID) throws Throwable {
    AddressesV2ErrorResponse v2AddressesErrorResponse = getStoresForInvalidParams(invalidStoreAddressID, "storeAddressId", sharedData.accessToken);
    Assert.assertTrue(v2AddressesErrorResponse.getErrorMessage().equalsIgnoreCase("Not Found"));
  }

  @When("^I search for the pickup stores with invalid StoreID (.*) and validate the response")
  public void storesForInvalidStoreID(String invalidStoreID) throws Throwable {
    AddressesV2ErrorResponse v2AddressesErrorResponse = getStoresForInvalidParams(invalidStoreID, "storeid", sharedData.accessToken);
    Assert.assertTrue(v2AddressesErrorResponse.getErrorMessage().equalsIgnoreCase("Not Found"));
  }

  @Then("^I validate that the fulfilmentMethod match to (.*)$")
  public void validateFulfilmentMethod(String fulfilmentMethod) throws Throwable {
    if (sharedData.searchPostCodeResponse.getStores().length > 0) {
      sharedData.searchPostCodeResponse.getStores()[0].getFulfilmentMethod().contains(fulfilmentMethod);
    }

  }

  @Then("^I validate that the no matching results are returned for the invalid postcode$")
  public void validateInvalidPostCodeSearch() throws Throwable {
    Assert.assertTrue(sharedData.searchPostCodeResponse.getStores().length == 0, "Some record is being returned for the entered post code");
  }

  @When("^I search for the pickup stores with invalid postcode (.*)$")
  public void searchForTheInvalidPostCode(String postCode) throws Throwable {
    postCode = Utilities.replaceMultipleandTrimSpaces(postCode);

    ApigeeV2AddressStores searchPostCodeResponse = iSearchForThePostCode(postCode, sharedData.accessToken);
    sharedData.searchPostCodeResponse = searchPostCodeResponse;
    Assert.assertNotNull(searchPostCodeResponse.getStores(), "Pick up stores response is not null");

  }

  @When("^I set a pick up store using post code(.*)$")
  public void setPickUpStoreUsingPostCode(String postCode) throws Throwable {
    searchForThePostCode(postCode);
    iSetTheFulfilmentMethodToForTheStore("Pickup", 1);
  }
}
