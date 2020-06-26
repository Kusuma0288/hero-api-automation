package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.Utils.Utilities;
import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.ApigeeAddressHelper;
import au.com.woolworths.apigee.model.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import au.com.woolworths.apigee.model.DeliveryFulfilmentV3Response;

import java.util.Arrays;
import java.util.logging.Logger;

public class DeliveryAddressDefinition extends ApigeeAddressHelper {

  private final static Logger logger = Logger.getLogger("DeliveryAddressDefinition.class");

  private ApigeeSharedData sharedData;
  private ApigeeContainer picoContainer;

  public DeliveryAddressDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @When("^I pick a location at \"([^\"]*)\" for delivery$")
  public void iPickALocationAtForDelivery(String address) throws Throwable {
    picoContainer.fulfilment = "online";
    searchForTheAddresses(address);
    iSelectTheAddressAsFulfilmentAddressFromMatchingAddresses(1);
  }



  @When("^I search for the address \"([^\"]*)\"$")
  public void searchForTheAddresses(String lookupAddress) throws Throwable {
    lookupAddress = Utilities.replaceMultipleandTrimSpaces(lookupAddress);

    ApigeeSearchAddresses searchAddressResponse = iSearchForTheAddress(lookupAddress, sharedData.accessToken);
    sharedData.searchAddressResponse = searchAddressResponse;

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchAddressResponse.getAddresses()[0].getPostCode());
    Assert.assertNotNull(searchAddressResponse.getAddresses()[0].getText());
    Assert.assertNotNull(searchAddressResponse.getAddresses()[0].getAmasID());
  }

  @When("^I select the \"([^\"]*)\" address as fulfilment address from matching addresses$")
  public void iSelectTheAddressAsFulfilmentAddressFromMatchingAddresses(int position) throws Throwable {
    ApigeeSearchAddresses addressResponse = sharedData.searchAddressResponse;
    ApigeeAddress[] addressItem = addressResponse.getAddresses();

    ApigeeAddressDetails addressDetailResponse = iGetTheAddressIdFromAmasId(addressItem[position - 1].getAmasID(), sharedData.accessToken);

    String addressId = addressDetailResponse.getId();
    Assert.assertNotNull(addressId);
    sharedData.addressId = addressId;

  }

  @When("^verify the address saved is set as primary address in MyAccount$")
  public void verifyPrimaryAddressInMyAccount() throws Throwable {
    ApigeeListAddresses addressesInMyAccount = iGetTheListAddresses(sharedData.accessToken);
    ApigeeAddressDetails[] addressDetails = addressesInMyAccount.getAddresses();

    boolean isPrimary = Arrays.stream(addressDetails).filter(x -> x.getId().equals(sharedData.addressId))
        .findFirst().get().isIsprimary();

    Assert.assertTrue("Recently saved address is set as primary", isPrimary);
  }

  @Then("^filter the address by address text and verify address saved is set as primary address in MyAccount$")
  public void verifyPrimaryAddressByAddressText() throws Throwable {
    ApigeeListAddresses addressesInMyAccount = iGetTheListAddresses(sharedData.accessToken);
    ApigeeAddressDetails[] addressDetails = addressesInMyAccount.getAddresses();

    boolean isPrimary = Arrays.stream(addressDetails).filter(x -> x.getText().equals(sharedData.addressText))
        .findFirst().get().isIsprimary();

    Assert.assertTrue("Recently saved address is set as primary", isPrimary);

  }

  @Then("^I make a request to fulfilment api with primary address id to set the address as fulfilment address$")
  public void iMakeARequestToFulfilmentApiWithPrimaryAddressIdToSetTheAddressAsFulfilmentAddress() throws Throwable {

    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = setTheFulfilmentForAddress(sharedData.addressId, sharedData.accessToken);
    Integer deliveryFulfilmentID = deliveryFulfilmentV3Response.getDelivery().getAddress().getId();

    sharedData.addressText = deliveryFulfilmentV3Response.getDelivery().getAddress().getText();
    // To assert that the set fulfilment address id equals the primary address id
    Assert.assertTrue(deliveryFulfilmentID.toString().matches(sharedData.addressId));

    String getFulfilmentMethod = deliveryFulfilmentV3Response.getFulfilment().getMethod();

    // To assert that the fulfilment is type 'Courier'
    Assert.assertTrue(getFulfilmentMethod.contains("Courier"));
  }

  @Then("^I make a GET request to fulfilment api and verify the fulfilment address$")
  public void iMakeGETRequestToFulfilementApi() throws Throwable {
    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = getTheFulfilmentAddress(sharedData.accessToken);

    deliveryFulfilmentV3Response.getDelivery().getAddress().getId();

    Assert.assertTrue(deliveryFulfilmentV3Response.getDelivery().getAddress().getText().equalsIgnoreCase(sharedData.addressText));
  }

  @Then("^I make a request with invalid address to fulfilment api with primary address id to set the address as fulfilment address$")
  public void iMakeARequestToFulfilmentApiWithInvalidAccessToken() throws Throwable {
    Fulfilmentv3ErrorResponse fulfilmentv3ErrorResponse = setTheFulfilmentForAddressErrorScenario(" ", sharedData.accessToken);
    Assert.assertTrue("Error Code not matching", fulfilmentv3ErrorResponse.getErrorCode().equals("AP004"));
    Assert.assertTrue("Error Message not matching", fulfilmentv3ErrorResponse.getErrorMessage().equals("Not Found"));
  }


}
