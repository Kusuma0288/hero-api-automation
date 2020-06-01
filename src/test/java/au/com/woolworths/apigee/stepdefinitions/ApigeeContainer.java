package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.model.ApigeeListResponse;
import au.com.woolworths.apigee.model.CheckoutPackagingPreferencesResponse;
import au.com.woolworths.apigee.model.CheckoutPackagingPreferencesResponse;
import au.com.woolworths.apigee.model.CheckoutPaymentSummaryResponse;

import java.util.HashMap;
import java.util.List;

public class ApigeeContainer {
  public String currentListId;
  public long listTimeStamp;
  public String defaultListId;
  public String currentFreeTextId;
  public ApigeeListResponse updatedListResponse;
  public int windowId;
  public String windowStartTime;
  public CheckoutPackagingPreferencesResponse[] packagingPreference;
  public String orderCheckoutPaymentAddress;
  public String orderCheckoutPaymentWindowDate;
  public Object orderCheckoutPaymentWindowTime;
  public String orderCheckoutPAddress;
  public String orderCheckoutPWindowDate;
  public String orderCheckoutPWindowTime;
  public int orderCheckoutSubtotal;
  public int orderCheckoutTotalGST;
  public int orderCheckoutPackagingFee;
  public String orderCheckoutPackagingPreference;
  public String orderCheckoutDAddress;
  public String orderCheckoutDWindowDate;
  public String orderCheckoutDWindowTime;
  public String orderCheckoutDPaymentAddress;
  public String orderCheckoutDPaymentWindowDate;
  public String orderCheckoutDPaymentWindowTime;
  public int orderCheckoutPaymentSubtotal;
  public int orderCheckoutPaymentTotalGST;
  public int orderCheckoutPaymentPackagingFee;
  public String orderCheckoutPaymentPackagingPreference;
}
