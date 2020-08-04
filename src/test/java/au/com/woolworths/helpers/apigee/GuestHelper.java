package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.authentication.GuestLoginRequest;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import io.restassured.http.Header;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GuestHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeGuestHelper.class");

  public GuestHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public LoginReponse mobileUserConnectToApigeeAPIEndpointAsGuestWithPossibleOptions(String deviceId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    GuestLoginRequest guestLoginRequest = new GuestLoginRequest();
    LoginReponse response;

    guestLoginRequest.setDevice_auth_token(deviceId);

    String endPoint = URLResources.APIGEE_V2_GUEST_LOGIN;
    requestStr = mapper.writeValueAsString(guestLoginRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("user-agent", TestProperties.get("user-agent")));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, LoginReponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

}
