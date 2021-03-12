package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.kiosk.KioskLoginRequest;
import au.com.woolworths.model.scango.kiosk.KioskLoginResponse;
import au.com.woolworths.model.scango.login.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import io.restassured.http.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LoginHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("LoginHelper.class");
  RestInvocationUtil invocationUtil;

  public LoginHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public String iLoginWithValidRewardsCredentials() {
    WebDriver driver = initiateWebdriver();
    driver.get(TestProperties.get("REWARDS_URL"));
    driver.findElement(By.id("emailCardNumber")).sendKeys(TestProperties.get("USER_EMAIL_ID"));
    driver.findElement(By.xpath("//div[@id='wr-ios']/app-user-email-card-number/section/form/div/button/span")).click();
    driver.findElement(By.id("otp")).sendKeys(TestProperties.get("PASSWORD"));
    driver.findElement(By.xpath("//div[@id='wr-ios']/app-user-email-card-number/section/app-one-time-pass/section/form/div/button/span")).click();
    System.out.println("current url " + driver.getCurrentUrl());
    String rewardsTokenUrl = driver.getCurrentUrl();
    String authCode = getAuthCode(rewardsTokenUrl);
    driver.quit();
    return authCode;
  }

  public RewardsTokenResponse iCallRewardsTokenAPI() throws IOException {

    String rewardsAuthCode = sharedData.rewardsAuthCode;
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    RewardsTokenRequest rewardsTokenRequest = new RewardsTokenRequest();
    RewardsTokenResponse response;

    rewardsTokenRequest.setPartnersCustomerId(TestProperties.get("PARTNER_CUSTOMER_ID"));

    String endPoint = URLResources.SCANGO_REWARDS_TOKEN;
    requestStr = mapper.writeValueAsString(rewardsTokenRequest);

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("Authorization", TestProperties.get("REWARDS_TOKEN")));
    headerList.add(new Header("authcode", rewardsAuthCode));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, RewardsTokenResponse.class);
    return response;
  }

  public ScanGoLoginResponse userLoginIntoScanGo() throws IOException {
    String rewardsAccessToken = sharedData.accessToken;
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    DeviceInfo deviceInfo = new DeviceInfo();
    deviceInfo.setOs(TestProperties.get("OS"));
    deviceInfo.setAppversion(TestProperties.get("APP_VERSION"));
    deviceInfo.setOsversion(TestProperties.get("OS_VERSION"));
    deviceInfo.setDeviceid(TestProperties.get("DEVICE_ID"));
    deviceInfo.setDevicemake(TestProperties.get("DEVICE_MAKE"));

    ScanGoLoginRequest scanGoLoginRequest = new ScanGoLoginRequest();
    ScanGoLoginResponse response;

    scanGoLoginRequest.setDeviceinfo(deviceInfo);

    String endPoint = URLResources.SCANGO_LOGIN;
    requestStr = mapper.writeValueAsString(scanGoLoginRequest);

    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("SCANGO_API_KEY")));
    headerList.add(new Header("accesstoken", rewardsAccessToken));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ScanGoLoginResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }

  public KioskLoginResponse iCallKioskLoginAPI() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    KioskLoginRequest kioskLoginRequest = new KioskLoginRequest();
    KioskLoginResponse response;

    kioskLoginRequest.setBarcode(TestProperties.get("TEAM_MEMBER_BARCODE"));

    String endPoint = URLResources.SCANGO_KIOSK_LOGIN;
    requestStr = mapper.writeValueAsString(kioskLoginRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListScanGoKiosk);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, KioskLoginResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }


  public String getAuthCode(String rewardsUrl) {
    String[] params = rewardsUrl.split("=");
    String[] param = params[1].split("&");
    return param[0];
  }
}

