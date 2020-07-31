package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.CheckoutHelper;
import au.com.woolworths.model.apigee.checkout.*;
import au.com.woolworths.model.apigee.payment.PayCardCaptureResponse;
import au.com.woolworths.model.apigee.payment.PayIntrumentsRepsonse;
import au.com.woolworths.utils.TestProperties;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Logger;

public class CheckoutDefinition extends CheckoutHelper {
  private final static Logger logger = Logger.getLogger("DeliveryAddressDefinition.class");


  @And("^I get the available windows for the logged in user with storeId or addressId$")
  public void iGetAvailableWindow() throws Throwable {
    CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);
    CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();
    checkoutFulfilmentWindows[0] = Arrays.stream(checkoutResponse.getFulfilmentWindows()).filter(i -> i.getIsAvailable().equals(true))
        .findFirst().orElse(null);
    //Get Afternoon or Evening Slot
    assert checkoutFulfilmentWindows[0] != null;
    CheckoutWindowItems checkoutWindowItems = checkoutFulfilmentWindows[0].getAfternoon();
    CheckoutWindowSlots[] checkoutWindowSlots = checkoutWindowItems.getSlots();
    try {
      sharedData.orderCheckoutSelectedWindowDate = checkoutFulfilmentWindows[0].getDate();
      if (Arrays.stream(checkoutFulfilmentWindows[0].getAfternoon().getSlots()).anyMatch(CheckoutWindowSlots::isIsAvailable)) {
        checkoutWindowSlots[0] = Arrays.stream(checkoutFulfilmentWindows[0].getAfternoon().getSlots()).filter(CheckoutWindowSlots::isIsAvailable).findFirst().orElse(null);
        assert checkoutWindowSlots[0] != null;
        sharedData.windowId = checkoutWindowSlots[0].getId();
        sharedData.orderCheckoutPaymentWindowTime = checkoutWindowSlots[0].getStartTime();
      } else {
        checkoutWindowItems = checkoutFulfilmentWindows[0].getEvening();
        checkoutWindowSlots = checkoutWindowItems.getSlots();
        if (checkoutWindowSlots[0] != null) {
          sharedData.windowId = checkoutWindowSlots[0].getId();
          sharedData.orderCheckoutPaymentWindowTime = checkoutWindowSlots[0].getStartTime();
        }
      }
    } catch (Exception ex) {
      logger.info("No available checkout window available.");
    }

  }

  @Then("^I reserve the available window for the selected \"([^\"]*)\"$")
  public void iReserveTheAvailableWindowForTheSelected(String collectionMode) throws Throwable {
    CheckoutResponse checkoutResponse = postSetCheckoutWindow(sharedData.windowId, sharedData.orderCheckoutPaymentWindowTime, sharedData.accessToken);
    if (collectionMode.equals("Pickup")) {
      sharedData.orderCheckoutPaymentAddress = checkoutResponse.getOrder().getPickup().getStore().getText();
      sharedData.orderCheckoutSelectedWindowDate = checkoutResponse.getOrder().getPickup().getWindow().getDisplayDate();
      sharedData.orderCheckoutPaymentWindowTime = checkoutResponse.getOrder().getPickup().getWindow().getDisplayTime();
    } else if (collectionMode.equals("Delivery")) {
      sharedData.orderCheckoutPaymentAddress = checkoutResponse.getOrder().getDelivery().getAddress().getText();
      sharedData.orderCheckoutSelectedWindowDate = checkoutResponse.getOrder().getDelivery().getWindow().getDisplayDate();
      sharedData.orderCheckoutPaymentWindowTime = checkoutResponse.getOrder().getDelivery().getWindow().getDisplayTime();
    }
    sharedData.orderCheckoutSubtotal = checkoutResponse.getOrder().getSubtotal();
    sharedData.orderCheckoutTotalGST = checkoutResponse.getOrder().getTotalIncludingGst();
    sharedData.orderCheckoutPackagingFee = checkoutResponse.getOrder().getPackagingFee();
    sharedData.orderCheckoutPackagingPreference = checkoutResponse.getOrder().getPackagingFeeLabel();
    sharedData.packagingPreference = checkoutResponse.getDeliveryPackagingPreferences();
    sharedData.leaveUnattended = checkoutResponse.getOrder().getLeaveUnattended().isDisableLeaveUnattended();

    Assert.assertEquals("Selected window is not set", checkoutResponse.getResults().getSetDeliveryWindow().getHttpStatusCode(), 200);
  }

  @And("^I validate the default selected packaging preference for Delivery is (.*)$")
  public void iValidateDefaultPackagingPreference(String packagingPref) {
    CheckoutPackagingPreferencesResponse[] checkoutPackagingPreferences = sharedData.packagingPreference;
    Assert.assertTrue("Packaging Preference not set correctly", Objects.requireNonNull(Arrays.stream(checkoutPackagingPreferences).filter(i -> i.getName().contains(packagingPref)).findFirst().orElse(null)).isIsSelected());
  }

  @Then("^I validate that user is able to select (.*) as packaging preference$")
  public void iSelectPackagingPreference(String packagingPref) throws Throwable {
    int packagingID;
    if (packagingPref.contains("Reusable")) {
      packagingID = Objects.requireNonNull(Arrays.stream(sharedData.packagingPreference).filter(i -> i.getName().contains("Reusable")).findFirst().orElse(null)).getId();
      CheckoutResponse checkoutResponse = postSetPackagingPreference(packagingID, sharedData.accessToken);
      Assert.assertEquals("Packaging Preference is not set", checkoutResponse.getResults().getSetPackagingOption().getHttpStatusCode(), 200);
      Assert.assertTrue("Packaging Preference not set correctly", Objects.requireNonNull(Arrays.stream(checkoutResponse.getDeliveryPackagingPreferences()).filter(i -> i.getName().contains(packagingPref)).findFirst().orElse(null)).isIsSelected());

    } else if (packagingPref.contains("BYO")) {
      packagingID = Objects.requireNonNull(Arrays.stream(sharedData.packagingPreference).filter(i -> i.getName().contains("BYO")).findFirst().orElse(null)).getId();
      CheckoutResponse checkoutResponse = postSetPackagingPreference(packagingID, sharedData.accessToken);
      Assert.assertEquals("Packaging Preference is not set", checkoutResponse.getResults().getSetPackagingOption().getHttpStatusCode(), 200);
      Assert.assertTrue("Packaging Preference not set correctly", Objects.requireNonNull(Arrays.stream(checkoutResponse.getDeliveryPackagingPreferences()).filter(i -> i.getName().contains(packagingPref)).findFirst().orElse(null)).isIsSelected());

    }
  }

  @Then("^I validate the selected \"([^\"]*)\" and selected windows$")
  public void iValidateTheSelectedStoreAndSelectedWindows(String collectionMode) {
    Assert.assertEquals("Selected " + collectionMode + " address doesn't match ", sharedData.orderCheckoutPaymentAddress, sharedData.orderCheckoutSummaryPaymentAddress);
    Assert.assertEquals("Reserved window date doesn't match", sharedData.orderCheckoutSelectedWindowDate, sharedData.orderCheckoutSummaryPaymentWindowDate);
    Assert.assertEquals("Reserved time doesn't match", sharedData.orderCheckoutPaymentWindowTime, sharedData.orderCheckoutSummaryPaymentWindowTime);
  }

  @Then("^I validate the product subtotal and total GST$")
  public void iValidateTheProductSubtotalAndTotalGST() {
    Assert.assertEquals("Order subtotal is not matching", sharedData.orderCheckoutSubtotal, sharedData.orderCheckoutPaymentSubtotal);
    Assert.assertEquals("Order totalGST is not matching", sharedData.orderCheckoutTotalGST, sharedData.orderCheckoutPaymentTotalGST);
  }

  @And("^I validate the packaging fee and preference$")
  public void iValidateThePackagingFeeAndPreference() {
    Assert.assertEquals("Packaging fee is not matching", sharedData.orderCheckoutPackagingFee, sharedData.orderCheckoutPaymentPackagingFee);
    Assert.assertEquals("Packaging preference is not matching", sharedData.orderCheckoutPackagingPreference, sharedData.orderCheckoutPaymentPackagingPreference);
  }

  @When("^I get the checkout summary details for the \"([^\"]*)\" order$")
  public void iGetTheCheckoutSummaryDetailsForThePickupOrder(String collectionMode) throws Throwable {
    CheckoutPaymentSummaryResponse checkoutPaymentResponse = getCheckoutPaymentResponse(sharedData.accessToken);
    if (collectionMode.equals("Pickup")) {
      sharedData.orderCheckoutSummaryPaymentAddress = checkoutPaymentResponse.getOrder().getPickup().getStore().getText();
      sharedData.orderCheckoutSummaryPaymentWindowDate = checkoutPaymentResponse.getOrder().getPickup().getWindow().getDisplayDate();
      sharedData.orderCheckoutSummaryPaymentWindowTime = checkoutPaymentResponse.getOrder().getPickup().getWindow().getDisplayTime();
    } else if (collectionMode.equals("Delivery")) {
      sharedData.orderCheckoutSummaryPaymentAddress = checkoutPaymentResponse.getOrder().getDelivery().getAddress().getText();
      sharedData.orderCheckoutSummaryPaymentWindowDate = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getDisplayDate();
      sharedData.orderCheckoutSummaryPaymentWindowTime = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getDisplayTime();
    }
    sharedData.orderCheckoutPaymentSubtotal = checkoutPaymentResponse.getOrder().getSubtotal();
    sharedData.orderCheckoutPaymentTotalGST = checkoutPaymentResponse.getOrder().getTotalIncludingGst();
    sharedData.orderCheckoutPaymentPackagingFee = checkoutPaymentResponse.getOrder().getPackagingFee();
    sharedData.orderCheckoutPaymentPackagingPreference = checkoutPaymentResponse.getOrder().getPackagingFeeLabel();
  }

  @And("^I get the available Delivery Now window to reserve them and validate the leave unattended flag$")
  public void iGetTheAvailableDeliveryNowWindowToReserveThemAndValidateTheLeaveUnattendedFlag() throws Throwable {
    CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);
    CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();

    //Assert Delivery Now Window is not Null
    Assert.assertNotNull("Delivery Now window is not available", checkoutFulfilmentWindows[0].getDeliveryNow());
    sharedData.windowId = checkoutFulfilmentWindows[0].getDeliveryNow().getId();
    sharedData.orderCheckoutPaymentWindowTime = checkoutFulfilmentWindows[0].getDeliveryNow().getStartTime();
    CheckoutResponse postCheckoutResponse = postSetCheckoutWindow(sharedData.windowId, sharedData.orderCheckoutPaymentWindowTime, sharedData.accessToken);

    //Assert the leave unattended flag is true when delivery now window is selected
    Assert.assertTrue("Disable Leave Unattended flag is not set to True", postCheckoutResponse.getOrder().getLeaveUnattended().isDisableLeaveUnattended());
    //Assert the warning message field contains the exact message
    Assert.assertEquals("Warning message is incorrect", postCheckoutResponse.getOrder().getLeaveUnattended().getWarningMessage(), "A signature is required for Delivery Now orders.");
  }

  @And("^I validate the leave unattended flag to be enabled$")
  public void iValidateTheLeaveUnattendedFlag() {
    Assert.assertFalse("Disable Leave Unattended flag is not set to False", sharedData.leaveUnattended);
  }

  @And("^I make a payment using (.*)$")
  public void iMakeAPaymentUsing(String paymentMode) throws Throwable {
    PayIntrumentsRepsonse payIntrumentsRepsonse = getPayInstruments();
    PayCardCaptureResponse payCardCaptureResponse = getCardCapture();
    String sessionID;
    if (System.getProperty("env").equals("uat"))
    {
      sessionID = payCardCaptureResponse.getCardCaptureURL().replace(TestProperties.get("iFRAME_UAT_URL"), "");
      String instrumentId = postiFrameCardDetails(sessionID).getItem().getItemID();
      float amount = sharedData.orderCheckoutPaymentTotalGST;
      postDigitalPay(instrumentId, String.valueOf(amount));
    } else {
      logger.info("There is an existing issue with Digipay in Test environment, will be updated once the issue is addressed");
      //**There are digipay issues in TEST environment**//
      //sessionID=payCardCaptureResponse.getCardCaptureURL().replace(TestProperties.get("iFRAME_TEST_URL"),"");
    }
  }
}
