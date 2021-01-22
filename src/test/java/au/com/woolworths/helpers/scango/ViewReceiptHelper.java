package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.menu.ViewReceiptRequest;
import au.com.woolworths.model.scango.menu.ViewReceiptResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class ViewReceiptHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("ViewReceiptHelper.class");

    public ViewReceiptHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;

    }

    public ViewReceiptResponse iCallViewReceiptAPI() throws IOException {

        Map<String, String> mapWebserviceResponse;
        String requestStr = null;
        String responseStr = null;
        String cartID = sharedData.checkoutResponse.getCartid();

        ViewReceiptRequest viewReceiptRequest = new ViewReceiptRequest();
        viewReceiptRequest.setCartid(cartID);

        ViewReceiptResponse response;

        String endPoint = URLResources.SCANGO_VEIW_RECEIPT;
        requestStr = mapper.writeValueAsString(viewReceiptRequest);

        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGo);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, ViewReceiptResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }
}

