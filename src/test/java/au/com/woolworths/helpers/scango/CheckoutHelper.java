package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.checkout.CheckoutResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CheckoutHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("CheckoutHelper.class");
  RestInvocationUtil invocationUtil;

  public CheckoutHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public CheckoutResponse iClickOnCheckout() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<>();

    CheckoutResponse response;

    String endPoint = URLResources.SCANGO_CHECKOUT;

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, CheckoutResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}
