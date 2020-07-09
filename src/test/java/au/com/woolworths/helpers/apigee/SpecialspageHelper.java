package au.com.woolworths.helpers.apigee;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.SpecialspageResponse;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SpecialspageHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("SpeicalspageHelper.class");

  public SpecialspageHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SpecialspageResponse iRetrieveSpecialspageWithOnlineMode(Map<String, String> queryParams) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SPECIALS;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    SpecialspageResponse response = mapper.readValue(responseStr, SpecialspageResponse.class);
    return response;
  }

}
