package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.*;
import au.com.woolworths.apigee.model.ApigeeV2AddressStores;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeAddressHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeAddressHelper.class");

  public ApigeeAddressHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeSearchAddresses iSearchForTheAddress(String lookupAddress, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", lookupAddress);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeSearchAddresses searchAddressResponse = mapper.readValue(responseStr, ApigeeSearchAddresses.class);
    return searchAddressResponse;
  }

  public ApigeeAddressDetails iGetTheAddressIdFromAmasId(String amasId, String accessToken) throws Throwable {

    ApigeeAddressRequest addressRequest = new ApigeeAddressRequest();
    String endPoint = URLResources.APIGEE_V2_ADDRESS;
    addressRequest.setAmasId(amasId);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    String addressRequestStr = mapper.writeValueAsString(addressRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, addressRequestStr, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    ApigeeAddressDetails addressDetailResponse = mapper.readValue(responseStr, ApigeeAddressDetails.class);
    return addressDetailResponse;
  }

  public ApigeeListAddresses iGetTheListAddresses(String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_LIST_ADDRESSES;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, new HashMap<String, String>());
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeListAddresses myAddressResponse = mapper.readValue(responseStr, ApigeeListAddresses.class);
    return myAddressResponse;

  }

  public ApigeeV2AddressStores iSearchForThePostCode(String postCode, String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("postcode", postCode);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeV2AddressStores searchPostCodeResponse = mapper.readValue(responseStr, ApigeeV2AddressStores.class);
    return searchPostCodeResponse;

  }

  public ApigeeV2AddressStores getStoresByAddressID(String storeAddressID, String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("storeAddressId", storeAddressID);
    ApigeeV2AddressStores storeLocatorResponse;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    storeLocatorResponse = mapper.readValue(responseStr, ApigeeV2AddressStores.class);
    return storeLocatorResponse;
  }

  public ApigeeV2AddressStores getStoresByStoreID(String storeID, String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("storeid", storeID);
    ApigeeV2AddressStores storeLocatorResponse;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    storeLocatorResponse = mapper.readValue(responseStr, ApigeeV2AddressStores.class);
    return storeLocatorResponse;

  }

  public ApigeeV2AddressStores getStoresForLatLong(String latitude, String longitude, String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("latitude", latitude);
    queryParams.put("longitude", longitude);
    ApigeeV2AddressStores storeLocatorResponse;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    storeLocatorResponse = mapper.readValue(responseStr, ApigeeV2AddressStores.class);
    return storeLocatorResponse;
  }

  public AddressesV2ErrorResponse getStoresForInvalidParams(String param, String type, String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SEARCH_ADDRESS_POSTCODE;
    Map<String, String> queryParams = new HashMap<String, String>();
    if (type.equals("storeid")) {
      queryParams.put("storeid", param);
    } else {
      queryParams.put("storeAddressId", param);
    }
    AddressesV2ErrorResponse v2ErrorResponse;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    v2ErrorResponse = mapper.readValue(responseStr, AddressesV2ErrorResponse.class);
    return v2ErrorResponse;
  }

  public FulFilmentResponse setTheFulfilmentForTheStore(String storeAddressId, String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    StoreAddressRequest storeAddressRequest = new StoreAddressRequest();
    storeAddressRequest.setStoreAddressId(storeAddressId);

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(storeAddressRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
   // mapWebserviceResponse = invocationUtil.invoke(endPoint, requestStr, accessToken);
    responseStr = mapWebserviceResponse.get("response");

    FulFilmentResponse fulFilmentResponse = mapper.readValue(responseStr, FulFilmentResponse.class);
    return fulFilmentResponse;

  }

  public DeliveryFulfilmentV3Response setTheFulfilmentForAddress(String address, String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    DeliveryAddressRequest deliveryAddressRequest = new DeliveryAddressRequest();
    deliveryAddressRequest.setAddress(address);

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(deliveryAddressRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = mapper.readValue(responseStr, DeliveryFulfilmentV3Response.class);
    return deliveryFulfilmentV3Response;

  }

  public Fulfilmentv3ErrorResponse setTheFulfilmentForAddressErrorScenario(String address, String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    DeliveryAddressRequest deliveryAddressRequest = new DeliveryAddressRequest();
    deliveryAddressRequest.setAddress(address);

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(deliveryAddressRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    Fulfilmentv3ErrorResponse fulfilmentv3ErrorResponse = mapper.readValue(responseStr, Fulfilmentv3ErrorResponse.class);
    return fulfilmentv3ErrorResponse;

  }

  public DeliveryFulfilmentV3Response getTheFulfilmentAddress(String accessToken) throws Throwable {
    String responseStr = null;

    String endPoint = URLResources.APIGEE_V3_FULFILMENT;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();

    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");


    DeliveryFulfilmentV3Response deliveryFulfilmentV3Response = mapper.readValue(responseStr, DeliveryFulfilmentV3Response.class);

    return deliveryFulfilmentV3Response;
  }

}
