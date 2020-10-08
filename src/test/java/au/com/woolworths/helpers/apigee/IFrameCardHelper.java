package au.com.woolworths.helpers.apigee;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.payment.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import io.restassured.http.Header;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IFrameCardHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;

  String iFrameRequest, response, endPoint, bearer;

  public IFrameCardHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public iFrameResponse postiFrameCardDetails(String sessionID) throws Throwable {
    bearer = sessionID;

    if (System.getProperty("env").equals("uat")) {
      endPoint = URLResources.APIGEE_iFRAME_UAT;
    } else {
      endPoint = URLResources.APIGEE_iFRAME_TEST;
    }

    return postRequest();
  }

  public iFrameResponse postiFrameCardDetails(String sessionID, String hostname) throws IOException {
    bearer = sessionID;
    endPoint = hostname + URLResources.IFRAME_CREDITCARD;

    return postRequest();
  }

  private iFrameResponse postRequest() throws IOException {
    iFrameRequest iframeRequest = new iFrameRequest();
    iframeRequest.setAa(TestProperties.get("CARD_NUMBER"));
    iframeRequest.setBb(TestProperties.get("CVV"));
    iframeRequest.setDd(TestProperties.get("EXPIRY_MONTH"));
    iframeRequest.setEe(TestProperties.get("EXPIRY_YEAR"));
    iframeRequest.setPrimary(true);
    iframeRequest.setSave(true);
    iframeRequest.setVerify(true);
    iFrameRequest = mapper.writeValueAsString(iframeRequest);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("Authorization", "Bearer " + bearer));
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, iFrameRequest, headerList);
    response = mapWebserviceResponse.get("response");

    return mapper.readValue(response, iFrameResponse.class);
  }
}