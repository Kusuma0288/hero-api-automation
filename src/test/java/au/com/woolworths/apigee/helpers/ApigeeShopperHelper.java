package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.ApigeeLoginReponse;
import au.com.woolworths.apigee.model.ApigeeShopperLoginRequest;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeShopperHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeShopperHelper.class");

  public ApigeeShopperHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeLoginReponse userToConnectApigeewithLoginAndPassword(String userName, String password, String deviceId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeShopperLoginRequest loginRequest = new ApigeeShopperLoginRequest();
    ApigeeLoginReponse response;

    loginRequest.setDeviceAuthToken(deviceId);
    loginRequest.setUserName(userName);
    loginRequest.setPassword(password);

    String endPoint = URLResources.APIGEE_V2_SHOPPER_LOGIN;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(loginRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("user-agent", TestProperties.get("user-agent")));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);

   // mapWebserviceResponse = invocationUtil.invoke(endPoint, requestStr, "user-agent");
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeLoginReponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }

  public ApigeeLoginReponse userToConnectApigeewithLoginAndPasswordWithAPIKey(String userName, String password, String deviceId, String apiKey) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeShopperLoginRequest loginRequest = new ApigeeShopperLoginRequest();
    ApigeeLoginReponse response;

    loginRequest.setDeviceAuthToken(deviceId);
    loginRequest.setUserName(userName);
    loginRequest.setPassword(password);

    String endPoint = URLResources.APIGEE_V2_SHOPPER_LOGIN;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(loginRequest);

    // invoke the service with the framed request
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", apiKey));
    headerList.add(new Header("user-agent", TestProperties.get("user-agent")));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeLoginReponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }
}
