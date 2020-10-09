package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.AddressHelper;
import au.com.woolworths.helpers.trader.ShopperHelper;
import au.com.woolworths.model.trader.CheckoutAddressResponse;
import au.com.woolworths.model.trader.PickupResponse;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.logging.Logger;

public class PickupDefinition extends AddressHelper {

  private final static Logger logger = Logger.getLogger("PickupDefinition.class");
  private PickupResponse[] pickupResponse;
  private final ShopperHelper shopperHelper;


  public PickupDefinition() {
    shopperHelper = new ShopperHelper();
  }

  @When("^apigee calls get pickup stores for the fulfilment store id (.*)$")
  public void iSearchForTheAddressWithoutSavingForFutureLookup(String pickupStoreID) throws Throwable {
    pickupResponse = iGetThePickupStore(sharedData.deliveryAddressId);
  }

  @Then("^apigee successfully authenticate to trader public api endpoint as guest shopper with selected pickup store and (.*)$")
  public void verifySelectedPickupStore(String deliveryMethod) {
    PickupResponse response = Arrays.asList(pickupResponse).stream().findFirst().get();
    Assert.assertTrue(response.getDeliveryMethod().equals(deliveryMethod), "Pickup mode not set");
    Assert.assertFalse(response.getAddressText().isEmpty(), "Address text is empty");
  }

  @And("^I select the \"1\" pickup store as checkout pickup store from matching stores for the logged in user$")
  public void iSelectPickupStore() throws Throwable {
    PickupResponse[] pickupResponses = sharedData.pickupResponse;
    sharedData.fulfilmentStoreAddressId = pickupResponses[0].getAddressId();
    sharedData.fulfilmentStoreId = Integer.parseInt(pickupResponses[0].getStoreNumber());
    CheckoutAddressResponse checkoutAddressResponse = shopperHelper.iSetTheFulfilmentStoreId(sharedData.fulfilmentStoreAddressId);
    sharedData.fulfilmentMethod = checkoutAddressResponse.getFulfilmentMethod();
    Assert.assertNotNull(checkoutAddressResponse);
    Assert.assertTrue(checkoutAddressResponse.isIsSuccessful());
  }

  @Then("^I validate fulfilmentMethod match to FulfilmentMethod for pickup mode stores$")
  public void iValidateFulfilmentForPickup() throws Throwable {
    //Validate fulfilment method matches for Pickup (Driveup and Instore)
    CheckoutAddressResponse checkoutAddressResponse = shopperHelper.iGETTheFulfilmentStoreId();
    Assert.assertNotNull(checkoutAddressResponse);
    Assert.assertTrue(sharedData.fulfilmentMethod.matches(checkoutAddressResponse.getFulfilmentMethod()));
  }
}
