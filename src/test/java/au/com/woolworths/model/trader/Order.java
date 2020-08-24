package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

  private String DeliveryMethod;
  private boolean IsExpress;
  private String FulfilmentMethod;
  private Date DeliveryDate;
  private int AddressId;
  private int FulfilmentStoreId;
  private int DeliveryFee;
  private int DeliveryFeeDiscount;
  private int DeliveryFeeBeforeDiscount;
  private String DeliveryCity;
  private String DeliveryPostalCode;
  private String DeliveryStreet1;
  private String DeliveryStreet2;
  private String DeliverySuburb;
  private String DeliveryPhoneNumber;
  private String DeliveryInstruction;
  private double PackagingFee;
  private double PackagingFeeBeforeDiscount;
  private double PackagingFeeDiscount;
  private String PackagingFeeLabel;
  private String PackagingCode;
  private double Savings;
  private double StoreCreditTotal;
  private double EvoucherTotal;
  private double DiscountableSubtotal;
  private String Subtotal;
  private String BalanceToPay;
  private double OrderDiscount;
  private double TeamDiscount;
  private double TotalIncludingGst;
  private double WowRewardsPaymentAmount;
  private double RedeemableWowRewardsDollars;
  private double DeferredWowRewardsDollars;
  private Object[] Discounts;
  private Object[] GiftCardPayments;
  private AvailableOrderItems[] AvailableOrderItems;
  private Object[] UnavailableOrderItems;
  private Object[] RestrictedOrderItems;
  private Object[] BonusItems;
  private Object[] ExceededSupplyLimitProducts;
  private Object[] ExceededSupplyLimitProductGroups;
  private Object[] RestrictedProductsByDeliveryMethod;
  private Object[] RestrictedProductsByDeliPlatter;
  private boolean ShowLeaveUnattended;
  private boolean DisableLeaveUnattended;
  private boolean CanLeaveUnattended;
  private LeaveUnattendedMessage OnLeaveUnattendedMessage;
}
