package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.logout.Logout;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class LogoutHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("LogoutHelper.class");
  RestInvocationUtil invocationUtil;

  public LogoutHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public Logout iRetrieveMyLogout(String deviceId) throws IOException {

    ObjectMapper mapper = new ObjectMapper();

    Map<String, String> mapWebserviceResponse;
    Logout request = new Logout();
    Logout response = new Logout();

    request.setDeviceId(deviceId);
    String endPoint = URLResources.METIS_LOGOUT;
    String requestStr = mapper.writeValueAsString(request);

    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, headerListRewards);

    String responseStr = mapWebserviceResponse.get("response");
    String statusCodeStr = mapWebserviceResponse.get("statusCode");

    response.setResponseString(responseStr);
    response.setStatusCode(statusCodeStr);

    return response;
  }

}
