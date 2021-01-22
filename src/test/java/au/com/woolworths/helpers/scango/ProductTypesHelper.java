package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.scanitems.AddItemRequest;
import au.com.woolworths.model.scango.scanitems.AddItemResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class ProductTypesHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("ProductTypesHelper.class");

    public ProductTypesHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;

    }

    public AddItemResponse iClickOnScanItem(String product) throws IOException {
        System.out.println("helper  file " +product);
        String productType = product;
        String ean="";
        Map<String, String> mapWebserviceResponse;
        String requestStr = null;
        String responseStr = null;

        AddItemRequest addItemRequest = new AddItemRequest();
        AddItemResponse response;


        switch (productType) {
                case "Simple item": ean = TestProperties.get("SIMPLE_ITEM"); break;
                case "Tun item":  ean = TestProperties.get("TUN_ITEM"); break;
        }

        addItemRequest.setEan(ean);

        String endPoint = URLResources.SCANGO_ADD_ITEM;
        requestStr = mapper.writeValueAsString(addItemRequest);

        mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGo);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, AddItemResponse.class);
        response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        return response;
    }
}
