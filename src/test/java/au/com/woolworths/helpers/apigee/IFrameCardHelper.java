package au.com.woolworths.helpers.apigee;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.payment.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import io.restassured.http.Header;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IFrameCardHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;

  public IFrameCardHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public iFrameResponse postiFrameCardDetails(String sessionID) throws Throwable {

    String iFrameRequeststr, responseStr, endPoint;
    if (System.getProperty("env").equals("uat")) {
      endPoint = URLResources.APIGEE_iFRAME_UAT;
    } else {
      // The TEST version of rewards app uses dev1, whereas the shop app uses test ¯\_(ツ)_/¯
      if (System.getProperty("useDev1").equals("true")) {
        endPoint = URLResources.APIGEE_iFRAME_DEV1;
      }
      else {
        endPoint = URLResources.APIGEE_iFRAME_TEST;
      }
    }

    iFrameRequest iframeRequest = new iFrameRequest();
    iframeRequest.setAa(TestProperties.get("CARD_NUMBER"));
    iframeRequest.setBb(TestProperties.get("CVV"));
    iframeRequest.setDd(TestProperties.get("EXPIRY_MONTH"));
    iframeRequest.setEe(TestProperties.get("EXPIRY_YEAR"));
    iframeRequest.setPrimary(true);
    iframeRequest.setSave(true);
    iframeRequest.setVerify(true);
    iFrameRequeststr = mapper.writeValueAsString(iframeRequest);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("Authorization", "Bearer " + sessionID));
    Map<String, String> mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, iFrameRequeststr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    return mapper.readValue(responseStr, iFrameResponse.class);
  }
}