package au.com.woolworths.apigee.stepdefinitions;

import au.com.woolworths.apigee.model.*;

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
    public List<String> stockCode =  new ArrayList<String>();
    public String mode;
    public String edrStatus;
    public SpecialspageResponse specialspageResponse;
}
