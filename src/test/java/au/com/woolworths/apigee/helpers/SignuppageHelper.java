package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.ApigeeLoginReponse;
import au.com.woolworths.apigee.model.SignUpRequest;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import au.com.woolworths.Utils.Utilities;

import java.util.Map;
import java.util.logging.Logger;

public class SignuppageHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("SignuppageHelper.class");

  public SignuppageHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeLoginReponse iCompleteSignUPWithDOB(String DateOfBirth) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    SignUpRequest signUpRequest = new SignUpRequest();
    signUpRequest.setFirstname("Naeem");
    signUpRequest.setLastname("Raza");
    signUpRequest.setEmailaddress(Utilities.getRandomEmailAddress());
    signUpRequest.setPassword("123456");
    signUpRequest.setDateofbirth(DateOfBirth);
    signUpRequest.setMobilephone("0421000000");
    signUpRequest.setEmailproductsandservices("true");
    signUpRequest.setSmsproductsservicesandpromotions("true");
    signUpRequest.setAgreetotsandcs(true);

    String endPoint = URLResources.APIGEE_V2_SIGNUP;
    requestStr = mapper.writeValueAsString(signUpRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    ApigeeLoginReponse response = mapper.readValue(responseStr, ApigeeLoginReponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}

