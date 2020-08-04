package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.authentication.LoginReponse;
import au.com.woolworths.model.apigee.authentication.SignUpRequest;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import au.com.woolworths.utils.Utilities;

import java.util.Map;
import java.util.logging.Logger;

public class SignupHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("SignupPageHelper.class");

  public SignupHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public LoginReponse iCompleteSignUPWithDOB(String DateOfBirth) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setEmailaddress(Utilities.getRandomEmailAddress());
    signUpRequest.setDateofbirth(DateOfBirth);
    String endPoint = URLResources.APIGEE_V2_SIGNUP;
    requestStr = mapper.writeValueAsString(signUpRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    LoginReponse response = mapper.readValue(responseStr, LoginReponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}

