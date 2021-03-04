package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.kiosk.VerifyCartRequest;
import au.com.woolworths.model.scango.kiosk.VerifyCartResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class KioskVerifyCartHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("KioskVerifyCartHelper.class");
  RestInvocationUtil invocationUtil;

  public KioskVerifyCartHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public VerifyCartResponse iCallVerifyCartAPI() throws IOException {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;
    String cartID = sharedData.checkoutResponse.getCartid();

    VerifyCartRequest verifyCartRequest = new VerifyCartRequest();
    verifyCartRequest.setCartbarcode(cartID);

    VerifyCartResponse response;

    String endPoint = URLResources.SCANGO_VERIFY_CART;
    requestStr = mapper.writeValueAsString(verifyCartRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGoKiosk);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, VerifyCartResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

}
