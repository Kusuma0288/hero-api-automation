package au.com.woolworths.model.apigee.checkout;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OrderConfirmation {
  private Object Order;
  private int ID;
  private String Date;
  private String DeliveryCity;
  private String DeliveryDate;
  private int DeliveryFee;
  private int DeliveryFeeBeforeDiscount;
  private int DeliveryFeeDiscount;
  private String DeliveryInstructions;
  private String DeliveryMethod;
  private String DeliveryPhone;
  private String DeliveryPostalCode;
  private String DeliveryStreet1;
  private String DeliveryStreet2;
  private String DeliverySuburb;
  private String DeliveryTimeSpan;
  private int DeliveryWindowID;
  private int DiscountableSubtotal;
  private String Email;
  private String FirstName;
  private String LastName;
  private String MiddleInitial;
  private int OneCardNumber;
  private int OrderDiscount;
  private float OrderTotal;
  private String OrderRef;
  private Object Products;
  private int Savings;
  private int Subtotal;
  private int TeamDiscount;
  private String Title;
  private String Status;
  private Object OrderPayments;
  private String RewardsPoints;
  private int PackagingFee;
  private int PackagingAccountingType;
  private int PackagingArticleId;
  private String PackagingFeeLabel;
  private boolean IsPostPickPayOrder;
  private String FulfilmentMethod;
  private int ShopperID;
  private boolean IsExpress;
  private int ExpressEtaInMinutes;
  private boolean IsCrowdSourced;
  private String FulfilmentMethodLabel;
  private Object RewardsCredits;
  private Object RewardsInfo;
  private Object RewardsCreditsUsed;
  private String PackagingFeeDiscount;
  private String DeliveryUnlimitedSavings;
  private boolean HasDeliveryUnlimitedRewards;
  private String RestrictedItemsRecipient;
}
