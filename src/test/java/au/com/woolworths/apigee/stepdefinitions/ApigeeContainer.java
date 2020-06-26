package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.model.ApigeeListResponse;
import au.com.woolworths.apigee.model.CheckoutPackagingPreferencesResponse;

public class ApigeeContainer {
  public String currentListId;
  public long listTimeStamp;
  public String defaultListId;
  public String currentFreeTextId;
  public ApigeeListResponse updatedListResponse;
  public int windowId;
  public String windowStartTime;
  public CheckoutPackagingPreferencesResponse[] packagingPreference;
  public String orderCheckoutSummaryPaymentAddress;
  public String orderCheckoutSummaryPaymentWindowDate;
  public Object orderCheckoutSummaryPaymentWindowTime;
  public String orderCheckoutPaymentAddress;
  public String orderCheckoutPaymentWindowDate;
  public String orderCheckoutPaymentWindowTime;
  public int orderCheckoutSubtotal;
  public int orderCheckoutTotalGST;
  public int orderCheckoutPackagingFee;
  public String orderCheckoutPackagingPreference;
  public String orderCheckoutDeliveryAddress;
  public String orderCheckoutDeliveryWindowDate;
  public String orderCheckoutDeliveryWindowTime;
  public String orderCheckoutSummaryDeliveryPaymentAddress;
  public String orderCheckoutSummaryDeliveryPaymentWindowDate;
  public String orderCheckoutSummaryDeliveryPaymentWindowTime;
  public int orderCheckoutPaymentSubtotal;
  public int orderCheckoutPaymentTotalGST;
  public int orderCheckoutPaymentPackagingFee;
  public String orderCheckoutPaymentPackagingPreference;
  public boolean leaveUnattended;
  public String fulfilment;
}
