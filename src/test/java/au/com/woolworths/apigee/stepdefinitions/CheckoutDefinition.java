package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.context.ApigeeApplicationContext;
import au.com.woolworths.apigee.helpers.CheckoutHelper;
import au.com.woolworths.apigee.model.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class CheckoutDefinition extends CheckoutHelper {
  private final static Logger logger = Logger.getLogger("DeliveryAddressDefinition.class");

  private final ApigeeSharedData sharedData;
  private final ApigeeContainer picoContainer;

  public CheckoutDefinition(ApigeeContainer container) {
    this.sharedData = ApigeeApplicationContext.getSharedData();
    this.picoContainer = container;
  }

  @And("^I get the available \"([^\"]*)\" windows for the logged in user with storeId or addressId$")
  public void iGetAvailableWindow(String collectionMode) throws Throwable {
    CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);
    if (collectionMode.equals("Pickup")) {
      picoContainer.orderCheckoutPaymentAddress = checkoutResponse.getOrder().getPickup().getStore().getText();
      picoContainer.orderCheckoutPaymentWindowDate = checkoutResponse.getOrder().getPickup().getWindow().getDisplayDate();
      picoContainer.orderCheckoutPaymentWindowTime = checkoutResponse.getOrder().getPickup().getWindow().getDisplayTime();

    } else if (collectionMode.equals("Delivery")) {
      picoContainer.orderCheckoutDeliveryAddress = checkoutResponse.getOrder().getDelivery().getAddress().getText();
      picoContainer.orderCheckoutDeliveryWindowDate = checkoutResponse.getOrder().getDelivery().getWindow().getDisplayDate();
      picoContainer.orderCheckoutDeliveryWindowTime = checkoutResponse.getOrder().getDelivery().getWindow().getDisplayTime();
    }
    picoContainer.orderCheckoutSubtotal = checkoutResponse.getOrder().getSubtotal();
    picoContainer.orderCheckoutTotalGST = checkoutResponse.getOrder().getTotalIncludingGst();
    picoContainer.orderCheckoutPackagingFee = checkoutResponse.getOrder().getPackagingFee();
    picoContainer.orderCheckoutPackagingPreference = checkoutResponse.getOrder().getPackagingFeeLabel();

    CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();
    checkoutFulfilmentWindows[0] = Arrays.stream(checkoutResponse.getFulfilmentWindows()).filter(i -> i.getIsAvailable().equals(true))
        .findFirst().orElse(null);
    //Get Afternoon or Evening Slot
    assert checkoutFulfilmentWindows[0] != null;
    CheckoutWindowItems checkoutWindowItems = checkoutFulfilmentWindows[0].getAfternoon();
    CheckoutWindowSlots[] checkoutWindowSlots = checkoutWindowItems.getSlots();
    try {
      if (Arrays.stream(checkoutFulfilmentWindows[0].getAfternoon().getSlots()).anyMatch(CheckoutWindowSlots::isIsAvailable)) {
        checkoutWindowSlots[0] = Arrays.stream(checkoutFulfilmentWindows[0].getAfternoon().getSlots()).filter(CheckoutWindowSlots::isIsAvailable).findFirst().orElse(null);
        assert checkoutWindowSlots[0] != null;
        picoContainer.windowId = checkoutWindowSlots[0].getId();
        picoContainer.windowStartTime = checkoutWindowSlots[0].getStartTime();
      } else {
        checkoutWindowItems = checkoutFulfilmentWindows[0].getEvening();
        checkoutWindowSlots = checkoutWindowItems.getSlots();
        if (checkoutWindowSlots[0] != null) {
          picoContainer.windowId = checkoutWindowSlots[0].getId();
          picoContainer.windowStartTime = checkoutWindowSlots[0].getStartTime();
        }
      }
    } catch (Exception ex) {
      logger.info("No available checkout window available.");
    }

  }


  @Then("^I set the selected available window for the logged in user$")
  public void iSetAvailableWindow() throws Throwable {
    CheckoutResponse checkoutResponse = postSetCheckoutWindow(picoContainer.windowId, picoContainer.windowStartTime, sharedData.accessToken);
    picoContainer.packagingPreference = checkoutResponse.getDeliveryPackagingPreferences();
    picoContainer.leaveUnattended = checkoutResponse.getOrder().getLeaveUnattended().isDisableLeaveUnattended();
    Assert.assertEquals("Selected window is not set", checkoutResponse.getResults().getSetDeliveryWindow().getHttpStatusCode(), 200);
  }

  @And("^I validate the default selected packaging preference for Delivery is (.*)$")
  public void iValidateDefaultPackagingPreference(String packagingPref) {
    CheckoutPackagingPreferencesResponse[] checkoutPackagingPreferences = picoContainer.packagingPreference;
    Assert.assertTrue("Packaging Preference not set correctly", Objects.requireNonNull(Arrays.stream(checkoutPackagingPreferences).filter(i -> i.getName().contains(packagingPref)).findFirst().orElse(null)).isIsSelected());
  }

  @Then("^I validate that user is able to select (.*) as packaging preference$")
  public void iSelectPackagingPreference(String packagingPref) throws Throwable {
    int packagingID;
    if (packagingPref.contains("Reusable")) {
      packagingID = Objects.requireNonNull(Arrays.stream(picoContainer.packagingPreference).filter(i -> i.getName().contains("Reusable")).findFirst().orElse(null)).getId();
      CheckoutResponse checkoutResponse = postSetPackagingPreference(packagingID, sharedData.accessToken);
      Assert.assertEquals("Packaging Preference is not set", checkoutResponse.getResults().getSetPackagingOption().getHttpStatusCode(), 200);
      Assert.assertTrue("Packaging Preference not set correctly", Objects.requireNonNull(Arrays.stream(checkoutResponse.getDeliveryPackagingPreferences()).filter(i -> i.getName().contains(packagingPref)).findFirst().orElse(null)).isIsSelected());

    } else if (packagingPref.contains("BYO")) {
      packagingID = Objects.requireNonNull(Arrays.stream(picoContainer.packagingPreference).filter(i -> i.getName().contains("BYO")).findFirst().orElse(null)).getId();
      CheckoutResponse checkoutResponse = postSetPackagingPreference(packagingID, sharedData.accessToken);
      Assert.assertEquals("Packaging Preference is not set", checkoutResponse.getResults().getSetPackagingOption().getHttpStatusCode(), 200);
      Assert.assertTrue("Packaging Preference not set correctly", Objects.requireNonNull(Arrays.stream(checkoutResponse.getDeliveryPackagingPreferences()).filter(i -> i.getName().contains(packagingPref)).findFirst().orElse(null)).isIsSelected());

    }
  }

  @Then("^I validate the selected \"([^\"]*)\" store and selected windows$")
  public void iValidateTheSelectedStoreAndSelectedWindows(String collectionMode) {
    if (collectionMode.equals("Pickup")) {
      Assert.assertEquals("Pick up store is verified", picoContainer.orderCheckoutPaymentAddress, picoContainer.orderCheckoutSummaryPaymentAddress);
      Assert.assertEquals("Pick up window is verified", picoContainer.orderCheckoutPaymentWindowDate, picoContainer.orderCheckoutSummaryPaymentWindowDate);
      Assert.assertEquals("Pick up time is verified", picoContainer.orderCheckoutPaymentWindowTime, picoContainer.orderCheckoutSummaryPaymentWindowTime);
    } else if (collectionMode.equals("Delivery")) {
      Assert.assertEquals("Delivery address is verified", picoContainer.orderCheckoutDeliveryAddress, picoContainer.orderCheckoutSummaryDeliveryPaymentAddress);
      Assert.assertEquals("Delivery window is verified", picoContainer.orderCheckoutDeliveryWindowDate, picoContainer.orderCheckoutSummaryDeliveryPaymentWindowDate);
      Assert.assertEquals("Delivery time is verified", picoContainer.orderCheckoutDeliveryWindowTime, picoContainer.orderCheckoutSummaryDeliveryPaymentWindowTime);
    }
  }

  @Then("^I validate the product subtotal and total GST$")
  public void iValidateTheProductSubtotalAndTotalGST() {
    Assert.assertEquals("Pick up subtotal is verified", picoContainer.orderCheckoutSubtotal, picoContainer.orderCheckoutPaymentSubtotal);
    Assert.assertEquals("Pick up totalGST is verified", picoContainer.orderCheckoutTotalGST, picoContainer.orderCheckoutPaymentTotalGST);
  }

  @And("^I validate the packaging fee and preference$")
  public void iValidateThePackagingFeeAndPreference() {
    Assert.assertEquals("Pick up packaging fee is verified", picoContainer.orderCheckoutPackagingFee, picoContainer.orderCheckoutPaymentPackagingFee);
    Assert.assertEquals("Pick up packaging preference is verified", picoContainer.orderCheckoutPackagingPreference, picoContainer.orderCheckoutPaymentPackagingPreference);
  }

  @When("^I get the checkout summary details for the \"([^\"]*)\" order$")
  public void iGetTheCheckoutSummaryDetailsForThePickupOrder(String collectionMode) throws Throwable {
    CheckoutPaymentSummaryResponse checkoutPaymentResponse = getCheckoutPaymentResponse(sharedData.accessToken);
    if (collectionMode.equals("Pickup")) {
      picoContainer.orderCheckoutSummaryPaymentAddress = checkoutPaymentResponse.getOrder().getPickup().getStore().getText();
      picoContainer.orderCheckoutSummaryPaymentWindowDate = checkoutPaymentResponse.getOrder().getPickup().getWindow().getDisplayDate();
      picoContainer.orderCheckoutSummaryPaymentWindowTime = checkoutPaymentResponse.getOrder().getPickup().getWindow().getDisplayTime();
    } else if (collectionMode.equals("Delivery")) {
      picoContainer.orderCheckoutSummaryDeliveryPaymentAddress = checkoutPaymentResponse.getOrder().getDelivery().getAddress().getText();
      picoContainer.orderCheckoutSummaryDeliveryPaymentWindowDate = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getDisplayDate();
      picoContainer.orderCheckoutSummaryDeliveryPaymentWindowTime = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getDisplayTime();
    }
    picoContainer.orderCheckoutPaymentSubtotal = checkoutPaymentResponse.getOrder().getSubtotal();
    picoContainer.orderCheckoutPaymentTotalGST = checkoutPaymentResponse.getOrder().getTotalIncludingGst();
    picoContainer.orderCheckoutPaymentPackagingFee = checkoutPaymentResponse.getOrder().getPackagingFee();
    picoContainer.orderCheckoutPaymentPackagingPreference = checkoutPaymentResponse.getOrder().getPackagingFeeLabel();
  }

  @And("^I get the available Delivery Now window to reserve them and validate the leave unattended flag$")
  public void iGetTheAvailableDeliveryNowWindowToReserveThemAndValidateTheLeaveUnattendedFlag() throws Throwable {
    CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);
    CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();

    //Assert Delivery Now Window is not Null
    Assert.assertNotNull("Delivery Now window is not available", checkoutFulfilmentWindows[0].getDeliveryNow());
    picoContainer.windowId = checkoutFulfilmentWindows[0].getDeliveryNow().getId();
    picoContainer.windowStartTime = checkoutFulfilmentWindows[0].getDeliveryNow().getStartTime();
    CheckoutResponse postCheckoutResponse = postSetCheckoutWindow(picoContainer.windowId, picoContainer.windowStartTime, sharedData.accessToken);

    //Assert the leave unattended flag is true when delivery now window is selected
    Assert.assertTrue("Disable Leave Unattended flag is not set to True", postCheckoutResponse.getOrder().getLeaveUnattended().isDisableLeaveUnattended());
    //Assert the warning message field contains the exact message
    Assert.assertEquals("Warning message is incorrect", postCheckoutResponse.getOrder().getLeaveUnattended().getWarningMessage(), "A signature is required for Delivery Now orders.");
  }

  @And("^I validate the leave unattended flag to be disabled$")
  public void iValidateTheLeaveUnattendedFlag() {
    Assert.assertFalse("Disable Leave Unattended flag is not set to False", picoContainer.leaveUnattended);
  }
}

