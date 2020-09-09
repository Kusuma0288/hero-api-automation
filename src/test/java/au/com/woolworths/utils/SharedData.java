package au.com.woolworths.utils;

import au.com.woolworths.model.apigee.address.AddressStoresV2;
import au.com.woolworths.model.apigee.address.SearchAddresses;
import au.com.woolworths.model.apigee.lists.ListResponse;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import au.com.woolworths.model.apigee.products.SpecialspageResponse;
import au.com.woolworths.model.apigee.search.SearchInStore;
import au.com.woolworths.model.apigee.trolley.TrolleyV2Response;
import au.com.woolworths.model.apigee.trolley.TrolleyV3Response;
import au.com.woolworths.model.apigee.search.SearchResponseV3;
import au.com.woolworths.model.apigee.checkout.CheckoutPackagingPreferencesResponse;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.model.trader.product.productGroup.ProductsByProductGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedData {

  public String deviceId;
  public String sessionToken;
  public String authToken;
  public String responseStatusCode;
  public LoginReponse guestResponse;
  public String accessToken;
  public LoginReponse shopperResponse;
  public SearchAddresses searchAddresses;
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
  public int orderCheckoutSummaryPaymentWindowId;
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
  public int orderId;
  public int orderConfirmationSubtotal;
  public float orderConfirmationOrderTotal;
  public int orderConfirmationPackagingFee;
  public String orderConfirmationPackagingFeeLabel;
  public Object orderConfirmationDeliveryTime;
  public int orderConfirmationWindowId;


  public GuestLoginResponse guestLoginResponse;
  public ShopperLoginResponseV2 shopperLoginResponseV2;
  public SearchAddressResponse searchAddressResponse;
  public PickupResponse[] pickupResponse;
  public String signupResponseStatusCode;
  public ShopperLoginResponseV2 signupLoginResponseV2;
  public String signupAuthToken;
  public int deliveryAddressId;
  public GetProductItems searchProductItemsResult;
  public TrolleyResponse trolleyResponse;
  public ProductDetailsResponse productDetailsResponse;
  public HashMap<String, String> checkoutDetails = new HashMap<>();
  public HashMap<String, Integer> specialGroupCount = new HashMap<>();
  public HashMap<String, String> deliveryNowDetails = new HashMap<>();
  public ProductsByProductGroup productsByProductGroup;
  public Map<String, String> recentCompleteResponse = new HashMap<>();    //this is generic to any response
  public Address address;

  public String shopperDeliveryAddress;
  public int trolleyQuantity;
  public int fulfilmentStoreId;
  public int guestAddressId;
  public int deliveryAddressIdContainer;
  public String signedUpEmail;
  public String fulfilmentMethod;
  public String trolleyItemNote;
  public String stockCodeTrader;
  public List<String> stockCodes;
  public HashMap<Integer, String> deliveryAddresses = new HashMap<Integer, String>();
  public String orderIdTrader;

  public void setDeliveryAddresses(int addressID, String addressText) {
    deliveryAddresses.put(addressID, addressText);
  }
  public HashMap<Integer, String> getDeliveryAddresses() {
    return deliveryAddresses;
  }
  public void removeAllItemsInDeliveryAddresses() {
    deliveryAddresses.clear();
  }
}
