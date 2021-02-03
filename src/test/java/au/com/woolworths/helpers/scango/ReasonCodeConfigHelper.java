package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.kiosk.KioskReasonCodeConfigResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ReasonCodeConfigHelper extends BaseHelper {
    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("ReasonCodeConfigHelper.class");

    public ReasonCodeConfigHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;

    }

    public KioskReasonCodeConfigResponse iCallKioskReasonCodeConfigAPI() throws IOException {
        Map<String, String> mapWebserviceResponse;
        String responseStr = null;
        Map<String, String> queryParams = new HashMap<>();

        KioskReasonCodeConfigResponse response;

        String endPoint = URLResources.SCANGO_KIOSK_CONFIG;

        mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGoKiosk);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, KioskReasonCodeConfigResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }
}
