package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.CheckoutHelper;
import au.com.woolworths.model.trader.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;

import java.util.Arrays;
import java.util.logging.Logger;

public class CheckoutDefinition extends CheckoutHelper {

  private final static Logger logger = Logger.getLogger("CheckoutDefinition.class");

  @When("^I do a V2 checkout then I should see the leave unattended option \"([^\"]*)\" and turned \"([^\"]*)\"$")
  public void iDoAV2CheckoutThenIShouldSeeTheLeaveUnattendedOptionAndTurned(String disableLeaveUnattended, String canLeaveUnattended) throws Throwable {
    //When I do a V2 checkout then I should see the leave unattended option "ENABLED" and turned "ON"
    CheckoutV2Response checkoutResponse = getCheckoutResponseBasedOnTrolleyItems();
    Assert.assertTrue(checkoutResponse.getOrder().isShowLeaveUnattended(), "Show Leave Unattended Option is displayed");
    if (disableLeaveUnattended.equalsIgnoreCase("ENABLED")) {
      Assert.assertFalse(checkoutResponse.getOrder().isDisableLeaveUnattended(), "Disable Leave Unattended should be false");
    } else {
      Assert.assertTrue(checkoutResponse.getOrder().isDisableLeaveUnattended(), "Disable Leave Unattended should be true");
    }

    if (canLeaveUnattended.equalsIgnoreCase("ON")) {
      Assert.assertTrue(checkoutResponse.getOrder().isCanLeaveUnattended(), "Can Leave Unattended should be true");
    } else {
      Assert.assertFalse(checkoutResponse.getOrder().isCanLeaveUnattended(), "Can Leave Unattended should be false");
      Assert.assertTrue(checkoutResponse.getWarningMessage().contains("Your order contains restricted items. Please ensure someone over 18 with valid photo ID is available to sign for the delivery."), "Restricted items warning message is not coming as expected");
    }

  }

  @Then("^I can leave the products unattended$")
  public void iCanLeaveTheProductsUnattended() throws Throwable {
    LeaveUnattended leaveUnattended = updateCanLeaveUnattended(true);
    Assert.assertTrue(leaveUnattended.isSavedCanLeaveUnattended(), "Can Leave Unattended should be true");

  }

  @Then("^I could not leave the products unattended$")
  public void iCouldNotLeaveTheProductsUnattended() throws Throwable {
    LeaveUnattended leaveUnattended = updateCanLeaveUnattended(false);
    Assert.assertFalse(leaveUnattended.isSavedCanLeaveUnattended(), "Can Leave Unattended should be false");

  }

  @Then("^I select the delivery packaging options available for the shopper to \"([^\"]*)\"$")
  public void iSelectTheDeliveryPackagingOptionsAvailableForTheShopper(String deliveryOption) throws Throwable {
    PackagingPreferences packagingPreferenceResponse = getDeliveryPackagingPreferenceFor();
    for (DeliveryPackagingPreferences packagingPreferences : packagingPreferenceResponse.getDeliveryPackagingPreferences()) {
      if (packagingPreferences.getName().toLowerCase().equals(deliveryOption.toLowerCase()) && !packagingPreferences.isIsSelected()) {
        PackagingPreferences updatedPreferenceResponse = updateDeliveryPackagingPreference(packagingPreferences.getId());
        DeliveryPackagingPreferences preference = Arrays.stream(updatedPreferenceResponse.getDeliveryPackagingPreferences()).filter(x -> x.getId() == packagingPreferences.getId()).findAny().orElse(null);
        assert preference != null;
        Assert.assertTrue(preference.isIsSelected(), "The Preference is not updated to::" + deliveryOption);
      }
    }
  }

  @Then("^I do a V2 checkout and should see crate to bench packaging unavailable$")
  public void iDoAV2CheckoutAndShouldSeeCrateToBenchPackagingUnavailable() throws Throwable {
    CheckoutV2Response v2Response = getCheckoutResponseBasedOnTrolleyItems();
    Assert.assertTrue(v2Response.getOrder().getOnLeaveUnattendedMessage().getTitle().contains("Crate to Bench Packaging Unavailable"), "Crate to Bench Title is not coming as expected");
    Assert.assertTrue(v2Response.getOrder().getOnLeaveUnattendedMessage().getMessage().contains("we cannot provide the crate to bench service if you choose to have an order left unattended"), "Crate to Bench Message is not coming as expected");
  }

  @Then("^I do a V3 checkout and should see crate to bench packaging unavailable$")
  public void iDoAV3CheckoutAndShouldSeeCrateToBenchPackagingUnavailable() throws Throwable {
    CheckoutV3Response v3Response = getV3CheckoutResponseBasedOnTrolleyItems();
    Assert.assertTrue(v3Response.getOrder().getOnLeaveUnattendedMessage().getTitle().contains("Crate to Bench Packaging Unavailable"), "Crate to Bench Title is not coming as expected");
    Assert.assertTrue(v3Response.getOrder().getOnLeaveUnattendedMessage().getMessage().contains("we cannot provide the crate to bench service if you choose to have an order left unattended"), "Crate to Bench Message is not coming as expected");
  }

