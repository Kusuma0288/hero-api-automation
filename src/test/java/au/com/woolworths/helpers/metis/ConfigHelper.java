package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.config.ConfigResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConfigHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;

  public ConfigHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ConfigResponse getconfig(String appVersion, String osVersion) throws IOException {
    String endPoint = URLResources.METIS_CONFIG;

    Map <String, String> mapWebserviceResponse = new HashMap <String, String>();
    Map <String, String> queryParams = new HashMap <String, String>();
    queryParams.put("appVersion", appVersion);
    queryParams.put("osVersion", osVersion);

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    String statusCodeStr = mapWebserviceResponse.get("statusCode");

    ConfigResponse response = mapper.readValue(responseStr, ConfigResponse.class);
    response.setStatusCode(statusCodeStr);

    return response;
  }

}
