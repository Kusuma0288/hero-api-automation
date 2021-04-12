package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.helpers.apigee.CheckoutHelper;
import au.com.woolworths.model.apigee.checkout.*;
import au.com.woolworths.model.apigee.payment.*;
import au.com.woolworths.utils.TestProperties;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import junit.framework.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CheckoutDefinition extends CheckoutHelper {
  private final static Logger logger = Logger.getLogger("DeliveryAddressDefinition.class");


  @And("^I get the available windows for the logged in user with storeId or addressId$")
  public void iGetAvailableWindow() throws Throwable {
    CheckoutResponse checkoutResponse = getCheckoutResponse(sharedData.accessToken);
    CheckoutFulfilmentWindows[] checkoutFulfilmentWindows = checkoutResponse.getFulfilmentWindows();
    List<CheckoutFulfilmentWindows> checkoutWindows = Arrays.stream(checkoutFulfilmentWindows).filter(i -> i.getIsAvailable().equals(true))
        .collect(Collectors.toList());
    //Assert if all windows available
    Assert.assertNotNull("Checkout Windows not available", checkoutWindows);

    for (int i = 0; i < checkoutWindows.size() && i < 5; i++) {

      checkoutFulfilmentWindows[0] = checkoutWindows.get(i);
      sharedData.orderCheckoutSelectedWindowDate = checkoutFulfilmentWindows[0].getDate();

      //Get Afternoon Slot
      CheckoutWindowItems checkoutWindowAfternoonItems = checkoutFulfilmentWindows[0].getAfternoon();
      CheckoutWindowSlots[] checkoutWindowAfternoonSlots = checkoutWindowAfternoonItems.getSlots();

      //Get Evening Slot
      CheckoutWindowItems checkoutWindowEveningItems = checkoutFulfilmentWindows[0].getEvening();
      CheckoutWindowSlots[] checkoutWindowEveningSlots = checkoutWindowEveningItems.getSlots();

    //Made the changes to cover if there are no slots available. Earlier it was throwing Array OutOfBound error
      if (checkoutWindowAfternoonSlots.length == 0) {
        checkoutWindowEveningSlots[0] = Arrays.stream(checkoutWindowEveningItems.getSlots()).filter(j -> j.getStatus().equals("Available"))
            .findFirst().orElse(null);
      } else {
        checkoutWindowAfternoonSlots[0] = Arrays.stream(checkoutWindowAfternoonItems.getSlots()).filter(j -> j.getStatus().equals("Available"))
            .findFirst().orElse(null);
      }

      if (checkoutWindowAfternoonSlots[0] != null) {
        sharedData.windowId = checkoutWindowAfternoonSlots[0].getId();
        sharedData.orderCheckoutPaymentWindowTime = checkoutWindowAfternoonSlots[0].getStartTime();
        break;
      } else if (checkoutWindowEveningSlots[0] != null) {
        sharedData.windowId = checkoutWindowEveningSlots[0].getId();
        sharedData.orderCheckoutPaymentWindowTime = checkoutWindowEveningSlots[0].getStartTime();
        break;
      }
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
    //To validate that the "proceed to payment" flag is true to proceed for the payment
    Assert.assertTrue("Resolve the checkout errors before proceeding to payment", checkoutPaymentResponse.getOrder().isCanProceedToPayment());

    if (collectionMode.equals("Pickup")) {
      sharedData.orderCheckoutSummaryPaymentAddress = checkoutPaymentResponse.getOrder().getPickup().getStore().getText();
      sharedData.orderCheckoutSummaryPaymentWindowDate = checkoutPaymentResponse.getOrder().getPickup().getWindow().getDisplayDate();
      sharedData.orderCheckoutSummaryPaymentWindowTime = checkoutPaymentResponse.getOrder().getPickup().getWindow().getDisplayTime();
      sharedData.orderCheckoutSummaryPaymentWindowId = checkoutPaymentResponse.getOrder().getPickup().getWindow().getId();

    } else if (collectionMode.equals("Delivery")) {
      sharedData.orderCheckoutSummaryPaymentAddress = checkoutPaymentResponse.getOrder().getDelivery().getAddress().getText();
      sharedData.orderCheckoutSummaryPaymentWindowDate = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getDisplayDate();
      sharedData.orderCheckoutSummaryPaymentWindowTime = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getDisplayTime();
      sharedData.orderCheckoutSummaryPaymentWindowId = checkoutPaymentResponse.getOrder().getDelivery().getWindow().getId();

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
    //PayInstrumentsResponse payIntrumentsRepsonse = getPayInstruments();
    PayCardCaptureResponse payCardCaptureResponse = getCardCapture();
    String sessionID;
    if (System.getProperty("env").equals("uat")) {
      sessionID = payCardCaptureResponse.getCardCaptureURL().replace(TestProperties.get("iFRAME_UAT_URL"), "");
    } else {
      sessionID = payCardCaptureResponse.getCardCaptureURL().replace(TestProperties.get("iFRAME_TEST_URL"), "");
    }
    iFrameResponse iframeResponse = postiFrameCardDetails(sessionID);
    String instrumentId;
    if (iframeResponse.itemId == null) {
      instrumentId = iframeResponse.getPaymentInstrument().getItemId();
    } else {
      instrumentId = iframeResponse.getItemId();
    }
    float amount = sharedData.orderCheckoutPaymentTotalGST;
    DigitalPayResponse digitalPayResponse = postDigitalPay(instrumentId, String.valueOf(amount));
    sharedData.orderId = digitalPayResponse.getOrderId();
  }

  @And("^I complete the payment via saved paypal account$")
  public void iCompleteThePaymentViaSavedPaypalAccount() throws Throwable {
    PayInstrumentsResponse payInstrumentsResponse = getPayInstruments();
    PayPal[] savedPaypal = payInstrumentsResponse.getPayPal();
    if (payInstrumentsResponse.getPayPal().length != 0) {
      String savedInstrumentId = savedPaypal[0].getPaymentInstrumentId();
      float amount = sharedData.orderCheckoutPaymentTotalGST;
      DigitalPayResponse digitalPayResponse = postDigitalPay(savedInstrumentId, String.valueOf(amount));
      sharedData.orderId = digitalPayResponse.getOrderId();
    } else {
      logger.info("There is no saved paypal account");
    }
  }

  @And("^I verify the completed \"([^\"]*)\" order$")
  public void iVerifyTheCompletedOrder(String collectionMode) throws Throwable {

    OrderPlaced orderPlaced = getOrderDetails(sharedData.orderId);
    sharedData.orderConfirmationDeliveryTime = orderPlaced.getOrder().getDeliveryTimeSpan();
    sharedData.orderConfirmationWindowId = orderPlaced.getOrder().getDeliveryWindowID();
    sharedData.orderConfirmationSubtotal = orderPlaced.getOrder().getSubtotal();
    sharedData.orderConfirmationOrderTotal = orderPlaced.getOrder().getOrderTotal();
    sharedData.orderConfirmationPackagingFee = orderPlaced.getOrder().getPackagingFee();
    sharedData.orderConfirmationPackagingFeeLabel = orderPlaced.getOrder().getPackagingFeeLabel();

    //Validation of Order Confirmation responses
    if (collectionMode.equals("Delivery")) {
      Assert.assertTrue(orderPlaced.getOrder().getFulfilmentMethod().contains("Courier"));
    } else if (collectionMode.equals("Pickup")) {
      Assert.assertTrue(orderPlaced.getOrder().getFulfilmentMethod().contains("Pickup"));
    }
    Assert.assertEquals("Window ID doesn't match", sharedData.orderCheckoutSummaryPaymentWindowId, sharedData.orderConfirmationWindowId);
    Assert.assertEquals("Order subtotal is not matching", sharedData.orderCheckoutPaymentSubtotal, sharedData.orderConfirmationSubtotal);
    Assert.assertEquals("Order Total is not matching", sharedData.orderCheckoutPaymentTotalGST, sharedData.orderConfirmationOrderTotal);
    Assert.assertEquals("Packaging fee is not matching", sharedData.orderCheckoutPaymentPackagingFee, sharedData.orderConfirmationPackagingFee);
    Assert.assertEquals("Packaging preference is not matching", sharedData.orderCheckoutPaymentPackagingPreference, sharedData.orderConfirmationPackagingFeeLabel);
  }
}


