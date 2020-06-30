package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.model.*;
import au.com.woolworths.apigee.model.Trolley.TrolleyV2Response;
import au.com.woolworths.apigee.model.Trolley.TrolleyV3Response;
import au.com.woolworths.apigee.model.SearchProducts.ApigeeV3SearchResponse;

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

}
