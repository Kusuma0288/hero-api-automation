package au.com.woolworths.stepdefinitions.apigee;

import au.com.woolworths.model.apigee.*;
import au.com.woolworths.model.apigee.trolley.TrolleyV2Response;
import au.com.woolworths.model.apigee.trolley.TrolleyV3Response;
import au.com.woolworths.model.apigee.search.ApigeeV3SearchResponse;
import au.com.woolworths.model.apigee.checkout.CheckoutPackagingPreferencesResponse;

import java.util.ArrayList;
import java.util.List;

public class ApigeeSharedData {
  public String deviceId;
  public String authToken;
  public String responseStatusCode;
  public ApigeeLoginReponse guestResponse;
  public String accessToken;
  public ApigeeLoginReponse shopperResponse;
  public ApigeeSearchAddresses searchAddressResponse;
  public String addressId;
  public String addressText;
  public String inStoreId;
  public ApigeeSearchInStore searchInStoreResponse;
  public ApigeeV2AddressStores searchPostCodeResponse;
  public String productAisle;
  public String productCategory;
  public ApigeeV3SearchResponse searchProductResponse;
  public String promoTileDataPath;
  public List<String> stockCode = new ArrayList<String>();
  public String mode;
  public String edrStatus;
  public SpecialspageResponse specialspageResponse;
  public TrolleyV2Response trolleyV2Response;
  public TrolleyV3Response trolleyV3Response;

  public String currentListId;
  public long listTimeStamp;
  public String defaultListId;
  public String currentFreeTextId;
  public ApigeeListResponse updatedListResponse;
  public int windowId;
  public String orderCheckoutPaymentWindowTime;
  public CheckoutPackagingPreferencesResponse[] packagingPreference;
  public String orderCheckoutSummaryPaymentAddress;
  public String orderCheckoutSummaryPaymentWindowDate;
  public Object orderCheckoutSummaryPaymentWindowTime;
  public String orderCheckoutPaymentAddress;
  public String orderCheckoutSelectedWindowDate;
  public int orderCheckoutSubtotal;
  public int orderCheckoutTotalGST;
  public int orderCheckoutPackagingFee;
  public String orderCheckoutPackagingPreference;
  public int orderCheckoutPaymentSubtotal;
  public int orderCheckoutPaymentTotalGST;
  public int orderCheckoutPaymentPackagingFee;
  public String orderCheckoutPaymentPackagingPreference;
  public boolean leaveUnattended;
  public String fulfilment;

}
