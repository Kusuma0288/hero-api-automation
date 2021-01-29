package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.scanitems.RemoveItemResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

    public class RemoveItemHelper extends BaseHelper {

        RestInvocationUtil invocationUtil;
        private final static Logger logger = Logger.getLogger("RemoveItemHelper.class");

        public RemoveItemHelper() {
            this.invocationUtil = ServiceHooks.restInvocationUtil;

        }

        public RemoveItemResponse iClickOnRemoveItemAPI() throws IOException {
            Map<String, String> mapWebserviceResponse;
            String responseStr = null;
            Map<String, ?> queryParams = new HashMap<>();

            RemoveItemResponse response;

            String endPoint = URLResources.SCANGO_REMOVE_ITEM + sharedData.lineNumber;
            mapWebserviceResponse = invocationUtil.invokeDelete(endPoint,queryParams,headerListScanGo);
            responseStr = mapWebserviceResponse.get("response");
            response = mapper.readValue(responseStr, RemoveItemResponse.class);
            response.setStatusCode(mapWebserviceResponse.get("statusCode"));
            return response;
        }

    }


