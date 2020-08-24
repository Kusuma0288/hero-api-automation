package au.com.woolworths.helpers.apigee;

import au.com.woolworths.model.apigee.payment.*;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.checkout.CheckoutPaymentSummaryResponse;
import au.com.woolworths.model.apigee.checkout.CheckoutRequest;
import au.com.woolworths.model.apigee.checkout.CheckoutResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

import static au.com.woolworths.model.apigee.payment.iFrameRequest.*;
import static au.com.woolworths.model.apigee.payment.iFrameRequest.Authentication.*;
import static au.com.woolworths.model.apigee.payment.iFrameRequest.Item.*;

import java.util.*;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import io.restassured.http.Header;

public class CheckoutHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("CheckoutHelper.class");

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

    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerListApigee);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, PayInstrumentsResponse.class);

  }

  public PayCardCaptureResponse getCardCapture() throws Throwable {
    String endPoint = URLResources.APIGEE_PAYMENT_CARDS;

    PayCardCaptureRequest payCardCaptureRequest = new PayCardCaptureRequest();
    payCardCaptureRequest.setClientReference("VALID_REFERENCE");
    String payCardCaptureRequestStr = mapper.writeValueAsString(payCardCaptureRequest);

    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, payCardCaptureRequestStr, headerListApigee);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, PayCardCaptureResponse.class);

  }

  public iFrameResponse postiFrameCardDetails(String sessionID) throws Throwable {

    String iFrameRequeststr, responseStr, endPoint = null;
    if (System.getProperty("env").equals("uat")) {
      endPoint = URLResources.APIGEE_iFRAME_UAT;
    } else { //endPoint = URLResources.APIGEE_iFRAME_TEST;
      logger.info("There is an existing issue with Digipay in Test environment, will be updated once the issue is addressed");
    }

    iFrameRequest iframeRequest = new iFrameRequest();
    Authentication authentication = new Authentication();
    iframeRequest.setAuthentication(authentication);

    Credentials[] credentials = new Credentials[1];
    credentials[0] = new Credentials();
    credentials[0].setType("PERSON");
    credentials[0].setSessionID(sessionID);
    iframeRequest.getAuthentication().setCredentials(credentials);

    Item item = new Item();
    iframeRequest.setItem(item);

    ItemFields[] itemFields = new ItemFields[4];
    itemFields[0] = new ItemFields();
    itemFields[0].setData(TestProperties.get("CARD_NUMBER"));
    itemFields[0].setName("cardNumber");

    itemFields[1] = new ItemFields();
    itemFields[1].setData(TestProperties.get("EXPIRY_MONTH"));
    itemFields[1].setName("expiryMonth");

    itemFields[2] = new ItemFields();
    itemFields[2].setData(TestProperties.get("EXPIRY_YEAR"));
    itemFields[2].setName("expiryYear");

    itemFields[3] = new ItemFields();
    itemFields[3].setData(TestProperties.get("CVV"));
    itemFields[3].setName("cvv");

    iframeRequest.getItem().setItemFields(itemFields);

    iFrameRequeststr = mapper.writeValueAsString(iframeRequest);
    List<Header> headerList = new LinkedList<>();
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, iFrameRequeststr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, iFrameResponse.class);

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
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, digipayRequestStr, headerListApigee);
    responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, DigitalPayResponse.class);

  }
}
