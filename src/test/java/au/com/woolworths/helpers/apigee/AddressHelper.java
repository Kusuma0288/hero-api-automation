package au.com.woolworths.helpers.apigee;

import au.com.woolworths.model.apigee.address.AddressDetails;
import au.com.woolworths.model.apigee.address.AddressRequest;
import au.com.woolworths.model.apigee.address.AddressStoresV2;
import au.com.woolworths.model.apigee.address.SearchAddresses;
import au.com.woolworths.model.apigee.delivery.DeliveryAddressRequest;
import au.com.woolworths.model.apigee.delivery.DeliveryFulfilmentV3Response;
import au.com.woolworths.model.apigee.fulfilment.FulFilmentResponse;
import au.com.woolworths.model.apigee.fulfilment.Fulfilmentv3ErrorResponse;
import au.com.woolworths.model.apigee.lists.ListAddresses;
import au.com.woolworths.model.apigee.response.ErrorResponseV2;
import au.com.woolworths.model.apigee.store.StoreAddressRequest;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AddressHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeAddressHelper.class");

  public AddressHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SearchAddresses iSearchForTheAddress(String lookupAddress) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", lookupAddress);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    SearchAddresses searchAddressResponse = mapper.readValue(responseStr, SearchAddresses.class);
    return searchAddressResponse;
  }

  public AddressDetails iGetTheAddressIdFromAmasId(String amasId) throws Throwable {

    AddressRequest addressRequest = new AddressRequest();
    String endPoint = URLResources.APIGEE_V2_ADDRESS;
    addressRequest.setAmasId(amasId);

    String addressRequestStr = mapper.writeValueAsString(addressRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, addressRequestStr, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    AddressDetails addressDetailResponse = mapper.readValue(responseStr, AddressDetails.class);
    return addressDetailResponse;
  }

  public ListAddresses iGetTheListAddresses() throws Throwable {

    String endPoint = URLResources.APIGEE_V2_LIST_ADDRESSES;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ListAddresses myAddressResponse = mapper.readValue(responseStr, ListAddresses.class);
    return myAddressResponse;

  }

  public AddressStoresV2 getStore(Map<String, String> queryParams) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    AddressStoresV2 searchPostCodeResponse = mapper.readValue(responseStr, AddressStoresV2.class);
    return searchPostCodeResponse;

  }

  public ErrorResponseV2 getStoresForInvalidParams(String type, String param) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put(type, param);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ErrorResponseV2 errorResponseV2 = mapper.readValue(responseStr, ErrorResponseV2.class);
    return errorResponseV2;
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
