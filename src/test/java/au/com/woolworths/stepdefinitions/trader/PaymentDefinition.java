package au.com.woolworths.stepdefinitions.trader;

import au.com.woolworths.helpers.trader.PaymentHelper;
import au.com.woolworths.model.trader.Address;
import au.com.woolworths.model.trader.MyOrders;
import au.com.woolworths.model.trader.OrderResponse;
import au.com.woolworths.model.trader.PaywithPayPalResponse;
import cucumber.api.java.en.And;
import org.testng.Assert;

import java.util.logging.Logger;

public class PaymentDefinition extends PaymentHelper {
  private final static Logger logger = Logger.getLogger("AddressDefinition.class");

  private Address[] guestAddress;


  @And("^I set the payment as (.*) and make a payment$")
  public void iMakePayment(String paymentMethod) throws Throwable {

    switch (paymentMethod) {
      case "Credit Card":
        break;

      case "Gift Card":
        break;

      case "Paypal":
        PaywithPayPalResponse payPalResponse = iMakePayPalPayment();
        if (payPalResponse.getStatusCode().equals("400")) {
          Assert.assertTrue(payPalResponse.getResponseStatus().getErrors().get(0).getMessage().contains("decline message from the bank"), "Some issue with Paypal other than bank decline");
        } else {
          Assert.assertNotNull(payPalResponse);
          Assert.assertTrue(payPalResponse.isSuccess(), "Payment didn't process through.");
          sharedData.orderIdTrader =payPalResponse.getPlacedOrderId().toString();
        }
        break;

      default:
        logger.info("Please check the payment method that you want to pay with");
    }
  }

  @And("^I verify the order details for (.*) method$")
  public void iverifyOrderDetails(String deliveryMethod) throws Throwable {
    if (sharedData.orderIdTrader != null) {
      OrderResponse orderResponse = igetOrderDetails(sharedData.orderIdTrader);
      Assert.assertNotNull(orderResponse);
      //verify shopperid
      Assert.assertNotNull(orderResponse.getPaymentOrderResponse().getShopperID());
      //verify courier and delivery address details
      Assert.assertTrue(deliveryMethod.equalsIgnoreCase(orderResponse.getPaymentOrderResponse().getFulfilmentMethod()), "Delivery method does not match");
      Assert.assertTrue(orderResponse.getPaymentOrderResponse().getDeliveryStreet1().equalsIgnoreCase(sharedData.checkoutDetails.get("DeliveryStreet1")), "Delivery address - Street1 does not match");
      Assert.assertTrue(orderResponse.getPaymentOrderResponse().getDeliveryStreet2().equalsIgnoreCase(sharedData.checkoutDetails.get("DeliveryStreet2")), "Delivery address - Street2 does not match");
      Assert.assertTrue(orderResponse.getPaymentOrderResponse().getDeliveryPostalCode().equalsIgnoreCase(sharedData.checkoutDetails.get("DeliveryPostalCode")), "Delivery address - Postal Code does not match");
      //verify packaging fee
      Assert.assertTrue(orderResponse.getPaymentOrderResponse().getPackagingFeeLabel().equalsIgnoreCase(sharedData.checkoutDetails.get("PackagingFeeLabel")), "Packaging Fee Label does not match");
      //verify total
      Assert.assertEquals(sharedData.checkoutDetails.get("Subtotal").replaceAll("\\.[0-9]*$", ""), orderResponse.getPaymentOrderResponse().getSubtotal().replaceAll("\\.[0-9]*$", ""), "Subtotal does not match");
      Assert.assertTrue(orderResponse.getPaymentOrderResponse().getOrderTotal().replaceAll("\\.[0-9]*$", "").equalsIgnoreCase(sharedData.checkoutDetails.get("BalanceToPay").replaceAll("\\.[0-9]*$", "")), "Order Total does not match");
      //verify selected window
      if (sharedData.checkoutDetails.get("selectedSlotId") != null) {
        Assert.assertTrue(orderResponse.getPaymentOrderResponse().getDeliveryWindowID().equalsIgnoreCase(sharedData.checkoutDetails.get("selectedSlotId")), "Selected Window slot id does not match");
        Assert.assertTrue(orderResponse.getPaymentOrderResponse().getDeliveryDate().replaceAll("T.*$", "").equalsIgnoreCase(sharedData.checkoutDetails.get("selectedDate").replaceAll("T.*$", "")), "Selected Window Date does not match");
      }
    }
  }

  @And("^I verify the V3 My Order details for Order status, Fulfillment method, Delivery address, Delivery date and TotalOrderCount$")
  public void iVerifyV3OrderDetails() throws Throwable {
    MyOrders myOrdersResponse = igetV3OrderDetails();

    //Verify that the response is not null
    Assert.assertNotNull(myOrdersResponse);

    //Verify that the 'totalOrderCount' is > 1 (as 1 order has been placed)
    Assert.assertTrue(myOrdersResponse.getTotalOrdersCount() > 0, "TotalOrdersCount is not greater than 0, Order was not placed correctly.");

    //verify order id matches the order placed
    Assert.assertEquals(myOrdersResponse.getOrders()[0].getOrderV3().getID().toString(), sharedData.orderIdTrader);

    //verify courier and delivery address details
    //Assert.assertTrue(deliveryMethod.equalsIgnoreCase(orderResponse.getOrder().getFulfilmentMethod()),"Delivery method does not match");
    Assert.assertTrue(myOrdersResponse.getOrders()[0].getDeliveryInfo().getStreet1().equalsIgnoreCase(sharedData.checkoutDetails.get("DeliveryStreet1")), "Delivery address - Street1 does not match");
    Assert.assertTrue(myOrdersResponse.getOrders()[0].getDeliveryInfo().getStreet2().equalsIgnoreCase(sharedData.checkoutDetails.get("DeliveryStreet2")), "Delivery address - Street2 does not match");
    Assert.assertTrue(myOrdersResponse.getOrders()[0].getDeliveryInfo().getPostalCode().equalsIgnoreCase(sharedData.checkoutDetails.get("DeliveryPostalCode")), "Delivery address - Postal Code does not match");

    //verify order status is set as 'placed'
    Assert.assertEquals(myOrdersResponse.getOrders()[0].getOrderStatus(), "Placed", "Delivery order status does not match to Placed status");

    //verify fulfilmentMethod
    Assert.assertEquals(myOrdersResponse.getOrders()[0].getFulfilmentMethod(), "Courier", "Delivery method does not match");

    //verify selected window
    if (sharedData.checkoutDetails.get("selectedSlotId") != null) {
      Assert.assertTrue(myOrdersResponse.getOrders()[0].getDeliveryWindowInfo().getDate().split("T")[0].matches(sharedData.checkoutDetails.get("selectedDate").split("T")[0]), "Selected Window Date does not match");
    }
  }
}
