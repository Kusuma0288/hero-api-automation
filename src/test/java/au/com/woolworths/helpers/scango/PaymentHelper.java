package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.kiosk.KioskPaymentRequest;
import au.com.woolworths.model.scango.kiosk.KioskPaymentResponse;
import au.com.woolworths.model.scango.kiosk.KioskPaymentinfo;
import au.com.woolworths.model.scango.payment.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

public class PaymentHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("PaymentHelper.class");

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


    public KioskPaymentResponse iCallKioskPaymentAPI(Double balanceDue) throws IOException {
        Map<String, String> mapWebserviceResponse;
        String responseStr = null;
        String requestStr = null;
        Map<String, String> queryParams = new HashMap<>();

        String cartID = sharedData.checkoutResponse.getCartid();
   //     Double purchaseAmt = sharedData.kioskCheckoutResponse.getBalancedue();

        KioskPaymentinfo kioskPaymentinfo = new KioskPaymentinfo();
        kioskPaymentinfo.setAmountpurchase(balanceDue);
        kioskPaymentinfo.setAuthcode(TestProperties.get("AUTH_CODE"));
        kioskPaymentinfo.setBin(TestProperties.get("BIN"));
        kioskPaymentinfo.setCardSuffix(TestProperties.get("CARD_SUFFIX"));
        kioskPaymentinfo.setCardType(TestProperties.get("CARD_TYPE"));
        kioskPaymentinfo.setInstrumentType(TestProperties.get("INSTRUMENT_TYPE"));
        kioskPaymentinfo.setMerchantid(TestProperties.get("MERCHANT_ID"));
        kioskPaymentinfo.setProviderCode(TestProperties.get("PROVIDER_C0DE"));
        kioskPaymentinfo.setResponsecode(TestProperties.get("RESPONSE_CODE"));
        kioskPaymentinfo.setResponsetext(TestProperties.get("RESPONSE_TEXT"));
        kioskPaymentinfo.setRrn(TestProperties.get("RRN"));
        kioskPaymentinfo.setScheme(TestProperties.get("SCHEME"));
        kioskPaymentinfo.setStan(TestProperties.get("STAN"));
        kioskPaymentinfo.setTerminalId(TestProperties.get("TERMINAL_ID"));
        kioskPaymentinfo.setTokenizedpan(TestProperties.get("TOKENIZED_PAN"));
        kioskPaymentinfo.setTxnpaidtime(TestProperties.get("TXN_PAID_TIME"));
        kioskPaymentinfo.setTxnreference(TestProperties.get("TXN_REFERENCE"));
        kioskPaymentinfo.setTxnType(TestProperties.get("TXN_TYPE"));


        KioskPaymentRequest kioskPaymentRequest = new KioskPaymentRequest();
        kioskPaymentRequest.setCartbarcode(cartID);
        kioskPaymentRequest.setPaymentinfo(kioskPaymentinfo);

        KioskPaymentResponse response;


        String endPoint = URLResources.SCANGO_KIOSK_PAYMENT;

        requestStr = mapper.writeValueAsString(kioskPaymentRequest);
        System.out.println("PaymentRequest  file " + requestStr);

        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGoKiosk);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, KioskPaymentResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));



        return response;
    }

    public PaymentErrorResponse iCallInvalidListPaymentAPI(String instrumentsID, Double balanceDue) throws IOException {
        Map<String, String> mapWebserviceResponse;
        String responseStr = null;
        String requestStr = null;

        List<Payment> h = new ArrayList<Payment>();

        Payment payment = new Payment();
        payment.setPaymentInstrumentId(instrumentsID);
        payment.setAmount(balanceDue);
        payment.setCardSuffix(TestProperties.get("PAYMENT_CARD_SUFFIX"));
        payment.setCardType(TestProperties.get("PAYMENT_CART_TYPE"));


        h.add(payment);
        PaymentRequest paymentRequest = new PaymentRequest();
        PaymentErrorResponse response;

        paymentRequest.setClientReference(TestProperties.get("PAYMENT_CLIENT_REFERENCE"));
        paymentRequest.setPayment(h);

        String endPoint = URLResources.SCANGO_PAYMENT;

        requestStr = mapper.writeValueAsString(paymentRequest);

        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGo);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, PaymentErrorResponse.class);
     // response.setStatusCode(mapWebserviceResponse.get("statusCode"));

        return response;
    }
}
