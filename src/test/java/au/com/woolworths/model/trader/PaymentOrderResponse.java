package au.com.woolworths.model.trader;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class PaymentOrderResponse {

  ArrayList<Object> Products = new ArrayList<Object>();
  Object OrderPayments;
  private int OrderId;
  private String Date;
  private String DeliveryCity;
  private String DeliveryDate;
  private float DeliveryFee;
  private float DeliveryFeeBeforeDiscount;
  private float DeliveryFeeDiscount;
  private String DeliveryInstructions;
  private String DeliveryMethod;
  private String DeliveryPhone;
  private String DeliveryPostalCode;
  private String DeliveryStreet1;
  private String DeliveryStreet2;
  private String DeliverySuburb;
  private String DeliveryTimeSpan;
  private String DeliveryWindowID;
  private float DiscountableSubtotal;
  private String Email;
  private String FirstName;
  private String LastName;
  private String MiddleInitial;
  private String OneCardNumber;
  private float OrderDiscount;
  private String OrderTotal;
  private String OrderRef;
  private float Savings;
  private String Subtotal;
  private float TeamDiscount;
  private String Title;
  private String Status;
  private String RewardsPoints;
  private float PackagingFee;
  private String PackagingAccountingType = null;
  private String PackagingArticleId = null;
  private String PackagingFeeLabel;
  private boolean IsPostPickPayOrder;
  private String FulfilmentMethod;
  private Object ShopperID;
  private Boolean IsExpress;

}



