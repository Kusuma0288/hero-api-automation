package au.com.woolworths.utils;

import au.com.woolworths.model.apigee.*;
import au.com.woolworths.model.apigee.trolley.TrolleyV2Response;
import au.com.woolworths.model.apigee.trolley.TrolleyV3Response;
import au.com.woolworths.model.apigee.search.SearchResponseV3;
import au.com.woolworths.model.apigee.checkout.CheckoutPackagingPreferencesResponse;

import java.util.ArrayList;
import java.util.List;

public class SharedData {
  public String deviceId;
  public String authToken;
  public String responseStatusCode;
  public LoginReponse guestResponse;
  public String accessToken;
  public String guestAccessToken;
  public LoginReponse shopperResponse;
  public SearchAddresses searchAddressResponse;
  public String addressId;
  public String addressText;
  public String inStoreId;
  public SearchInStore searchInStoreResponse;
  public AddressStoresV2 searchPostCodeResponse;
  public String productAisle;
  public String productCategory;
  public SearchResponseV3 searchProductResponse;
  public String promoTileDataPath;
  public List<String> stockCode = new ArrayList<>();
  public String mode;
  public String edrStatus;
  public SpecialspageResponse specialspageResponse;
  public TrolleyV2Response trolleyV2Response;
  public TrolleyV3Response trolleyV3Response;

  public String currentListId;
  public long listTimeStamp;
  public String defaultListId;
  public String currentFreeTextId;
  public ListResponse updatedListResponse;
  public int windowId;
  public String orderCheckoutPaymentWindowTime;
  public CheckoutPackagingPreferencesResponse[] packagingPreference;
  public String orderCheckoutSummaryPaymentAddress;
  public String orderCheckoutSummaryPaymentWindowDate;
  public Object orderCheckoutSummaryPaymentWindowTime;
  public String orderCheckoutPaymentAddress;
  public String orderCheckoutSelectedWindowDate;
  public int orderCheckoutSubtotal;
  public float orderCheckoutTotalGST;
  public int orderCheckoutPackagingFee;
  public String orderCheckoutPackagingPreference;
  public int orderCheckoutPaymentSubtotal;
  public float orderCheckoutPaymentTotalGST;
  public int orderCheckoutPaymentPackagingFee;
  public String orderCheckoutPaymentPackagingPreference;
  public boolean leaveUnattended;
  public String fulfilment;

}
