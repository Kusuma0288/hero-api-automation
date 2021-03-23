package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.payment.ListInstrumentsResponse;
import au.com.woolworths.model.scango.payment.Payment;
import au.com.woolworths.model.scango.payment.PaymentRequest;
import au.com.woolworths.model.scango.payment.PaymentResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("PaymentHelper.class");
  RestInvocationUtil invocationUtil;

  public PaymentHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public ListInstrumentsResponse iCallListInstruments() throws IOException {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<>();

    ListInstrumentsResponse response;

    String endPoint = URLResources.SCANGO_LIST_INSTRUMENTS;

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ListInstrumentsResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));

    return response;
  }

  public PaymentResponse iCallListPaymentAPI(String instrumentsID, Double balanceDue) throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    String requestStr = null;
    Map<String, String> queryParams = new HashMap<>();

    List<Payment> h = new ArrayList<Payment>();

    Payment payment = new Payment();
    payment.setPaymentInstrumentId(instrumentsID);
    payment.setAmount(balanceDue);
    payment.setCardSuffix(TestProperties.get("PAYMENT_CARD_SUFFIX"));
    payment.setCardType(TestProperties.get("PAYMENT_CART_TYPE"));


    h.add(payment);
    PaymentRequest paymentRequest = new PaymentRequest();
    PaymentResponse response;

    paymentRequest.setClientReference(TestProperties.get("PAYMENT_CLIENT_REFERENCE"));
    paymentRequest.setPayment(h);

    String endPoint = URLResources.SCANGO_PAYMENT;

    requestStr = mapper.writeValueAsString(paymentRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, PaymentResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));

    return response;
  }
}