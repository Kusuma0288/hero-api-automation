package au.com.woolworths.helpers.trader;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class PaymentHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("PaymentHelper.class");
  RestInvocationUtil invocationUtil;

  public PaymentHelper() {
    //this.invocationUtil = new RestInvocationUtil();
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public PaywithPayPalResponse iMakePayPalPayment() throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    PaywithPayPalResponse response;
    String endPoint = URLResources.V2_PAYMENT_PAYPAL;


    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, PaywithPayPalResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

  public OrderResponse igetOrderDetails(String orderId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    OrderResponse response;
    String endPoint = URLResources.V2_ORDER.replace("{orderId}", orderId);


    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, OrderResponse.class);
    return response;
  }

  public MyOrders igetV3OrderDetails() throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    MyOrders myOrderresponse;
    String endPoint = URLResources.V3_ORDER;


    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    myOrderresponse = mapper.readValue(responseStr, MyOrders.class);
    return myOrderresponse;
  }

}