  @And("^I set the selected available delivery window for the logged in user$")
  public void iSetAvailableDeliveryWindow() throws Throwable {
    CheckoutSetWindowsResponseV2 responseV2 = setCheckoutDeliveryWindow(sharedData.checkoutDetails.get("selectedDate"), sharedData.checkoutDetails.get("selectedSlotId"));
    Assert.assertTrue(responseV2.getIsSuccessful(), "Delivery Window was not successfully");
    if (responseV2.getIsSuccessful()) {
      Assert.assertNull(responseV2.getMessage(), "Message is not null");
      Assert.assertNull(responseV2.getResponseStatus(), "Response status is not null");
    }
  }

  @And("^I get the available (.*) windows for the logged in user with storeId or addressId")
  public void iGetAvailableWindows(String shoppingMode) throws Throwable {
    CheckoutV2DeliveryWindowsResponse v2DeliveryWindowsResponse;
    CheckoutDeliveryWindowItems[] checkoutWindows = null;
    CheckoutWindowSlots[] checkoutWindowSlots;
    CheckoutDeliveryWindowItems window;
    CheckoutWindowSlots slot;

    if (shoppingMode.equalsIgnoreCase("pickup")) {
      v2DeliveryWindowsResponse = getCheckoutPickupWindows(sharedData.fulfilmentStoreId);
      checkoutWindows = v2DeliveryWindowsResponse.getItems();
    } else if (shoppingMode.equalsIgnoreCase("delivery")) {
      v2DeliveryWindowsResponse = getCheckoutDeliveryWindows(sharedData.deliveryAddressIdContainer);
      checkoutWindows = v2DeliveryWindowsResponse.getItems();
    }
    assert checkoutWindows != null;
    window = Arrays.stream(checkoutWindows).filter(CheckoutDeliveryWindowItems::isAvailable)
        .findFirst().orElseThrow(() -> new Exception("Window Is Not Available"));
    checkoutWindowSlots = window.getSlots();
    slot = Arrays.stream(checkoutWindowSlots).filter(i -> i.getStatus().equals("available"))
        .findFirst().orElseThrow(() -> new Exception("Slot Is Not Available"));
    sharedData.checkoutDetails.put("selectedDate", window.getDate());
    sharedData.checkoutDetails.put("selectedSlotId", slot.getId().toString());
    sharedData.checkoutDetails.put("selectedStartTime", slot.getStartTime());
    sharedData.checkoutDetails.put("selectedEndTime", slot.getEndTime());
  }

  @Then("^I validate that the V3Checkout returns the selected window for the logged in user$")
  public void iValidateV3CheckoutSelectedWindowDetails() throws Throwable {
    CheckoutV3Response checkoutV3Response = getV3CheckoutResponseBasedOnTrolleyItems();
    CheckoutWindowResponseV3 checkoutWindowResponseV3 = checkoutV3Response.getWindow();
    if (checkoutV3Response.getWindow() != null) {
      Assert.assertTrue(checkoutWindowResponseV3.getStartTime().matches(sharedData.checkoutDetails.get("selectedStartTime")));
      Assert.assertTrue(checkoutWindowResponseV3.getEndTime().matches(sharedData.checkoutDetails.get("selectedEndTime")));
      Assert.assertEquals(Integer.parseInt(sharedData.checkoutDetails.get("selectedSlotId")), (int) checkoutWindowResponseV3.getId());
    } else {
      logger.info("Not able to reserve window");
    }

  }

  @And("^I validate that the V3Checkout returns the correct order details for the logged in user$")
  public void iValidateV3CheckoutSelectedOrderDetails() throws Throwable {
    CheckoutV3Response checkoutV3Response = getV3CheckoutResponseBasedOnTrolleyItems();
    AvailableOrderItems[] availableOrderItemsResponse = checkoutV3Response.getOrder().getAvailableOrderItems();

  }

  @Then("^I validate that the fulfilmentMethod and DeliveryMethod match to Courier for (.*) mode in V3Checkout response$")
  public void iValidateV3CheckoutFulfillmentMethod(String shoppingMode) throws Throwable {
    if (shoppingMode.equalsIgnoreCase("pickup")) {

    } else if (shoppingMode.equalsIgnoreCase("delivery")) {
      CheckoutV3Response checkoutV3Response = getV3CheckoutResponseBasedOnTrolleyItems();
      Assert.assertTrue(checkoutV3Response.getFulfilmentMethod().equalsIgnoreCase("Courier"), "Fullfillment method doesn't match Courier");
      Assert.assertTrue(checkoutV3Response.getOrder().getDeliveryMethod().equalsIgnoreCase("Courier"), "Delivery method doesn't match Courier");
    }
  }

  @Then("^I clear the (.*) details")
  public void clearDetails(String param) {
    if (param.equalsIgnoreCase("checkout")) {
      sharedData.checkoutDetails.clear();
    }
  }

