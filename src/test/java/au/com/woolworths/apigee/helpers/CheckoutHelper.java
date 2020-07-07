package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.Checkout.CheckoutPaymentSummaryResponse;
import au.com.woolworths.apigee.model.Checkout.CheckoutRequest;
import au.com.woolworths.apigee.model.Checkout.CheckoutResponse;
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

public class CheckoutHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("CheckoutHelper.class");

  public CheckoutHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public CheckoutResponse getCheckoutResponse(String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_CHECKOUT;

    Map<String, String> mapWebserviceResponse;
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CheckoutResponse.class);

  }

  public CheckoutPaymentSummaryResponse getCheckoutPaymentResponse(String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_CHECKOUT_PAYMENT_SUMMARY;

    Map<String, String> mapWebserviceResponse;

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CheckoutPaymentSummaryResponse.class);

  }

  public CheckoutResponse postSetCheckoutWindow(int windowID, String windowDate, String accessToken) throws Throwable {
    String requestStr;
    String responseStr;

    CheckoutRequest checkoutRequest = new CheckoutRequest();

    checkoutRequest.setWindow(windowID);
    checkoutRequest.setDate(windowDate);

    String endPoint = URLResources.APIGEE_CHECKOUT;

    Map<String, String> mapWebserviceResponse;
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    requestStr = mapper.writeValueAsString(checkoutRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    CheckoutResponse checkoutResponse = mapper.readValue(responseStr, CheckoutResponse.class);
    return checkoutResponse;
  }

  public CheckoutResponse postSetPackagingPreference(int packagingID, String accessToken) throws Throwable {
    String requestStr;
    String responseStr;

    CheckoutRequest checkoutRequest = new CheckoutRequest();

    checkoutRequest.setPackaging(packagingID);

    String endPoint = URLResources.APIGEE_CHECKOUT;

    Map<String, String> mapWebserviceResponse;
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    requestStr = mapper.writeValueAsString(checkoutRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    CheckoutResponse checkoutResponse = mapper.readValue(responseStr, CheckoutResponse.class);
    return checkoutResponse;

  }
}
