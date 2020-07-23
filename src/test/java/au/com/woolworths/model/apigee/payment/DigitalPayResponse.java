package au.com.woolworths.model.apigee.payment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class DigitalPayResponse {
  private int orderId;
  private boolean placed;
  private float subtotal;
  private int orderDiscount;
  private int staffDiscount;
  private int deliveryFee;
  private int deliveryFeeDiscount;
  private float total;
  private OrderPayments orderPayments;
  private String fulfilmentMethod;
  private String fulfilmentMethodLabel;

  @Data public static class OrderPayments{
    private int wowRewards;
    private float creditCard;
    private int giftCard;
    private int coupon;
    private int account;
    private int payPal;
    private int storeCredit;
  }
}
