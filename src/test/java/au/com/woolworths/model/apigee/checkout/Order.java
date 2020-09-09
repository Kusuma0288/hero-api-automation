package au.com.woolworths.model.apigee.checkout;

import au.com.woolworths.model.apigee.delivery.DeliveryDetails;
import au.com.woolworths.model.apigee.pickup.Pickup;
import au.com.woolworths.model.apigee.checkout.LeaveUnattended;
import au.com.woolworths.model.trader.SubstitutionPreferences;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Order {
  private Object PrimaryAddress;
  private DeliveryDetails Delivery;
  private Pickup Pickup;
  private Object Fulfilment;
  private String Instructions;
  private double Savings;
  private int TeamDiscount;
  private int OrderDiscount;
  private int Subtotal;
  private int PackagingFee;
  private String PackagingFeeLabel;
  private int DeliveryFee;
  private int DeliveryFeeDiscount;
  private int DeliveryFeeBeforeDiscount;
  private float BalanceToPay;
  private float TotalIncludingGst;
  private int WowRewardsPaymentAmount;
  private int RedeemableWowRewardsDollars;
  private int DeferredWowRewardsDollars;
  private int StoreCreditTotal;
  private String EdrNumber;
  private Object Loyalty;
  private Object Discounts;
  private Object GiftCardPayments;
  private Object AvailableOrderItems;
  private Object UnavailableOrderItems;
  private Object RestrictedOrderItems;
  private Object ExceededSupplyLimitProducts;
  private Object ExceededSupplyLimitProductGroups;
  private Object RestrictedProductsByDeliveryMethod;
  private Object RestrictedProductsByDeliPlatter;
  private Object BonusItems;
  private Object Errors;
  private boolean CanProceedToPayment;
  private Object OrderTotalsTableData;
  private LeaveUnattended LeaveUnattended;
  private SubstitutionPreferences SubstitutionPreferences;
}
