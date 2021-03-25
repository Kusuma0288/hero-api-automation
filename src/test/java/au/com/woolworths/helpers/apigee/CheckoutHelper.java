package au.com.woolworths.helpers.apigee;

import au.com.woolworths.model.apigee.checkout.CheckoutPaymentSummaryResponse;
import au.com.woolworths.model.apigee.checkout.CheckoutRequest;
import au.com.woolworths.model.apigee.checkout.CheckoutResponse;
import au.com.woolworths.model.apigee.checkout.OrderPlaced;
import au.com.woolworths.model.apigee.payment.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.restassured.http.Header;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CheckoutHelper extends IFrameCardHelper {
  private final static Logger logger = Logger.getLogger("CheckoutHelper.class");
  RestInvocationUtil invocationUtil;

  public CheckoutHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public CheckoutResponse getCheckoutResponse(String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_CHECKOUT;

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);
    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CheckoutResponse.class);

  }

  public CheckoutPaymentSummaryResponse getCheckoutPaymentResponse(String accessToken) throws Throwable {
    String endPoint = URLResources.APIGEE_CHECKOUT_PAYMENT_SUMMARY;

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);
    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerList);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CheckoutPaymentSummaryResponse.class);

  }

  public CheckoutResponse postSetCheckoutWindow(int windowID, String windowDate, String accessToken) throws Throwable {
    String requestStr, responseStr, endPoint;
    CheckoutRequest checkoutRequest = new CheckoutRequest();

    checkoutRequest.setWindow(windowID);
    checkoutRequest.setDate(windowDate);

    endPoint = URLResources.APIGEE_CHECKOUT;

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    requestStr = mapper.writeValueAsString(checkoutRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CheckoutResponse.class);
  }

  public CheckoutResponse postSetPackagingPreference(int packagingID, String accessToken) throws Throwable {
    String requestStr, responseStr, endPoint;

    CheckoutRequest checkoutRequest = new CheckoutRequest();
    checkoutRequest.setPackaging(packagingID);
    endPoint = URLResources.APIGEE_CHECKOUT;

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    Header deliveryNow = new Header("x-enable-feature", "DYNAMIC_WINDOWS,DELIVERY_NOW");
    headerList.add(deliveryNow);

    requestStr = mapper.writeValueAsString(checkoutRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, CheckoutResponse.class);

  }

  public PayInstrumentsResponse getPayInstruments() throws Throwable {
    String endPoint = URLResources.APIGEE_PAYMENT_INSTRUMENTS;

    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, PayInstrumentsResponse.class);

  }

  public PayCardCaptureResponse getCardCapture() throws Throwable {
    String endPoint = URLResources.APIGEE_PAYMENT_CARDS;

    PayCardCaptureRequest payCardCaptureRequest = new PayCardCaptureRequest();
    payCardCaptureRequest.setClientReference("VALID_REFERENCE");
    String payCardCaptureRequestStr = mapper.writeValueAsString(payCardCaptureRequest);

    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, payCardCaptureRequestStr, headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, PayCardCaptureResponse.class);

  }

  public DigitalPayResponse postDigitalPay(String instrumentId, String amount) throws Throwable {

    String digipayRequestStr, responseStr, endPoint;
    endPoint = URLResources.APIGEE_PAYMENT_DIGITALPAY;
    DigitalPayRequest digitalPayRequest = new DigitalPayRequest();
    DigitalPayRequest.Payments[] Payments = new DigitalPayRequest.Payments[1];
    Payments[0] = new DigitalPayRequest.Payments();
    Payments[0].setAmount(amount);
    Payments[0].setStepUpToken("tokenise-stepup-token");
    Payments[0].setPaymentInstrumentId(instrumentId);
    digitalPayRequest.setPayments(Payments);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);
    digipayRequestStr = mapper.writeValueAsString(digitalPayRequest);
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, digipayRequestStr, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, DigitalPayResponse.class);

  }

  public OrderPlaced getOrderDetails(int OrderId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String endPoint, responseStr;
    Map<String, String> queryParams = new HashMap<>();
    endPoint = URLResources.APIGEE_V2_ORDER_CONFIRMATION;
    endPoint = endPoint.concat("" + OrderId);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, OrderPlaced.class);

  }
}