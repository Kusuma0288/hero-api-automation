package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.helios.login.AuthCode;
import au.com.woolworths.model.helios.login.AuthCodeRequest;
import au.com.woolworths.model.metis.authentication.LinkResponse;
import au.com.woolworths.model.metis.authentication.LoginRequest;
import au.com.woolworths.model.metis.authentication.LoginResponse;
import au.com.woolworths.model.metis.authentication.TokenRequest;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class LoginHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("MetisRewardsHelper.class");
  RestInvocationUtil invocationUtil;

  public LoginHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public LinkResponse getLinkSession() throws Throwable {
    String endPoint = URLResources.METIS_REWARDS_LINK;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    LinkResponse response = mapper.readValue(responseStr, LinkResponse.class);
    sharedData.responseStatusCode = mapWebserviceResponse.get("statusCode");
    logger.info("Link API Response: " + response);

    return response;
  }

  public AuthCode getAuthCode(String rewardsUser, String clientOS) throws IOException {

    String endPoint = URLResources.HELIOS_LOGIN;
    Map<String, String> mapWebserviceResponse;

    AuthCodeRequest authCodeRequest = new AuthCodeRequest();
    authCodeRequest.setEmailOrCardNumber(rewardsUser);
    authCodeRequest.setClientOS(clientOS);
    String requestStr = mapper.writeValueAsString(authCodeRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    AuthCode response = mapper.readValue(responseStr, AuthCode.class);
    sharedData.responseStatusCode = mapWebserviceResponse.get("statusCode");

    return response;
  }


  public LoginResponse postLoginUsingAuthCode(String DeviceID, String sessionToken) throws Throwable {
    String endPoint = URLResources.METIS_LOGIN;
    Map<String, String> mapWebserviceResponse;

    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setAuthCode(sharedData.authCode);
    loginRequest.setSessionToken(sessionToken);
    loginRequest.setDeviceId(DeviceID);

    String requestStr = mapper.writeValueAsString(loginRequest);
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");

    sharedData.responseStatusCode = mapWebserviceResponse.get("statusCode");
    return mapper.readValue(responseStr, LoginResponse.class);
  }

  public LoginResponse postToken(String refreshToken) throws Throwable {
    String endPoint = URLResources.METIS_TOKEN;
    Map<String, String> mapWebserviceResponse;

    TokenRequest tokenRequest = new TokenRequest();

    tokenRequest.setRefreshToken(refreshToken);
    String requestStr = mapper.writeValueAsString(tokenRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");

    sharedData.responseStatusCode = mapWebserviceResponse.get("statusCode");
    return mapper.readValue(responseStr, LoginResponse.class);
  }
}
