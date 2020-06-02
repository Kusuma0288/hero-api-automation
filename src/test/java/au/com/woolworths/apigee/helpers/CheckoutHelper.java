package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.CheckoutRequest;
import au.com.woolworths.apigee.model.CheckoutResponse;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import io.restassured.http.Header;

public class CheckoutHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("CheckoutHelper.class");

  public CheckoutHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public CheckoutResponse getCheckoutResponse(String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_CHECKOUT;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    List<Header> headerList = new LinkedList<Header>();
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    mapWebserviceResponse = invocationUtil.invokeWithHeaders(endPoint, accessToken, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    CheckoutResponse checkoutResponse = mapper.readValue(responseStr, CheckoutResponse.class);
    return checkoutResponse;

  }

  public CheckoutResponse postSetCheckoutWindow(int windowID, String windowDate, String accessToken) throws Throwable {
    String requestStr = null;
    String responseStr = null;

    CheckoutRequest checkoutRequest = new CheckoutRequest();
    CheckoutResponse checkoutResponse;

    checkoutRequest.setWindow(windowID);
    checkoutRequest.setDate(windowDate);

    String endPoint = URLResources.APIGEE_CHECKOUT;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    List<Header> headerList = new LinkedList<Header>();
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(checkoutRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, accessToken, headerList);
    responseStr = mapWebserviceResponse.get("response");
    checkoutResponse = mapper.readValue(responseStr, CheckoutResponse.class);
    return checkoutResponse;
  }

  public CheckoutResponse postSetPackagingPreference(int packagingID, String accessToken) throws Throwable {
    String requestStr = null;
    String responseStr = null;

    CheckoutRequest checkoutRequest = new CheckoutRequest();
    CheckoutResponse checkoutResponse;

    checkoutRequest.setPackaging(packagingID);

    String endPoint = URLResources.APIGEE_CHECKOUT;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    List<Header> headerList = new LinkedList<Header>();
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(checkoutRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, accessToken, headerList);
    responseStr = mapWebserviceResponse.get("response");
    checkoutResponse = mapper.readValue(responseStr, CheckoutResponse.class);
    return checkoutResponse;

  }
}
