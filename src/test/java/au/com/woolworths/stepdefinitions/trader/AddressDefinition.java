package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.AddressHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.utils.Utilities;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AddressDefinition extends AddressHelper {
  private final static Logger logger = Logger.getLogger("AddressDefinition.class");

  private Address[] guestAddress;


  @When("^I search for the address (.*)$")
  public void iSearchForTheAddress(String lookupAddress) throws Throwable {
    lookupAddress = Utilities.replaceMultipleandTrimSpaces(lookupAddress);
    sharedData.shopperDeliveryAddress = lookupAddress;

    SearchAddressResponse searchAddressResponse = searchForTheAddress(lookupAddress);
    sharedData.searchAddressResponse = searchAddressResponse;
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getPostcode());
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getId());
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getText());
  }

  @When("^I search for address \"([^\"]*)\" without saving for future lookup$")
  public void iSearchForTheAddressWithoutSavingForFutureLookup(String lookupAddress) throws Throwable {
    lookupAddress = Utilities.replaceMultipleandTrimSpaces(lookupAddress);
    sharedData.shopperDeliveryAddress = lookupAddress;
    SearchAddressResponse searchAddressResponse = searchForTheAddress(lookupAddress);
    sharedData.searchAddressResponse = searchAddressResponse;
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getPostcode());
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getId());
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getText());
  }

  @When("^I select the \"([^\"]*)\" address as checkout address from matching addresses$")
  public void iSelectTheAddressAsCheckoutAddressFromMatchingAddresses(int position) throws Throwable {
    SearchAddressResponse addressResponse = sharedData.searchAddressResponse;
    Items[] addressItem = addressResponse.getItems();

    AddressDetails addressDetailResponse = iGetTheAddressIdFromAmasId(addressItem[position - 1].getId());
    if (addressDetailResponse.getStatusCode().equals("200")) {
      Assert.assertNotNull(addressDetailResponse.getAddress());
      Assert.assertFalse(addressDetailResponse.isIsNonServiced());
      int addressId = addressDetailResponse.getAddress().getAddressId();
      CheckoutAddressResponse checkoutAddressResponse = iSetTheAddressId(addressId);
      sharedData.address = checkoutAddressResponse.getAddress();
      sharedData.deliveryAddressIdContainer = addressId;
      Assert.assertNotNull(checkoutAddressResponse);
    } else {
      MyAddresses adressessInMyAccount = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
      Address[] addresses = adressessInMyAccount.getAddresses();
      String[] deliveryAddress = sharedData.shopperDeliveryAddress.split(" ");
      Arrays.stream(addresses).forEach(x -> {
        if (x.getAddressText().contains(deliveryAddress[0]))
          sharedData.deliveryAddressIdContainer = x.getAddressId();
      });
      CheckoutAddressResponse checkoutAddressResponse = iSetTheAddressId(sharedData.deliveryAddressIdContainer);
      sharedData.address = checkoutAddressResponse.getAddress();
      Assert.assertNotNull(checkoutAddressResponse);
      Assert.assertTrue(checkoutAddressResponse.getAddress().isIsPrimary(), "Switched address ");
    }
  }

  @When("^I search the address for the guest mode$")
  public void iSearchTheAddressForTheGuestMode() throws Throwable {
    guestAddress = searchTheAddressForTheGuestMode();
    Address address = Arrays.stream(guestAddress).filter(x -> sharedData.shopperDeliveryAddress.contains(x.getAddressText())).findAny().orElse(null);
    assert address != null;
    Assert.assertTrue(sharedData.shopperDeliveryAddress.toLowerCase().contains(address.getAddressText().toLowerCase()), "Guest Address is not set to default::" + sharedData.shopperDeliveryAddress);
  }

  @When("^I search the address \"([^\"]*)\" for the guest mode$")
  public void iSearchTheAddressForTheGuestModeWithAddress(String findAddress) throws Throwable {
    guestAddress = searchTheAddressForTheGuestMode();
    Address address = Arrays.stream(guestAddress).filter(x -> x.getAddressText().contains(findAddress)).findAny().orElse(null);
    assert address != null;
    Assert.assertTrue(address.getAddressText().contains(findAddress), "Guest Address is not set to default");
  }

  @Then("^I should see the address as primary for guest user$")
  public void iShouldSeeTheAddressAsPrimaryForGuest() {
    Assert.assertTrue(sharedData.address.isIsPrimary(), "The Address is not set as primary");
  }

  @Then("^I should see the address as primary$")
  public void iShouldSeeTheAddressAsPrimary() {
    Address address = Arrays.stream(guestAddress).filter(x -> sharedData.shopperDeliveryAddress.contains(x.getAddressText())).findAny().orElse(null);
    assert address != null;
    Assert.assertTrue(address.isIsPrimary(), "The Address is not set as primary");
  }

  @Then("^I should see the address \"([^\"]*)\" as primary$")
  public void iShouldSeeTheAddressAsPrimary(String findAddress) {
    Address address = Arrays.stream(guestAddress).filter(x -> x.getAddressText().contains(findAddress)).findAny().orElse(null);
    assert address != null;
    Assert.assertTrue(address.isIsPrimary(), "The Address::" + findAddress + " is not set as primary");
  }

  @Then("^I login to signed up account to see the addresses$")
  public void iLoginToSignedUpAccountToSeeTheAddresses() throws Throwable {
    MyAddresses myAddressResponse = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address allAddress = myAddressResponse.getAddresses()[0];

    Assert.assertTrue(allAddress.getAddressText().toLowerCase().contains(sharedData.shopperDeliveryAddress.toLowerCase()), "Guest Address text is not matching with::" + sharedData.shopperDeliveryAddress.toLowerCase() + ":: as expected::" + allAddress.getAddressText().toLowerCase());
    Assert.assertTrue(allAddress.isIsPrimary(), "The address is not set as Primary");
    Assert.assertNotNull(allAddress.getPostalCode());
    Assert.assertTrue(allAddress.getAddressId() != sharedData.guestAddressId, "The Guest Address Id::" + sharedData.guestAddressId + " should be different from the shopper address id::" + allAddress.getAddressId());
  }

  @Then("^I login to signed up account to see the address information match$")
  public void iLoginToSignedUpAccountToSeeTheAddressInformationMatch() throws Throwable {
    MyAddresses myAddressResponse = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address allAddress = myAddressResponse.getAddresses()[0];

    Assert.assertNotNull(allAddress.getAddressText());
    Assert.assertTrue(allAddress.isIsPrimary(), "The address is not set as Primary");
    Assert.assertTrue(sharedData.shopperDeliveryAddress.toLowerCase().contains(allAddress.getStreet1().toLowerCase()), "Street address is not matching");
    Assert.assertTrue(sharedData.shopperDeliveryAddress.toLowerCase().contains(allAddress.getStreet2().toLowerCase()), "Street address 2 is not matching");
    Assert.assertNotNull(allAddress.getPostalCode());
    Assert.assertTrue(allAddress.getAddressId() != sharedData.guestAddressId, "The Guest Address Id::" + sharedData.guestAddressId + " should be different from the shopper address id::" + allAddress.getAddressId());
  }

  @Then("^I login to registered account to see the addresses$")
  public void iLoginToRegisteredAccountToSeeTheAddresses() throws Throwable {
    MyAddresses myAddressResponse = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address allAddress = myAddressResponse.getAddresses()[0];

    Assert.assertTrue(allAddress.getAddressText().toLowerCase().equalsIgnoreCase(sharedData.shopperDeliveryAddress.toLowerCase()), "Guest Address is not set to default::" + allAddress.getAddressText());
    Assert.assertTrue(allAddress.isIsPrimary(), "The address is not set as Primary");
    Assert.assertNotNull(allAddress.getPostalCode());
  }

  @Then("^delete the previous addresses and verify user has only 1 current address$")
  public void ideleteThePreviousAddressesAndVerify() throws Throwable {
    iDeleteThePreviousAddresses();
    MyAddresses myAddressResponse = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Assert.assertTrue(myAddressResponse.getAddresses()[0].isIsPrimary(), "The address is not set as Primary");
    Assert.assertEquals(myAddressResponse.getAddresses().length, 1, "Previous addresses not deleted");

  }

  @When("^I search for address \"([^\"]*)\" and select the (.*) address as checkout address from matching addresses$")
  public void iSearchForTheAddressAndSelect1Address(String lookupAddress, int position) throws Throwable {
    lookupAddress = Utilities.replaceMultipleandTrimSpaces(lookupAddress);

    SearchAddressResponse searchAddressResponse = searchForTheAddress(lookupAddress);
    //These assertions are to make sure there are no NULL FIELDS
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getPostcode());
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getId());
    Assert.assertNotNull(searchAddressResponse.getItems()[0].getText());

    Items[] addressItem = searchAddressResponse.getItems();

    AddressDetails addressDetailResponse = iGetTheAddressIdFromAmasId(addressItem[position - 1].getId());
    if (addressDetailResponse.getStatusCode().equals("200")) {
      Assert.assertNotNull(addressDetailResponse.getAddress());
      Assert.assertFalse(addressDetailResponse.isIsNonServiced());
      int addressId = addressDetailResponse.getAddress().getAddressId();
      String addressText = addressDetailResponse.getAddress().getAddressText();
      //This is to check if the address id set in Guest mode is different from shopper Guest Id
      CheckoutAddressResponse checkoutAddressResponse = iSetTheAddressId(addressId);
      Assert.assertNotNull(checkoutAddressResponse);

      sharedData.setDeliveryAddresses(addressId, addressText);
      sharedData.guestAddressId = addressId;

    }
  }

  @When("^verify the address added recently is set as primary in MyAccount for signed up user$")
  public void verifyPrimaryAdressInMyAccountForSignedUpUser() throws Throwable {
    MyAddresses adressesInMyAccount = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address[] addressItem = adressesInMyAccount.getAddresses();

    HashMap<Integer, String> deliveryAddress = sharedData.getDeliveryAddresses();
    int primaryAddressId = sharedData.guestAddressId;
    String primaryAddressText = deliveryAddress.get(primaryAddressId);

    Address address = Arrays.stream(addressItem).filter(x -> x.getAddressText().contains(primaryAddressText)).findAny().orElse(null);
    assert address != null;
    Assert.assertTrue(address.isIsPrimary(), "Recently added/switched address set as primary");
    //Removing an item in deliveryAddresses with old addressid and add new address id since addressid changes after signup
    if (address.isIsPrimary()) {
      sharedData.deliveryAddresses.remove(primaryAddressId);
      sharedData.setDeliveryAddresses(addressItem[0].getAddressId(), addressItem[0].getAddressText());
    }
    deliveryAddress.remove(primaryAddressId);
    deliveryAddress.put(addressItem[0].getAddressId(), primaryAddressText);

  }

  @When("^verify the address added/switched recently is set as primary in MyAccount$")
  public void verifyPrimaryAdressInMyAccount() throws Throwable {
    MyAddresses adressesInMyAccount = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address[] addressItem = adressesInMyAccount.getAddresses();

    HashMap<Integer, String> deliveryAddress = sharedData.getDeliveryAddresses();
    int primaryAddressId = sharedData.guestAddressId;
    String primaryAddressText = deliveryAddress.get(primaryAddressId);

    Address address = Arrays.stream(addressItem).filter(x -> x.getAddressId() == primaryAddressId
        && x.getAddressText().contains(primaryAddressText)).findAny().orElse(null);

    assert address != null;
    Assert.assertTrue(address.isIsPrimary(), "Recently added/switched address set as primary");

  }

  @When("^I switch the address \"([^\"]*)\" to primary address$")
  public void iSwitchAndSetThePrimary(String addressToSet) throws Throwable {

    int addressId = 0;
    HashMap<Integer, String> deliveryAddresses = sharedData.getDeliveryAddresses();

    for (Map.Entry<Integer, String> entry : deliveryAddresses.entrySet()) {
      if (entry.getValue().contains(addressToSet)) {
        addressId = entry.getKey();
      }
    }

    CheckoutAddressResponse checkoutAddressResponse = iSetTheAddressId(addressId);
    Assert.assertNotNull(checkoutAddressResponse);
    Assert.assertTrue(checkoutAddressResponse.getAddress().isIsPrimary(), "Switched address ");
    sharedData.guestAddressId = addressId;

  }

  @When("^verify all the adresses added recently are reflected in MyAccount$")
  public void verifyAllAddressInMyAccount() throws Throwable {
    MyAddresses adressesInMyAccount = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address[] addressItem = adressesInMyAccount.getAddresses();

    HashMap<Integer, String> deliveryAddress = sharedData.getDeliveryAddresses();
    int addressID;
    int counter = 0;

    while (deliveryAddress.size() > counter) {
      addressID = addressItem[counter].getAddressId();
      Assert.assertTrue(deliveryAddress.get(addressID).contains(addressItem[counter].getAddressText()), "Addresses match");
      counter++;

    }

  }

  @When("^I remove all the stored delivery addresses$")
  public void removeAllItemsInDeliveryAddresses() {
    sharedData.removeAllItemsInDeliveryAddresses();

  }

  @And("^I get the addresses for my account$")
  public void iGetTheAddressesForMyAccount() throws Throwable {
    MyAddresses adressesInMyAccount = iLoginToMyAccountToSeeTheAddresses(sharedData.authToken);
    Address[] addressItem = adressesInMyAccount.getAddresses();
    sharedData.deliveryAddressId = addressItem[0].getAddressId();
  }

}
