package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.startshop.StartShopRequest;
import au.com.woolworths.model.scango.startshop.StartShopResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class StartShopHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("StartShopHelper.class");
  RestInvocationUtil invocationUtil;

  public StartShopHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public StartShopResponse iClickOnStartShopAPI() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    StartShopRequest startShopRequest = new StartShopRequest();
    StartShopResponse response;

    startShopRequest.setTaponid(TestProperties.get("TAP_ON_ID"));
    startShopRequest.setDevicemake(TestProperties.get("DEVICE_MAKE"));
    startShopRequest.setDeviceid(TestProperties.get("DEVICE_ID"));
    startShopRequest.setOs(TestProperties.get("OS"));
    startShopRequest.setAppversion(TestProperties.get("APP_VERSION"));
    startShopRequest.setSkipwalletvalidation(Boolean.parseBoolean(TestProperties.get("SKIP_WALLET_VALIDATION")));


    String endPoint = URLResources.SCANGO_START_SHOP;
    requestStr = mapper.writeValueAsString(startShopRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, StartShopResponse.class);
    //response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}
