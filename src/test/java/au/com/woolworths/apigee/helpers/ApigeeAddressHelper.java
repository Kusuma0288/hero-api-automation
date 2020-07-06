package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.*;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeAddressHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeAddressHelper.class");

  public ApigeeAddressHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeSearchAddresses iSearchForTheAddress(String lookupAddress) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", lookupAddress);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    ApigeeSearchAddresses searchAddressResponse = mapper.readValue(responseStr, ApigeeSearchAddresses.class);
    return searchAddressResponse;
  }

  public ApigeeAddressDetails iGetTheAddressIdFromAmasId(String amasId) throws Throwable {

    ApigeeAddressRequest addressRequest = new ApigeeAddressRequest();
    String endPoint = URLResources.APIGEE_V2_ADDRESS;
    addressRequest.setAmasId(amasId);

    String addressRequestStr = mapper.writeValueAsString(addressRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, addressRequestStr, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    ApigeeAddressDetails addressDetailResponse = mapper.readValue(responseStr, ApigeeAddressDetails.class);
    return addressDetailResponse;
  }

  public ApigeeListAddresses iGetTheListAddresses() throws Throwable {

    String endPoint = URLResources.APIGEE_V2_LIST_ADDRESSES;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ApigeeListAddresses myAddressResponse = mapper.readValue(responseStr, ApigeeListAddresses.class);
    return myAddressResponse;

  }

  public ApigeeV2AddressStores getStore(Map<String, String> queryParams) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ApigeeV2AddressStores searchPostCodeResponse = mapper.readValue(responseStr, ApigeeV2AddressStores.class);
    return searchPostCodeResponse;

  }

  public AddressesV2ErrorResponse getStoresForInvalidParams(String type, String param) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put(type, param);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    AddressesV2ErrorResponse v2ErrorResponse = mapper.readValue(responseStr, AddressesV2ErrorResponse.class);
    return v2ErrorResponse;
  }

  public FulFilmentResponse setTheFulfilmentForTheStore(String storeAddressId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    StoreAddressRequest storeAddressRequest = new StoreAddressRequest();
    storeAddressRequest.setStoreAddressId(storeAddressId);

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;

    requestStr = mapper.writeValueAsString(storeAddressRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    FulFilmentResponse fulFilmentResponse = mapper.readValue(responseStr, FulFilmentResponse.class);
    return fulFilmentResponse;

  }

  public DeliveryFulfilmentV3Response setTheFulfilmentForAddress() throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    DeliveryAddressRequest deliveryAddressRequest = new DeliveryAddressRequest();
    deliveryAddressRequest.setAddress(sharedData.addressId);

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;

    requestStr = mapper.writeValueAsString(deliveryAddressRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = mapper.readValue(responseStr, DeliveryFulfilmentV3Response.class);
    return deliveryFulfilmentV3Response;

  }

  public Fulfilmentv3ErrorResponse setTheFulfilmentForAddressErrorScenario(String address) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    DeliveryAddressRequest deliveryAddressRequest = new DeliveryAddressRequest();
    deliveryAddressRequest.setAddress(address);

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;

    requestStr = mapper.writeValueAsString(deliveryAddressRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    Fulfilmentv3ErrorResponse fulfilmentv3ErrorResponse = mapper.readValue(responseStr, Fulfilmentv3ErrorResponse.class);
    return fulfilmentv3ErrorResponse;

  }

  public DeliveryFulfilmentV3Response getTheFulfilmentAddress() throws Throwable {
    String responseStr = null;

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");

    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = mapper.readValue(responseStr, DeliveryFulfilmentV3Response.class);

    return deliveryFulfilmentV3Response;
  }

}