  @Then("^I make a checkout to place an order")
  public void iMakeCheckout() throws Throwable {
    Thread.sleep(6000);
    CheckoutV3Response checkoutV3Response = getV3CheckoutResponseBasedOnTrolleyItems();
    CheckoutWindowResponseV3 checkoutWindowResponseV3 = checkoutV3Response.getWindow();
    Order checkoutOrderResponseV3 = checkoutV3Response.getOrder();
    if (checkoutV3Response.getWindow() != null) {
      sharedData.checkoutDetails.put("selectedDate", checkoutWindowResponseV3.getWindowDate());
      sharedData.checkoutDetails.put("selectedSlotId", checkoutWindowResponseV3.getId().toString());
    }
    sharedData.checkoutDetails.put("DeliveryStreet1", checkoutOrderResponseV3.getDeliveryStreet1());
    sharedData.checkoutDetails.put("DeliveryStreet2", checkoutOrderResponseV3.getDeliveryStreet2());
    sharedData.checkoutDetails.put("DeliveryPostalCode", checkoutOrderResponseV3.getDeliveryPostalCode());
    sharedData.checkoutDetails.put("PackagingFeeLabel", checkoutOrderResponseV3.getPackagingFeeLabel());
    sharedData.checkoutDetails.put("Subtotal", checkoutOrderResponseV3.getSubtotal());
    sharedData.checkoutDetails.put("BalanceToPay", checkoutOrderResponseV3.getBalanceToPay());

  }

  @Then("^I do a V2 checkout and should see \"([^\"]*)\" packaging selected$")
  public void iDoAV2CheckoutAndVerifySelectedPackaging(String packagingOption) throws Throwable {
    CheckoutV2Response v2Response = getCheckoutResponseBasedOnTrolleyItems();
    Assert.assertTrue(v2Response.getOrder().getPackagingFeeLabel().contains(packagingOption), "Reusable Packaging Option not selected");

  }

  @Then("^I do a V3 checkout and should see \"([^\"]*)\" packaging selected$")
  public void iDoAV3CheckoutAndVerifySelectedPackaging(String packagingOption) throws Throwable {
    CheckoutV3Response v3Response = getV3CheckoutResponseBasedOnTrolleyItems();
    Assert.assertTrue(v3Response.getOrder().getPackagingFeeLabel().contains(packagingOption), "Reusable Packaging Option not selected");

  }

  @And("^I get the available delivery now windows for the logged in user$")
  public void iGetTheAvailableDeliveryNowWindowsForTheLoggedInUser() throws Throwable {
    CheckoutV3DeliveryWindows[] checkoutV3DeliveryWindows;
    CheckoutV3DeliverySlots[] checkoutV3DeliverySlots;
    CheckoutV3DeliverySlots slot;
    checkoutV3DeliveryWindows = getCheckoutDeliveryNowWindows();
    checkoutV3DeliverySlots = Arrays.stream(checkoutV3DeliveryWindows).filter(CheckoutV3DeliveryWindows::isAvailable).findFirst().orElseThrow(() -> new Exception("Delivery Now Window Is Not Available")).getSlots();
    slot = Arrays.stream(checkoutV3DeliverySlots).filter(s -> s.getExpressDeliveryStatus().contains("Open")).filter(CheckoutV3DeliverySlots::isIsExpress).findFirst().orElseThrow(() -> new Exception("No Slots Available"));
    sharedData.deliveryNowDetails.put("selectedSlotId", slot.getId().toString());
    sharedData.deliveryNowDetails.put("selectedStartTime", slot.getStartTime());
  }

  @And("^I set the selected available delivery now window for the logged in user$")
  public void iSetTheSelectedAvailableDeliveryNowWindowForTheLoggedInUser() throws Throwable {
    CheckoutSetWindowsResponseV3 responseV3 = setCheckoutDeliveryNowWindow(sharedData.deliveryNowDetails.get("selectedStartTime"), sharedData.deliveryNowDetails.get("selectedSlotId"));
    Assert.assertTrue(responseV3.isIsSuccessful(), "Setup: Delivery Now Window was not successful");
  }

  @Then("^I validate that the V(\\d+)Checkout returns the selected window for the delivery now user$")
  public void iValidateThatTheVCheckoutReturnsTheSelectedWindowForTheDeliveryNowUser(int arg0) throws Throwable {
    CheckoutV3Response checkoutV3Response = getV3CheckoutResponseBasedOnTrolleyItems();
    CheckoutWindowResponseV3 checkoutWindowResponseV3 = checkoutV3Response.getWindow();
    assert checkoutV3Response.getWindow() != null;
    Assert.assertEquals(Integer.parseInt(sharedData.deliveryNowDetails.get("selectedSlotId")), (int) checkoutWindowResponseV3.getId());
    Assert.assertTrue(checkoutWindowResponseV3.getStartTime().matches(sharedData.deliveryNowDetails.get("selectedStartTime")));
    Assert.assertTrue(checkoutV3Response.getOrder().isDisableLeaveUnattended());

  }
}


