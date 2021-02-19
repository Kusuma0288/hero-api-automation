package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.checkout.CheckoutResponse;
import au.com.woolworths.model.scango.kiosk.KioskCheckoutRequest;
import au.com.woolworths.model.scango.kiosk.KioskCheckoutResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CheckoutHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("CheckoutHelper.class");

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

    public KioskCheckoutResponse iClickOnKioskCheckout() throws IOException {
        Map<String, String> mapWebserviceResponse;
        String requestStr = null;
        String responseStr = null;
        String cartID = sharedData.checkoutResponse.getCartid();

        KioskCheckoutRequest kioskCheckoutRequest = new KioskCheckoutRequest();
        kioskCheckoutRequest.setCartbarcode(cartID);

        KioskCheckoutResponse response;

        String endPoint = URLResources.SCANGO_KIOSK_CHECKOUT;
        requestStr = mapper.writeValueAsString(kioskCheckoutRequest);

        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGoKiosk);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, KioskCheckoutResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }
}
