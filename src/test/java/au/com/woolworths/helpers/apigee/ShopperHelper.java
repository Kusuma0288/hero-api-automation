package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import au.com.woolworths.model.apigee.authentication.ShopperLoginRequest;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import io.restassured.http.Header;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ShopperHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeShopperHelper.class");

  public ShopperHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public LoginReponse userToConnectApigeewithLoginAndPassword(String userName, String password, String deviceId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ShopperLoginRequest loginRequest = new ShopperLoginRequest();
    LoginReponse response;

    loginRequest.setDevice_auth_token(deviceId);
    loginRequest.setUser_name(userName);
    loginRequest.setPassword(password);

    String endPoint = URLResources.APIGEE_V2_SHOPPER_LOGIN;
    requestStr = mapper.writeValueAsString(loginRequest);

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

  public LoginReponse userToConnectApigeewithLoginAndPasswordWithAPIKey(String userName, String password, String deviceId, String apiKey) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ShopperLoginRequest loginRequest = new ShopperLoginRequest();
    LoginReponse response;

    loginRequest.setDevice_auth_token(deviceId);
    loginRequest.setUser_name(userName);
    loginRequest.setPassword(password);

    String endPoint = URLResources.APIGEE_V2_SHOPPER_LOGIN;
    requestStr = mapper.writeValueAsString(loginRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", apiKey));
    headerList.add(new Header("user-agent", TestProperties.get("user-agent")));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, LoginReponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }
}
