package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.startshop.UserProfileResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UserProfileHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("LoadCartHelper.class");
  RestInvocationUtil invocationUtil;

  public UserProfileHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public UserProfileResponse iCallUserProfileAPI() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<>();

    UserProfileResponse response;

    String endPoint = URLResources.SCANGO_USER_PROFILE;

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, UserProfileResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}
