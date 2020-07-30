package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.model.apigee.address.AddressDetails;
import au.com.woolworths.model.apigee.address.ApigeeAddress;
import au.com.woolworths.model.apigee.address.SearchAddresses;
import au.com.woolworths.model.apigee.delivery.DeliveryFulfilmentV3Response;
import au.com.woolworths.model.apigee.fulfilment.Fulfilmentv3ErrorResponse;
import au.com.woolworths.model.apigee.lists.ListAddresses;
import au.com.woolworths.utils.Utilities;
import au.com.woolworths.helpers.apigee.AddressHelper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.logging.Logger;

public class DeliveryAddressDefinition extends AddressHelper {

  private final static Logger logger = Logger.getLogger("DeliveryAddressDefinition.class");


  @When("^I pick a location at \"([^\"]*)\" for delivery$")
  public void iPickALocationAtForDelivery(String address) throws Throwable {
    sharedData.fulfilment = "online";
    searchForTheAddresses(address);
    iSelectTheAddressAsFulfilmentAddressFromMatchingAddresses(1);
  }

  public void searchForTheAddresses(String lookupAddress) throws Throwable {
    lookupAddress = Utilities.replaceMultipleandTrimSpaces(lookupAddress);

    SearchAddresses searchAddressResponse = iSearchForTheAddress(lookupAddress);
    sharedData.searchAddressResponse = searchAddressResponse;

    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchAddressResponse.getAddresses()[0].getPostCode());
    Assert.assertNotNull(searchAddressResponse.getAddresses()[0].getText());
    Assert.assertNotNull(searchAddressResponse.getAddresses()[0].getAmasID());
  }

  public void iSelectTheAddressAsFulfilmentAddressFromMatchingAddresses(int position) throws Throwable {
    SearchAddresses addressResponse = sharedData.searchAddressResponse;
    ApigeeAddress[] addressItem = addressResponse.getAddresses();

    AddressDetails addressDetailResponse = iGetTheAddressIdFromAmasId(addressItem[position - 1].getAmasID());

    String addressId = addressDetailResponse.getId();
    Assert.assertNotNull(addressId);
    sharedData.addressId = addressId;

  }

  @When("^verify the address saved is set as primary address in MyAccount$")
  public void verifyPrimaryAddressInMyAccount() throws Throwable {
    ListAddresses addressesInMyAccount = iGetTheListAddresses();
    AddressDetails[] addressDetails = addressesInMyAccount.getAddresses();

    boolean isPrimary = Arrays.stream(addressDetails).filter(x -> x.getId().equals(sharedData.addressId))
        .findFirst().get().isIsprimary();

    Assert.assertTrue("Recently saved address is set as primary", isPrimary);
  }

  @Then("^filter the address by address text and verify address saved is set as primary address in MyAccount$")
  public void verifyPrimaryAddressByAddressText() throws Throwable {
    ListAddresses addressesInMyAccount = iGetTheListAddresses();
    AddressDetails[] addressDetails = addressesInMyAccount.getAddresses();

    boolean isPrimary = Arrays.stream(addressDetails).filter(x -> x.getText().equals(sharedData.addressText))
        .findFirst().get().isIsprimary();

    Assert.assertTrue("Recently saved address is set as primary", isPrimary);

  }

  @Then("^I make a request to fulfilment api with primary address id to set the address as fulfilment address$")
  public void iMakeARequestToFulfilmentApiWithPrimaryAddressIdToSetTheAddressAsFulfilmentAddress() throws Throwable {

    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = setTheFulfilmentForAddress();
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
    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = getTheFulfilmentAddress();

    deliveryFulfilmentV3Response.getDelivery().getAddress().getId();

    Assert.assertTrue(deliveryFulfilmentV3Response.getDelivery().getAddress().getText().equalsIgnoreCase(sharedData.addressText));
  }

  @Then("^I make a request with invalid address to fulfilment api with primary address id to set the address as fulfilment address$")
  public void iMakeARequestToFulfilmentApiWithInvalidAccessToken() throws Throwable {
    Fulfilmentv3ErrorResponse fulfilmentv3ErrorResponse = setTheFulfilmentForAddressErrorScenario(" ");
    Assert.assertTrue("Error Code not matching", fulfilmentv3ErrorResponse.getErrorCode().equals("AP004"));
    Assert.assertTrue("Error Message not matching", fulfilmentv3ErrorResponse.getErrorMessage().equals("Not Found"));
  }


}
