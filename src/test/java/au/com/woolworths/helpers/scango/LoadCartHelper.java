package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.kiosk.KioskLoadCartRequest;
import au.com.woolworths.model.scango.kiosk.KioskLoadCartResponse;
import au.com.woolworths.model.scango.kiosk.VerifyCartRequest;
import au.com.woolworths.model.scango.scanitems.LoadCartResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class LoadCartHelper extends BaseHelper {
    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("LoadCartHelper.class");

    public LoadCartHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;

    }

    public LoadCartResponse iCallLoadCart() throws IOException {
        Map<String, String> mapWebserviceResponse;
        String responseStr = null;
        Map<String, String> queryParams = new HashMap<>();

        LoadCartResponse response;

        String endPoint = URLResources.SCANGO_LOAD_OR_DELETE_CART;

        mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGo);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, LoadCartResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }

    public KioskLoadCartResponse iCallKioskLoadCart() throws IOException {
        Map<String, String> mapWebserviceResponse;
        String responseStr = null;
        Map<String, String> queryParams = new HashMap<>();

        KioskLoadCartResponse response;

        String endPoint = URLResources.SCANGO_KIOSK_LOAD_CART;

        mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGoKiosk);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, KioskLoadCartResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }
}
