package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.login.*;
import au.com.woolworths.model.scango.payment.ListInstrumentsResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.http.Header;
import org.kohsuke.rngom.util.Uri;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LoginHelper extends BaseHelper {

    RestInvocationUtil invocationUtil;
    private final static Logger logger = Logger.getLogger("LoginHelper.class");

    public LoginHelper() {
        this.invocationUtil = ServiceHooks.restInvocationUtil;

    }
    public PreAuthResponse iCallPreAuthAPI() throws IOException {

        Map<String, String> mapWebserviceResponse;
        String requestStr = null;
        String responseStr = null;

        Map<String, String> queryParams = new HashMap<>();

        String endPoint = URLResources.SCANGO_PRE_AUTH;

        PreAuthResponse response;

        List<Header> headerList = new LinkedList<>();
        headerList.add(new Header("x-api-key", TestProperties.get("SCANGO_PRE_AUTH_API_KEY")));
        headerList.add(new Header("deviceid", TestProperties.get("DEVICE_ID")));
        mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
        responseStr = mapWebserviceResponse.get("response");
        response = mapper.readValue(responseStr, PreAuthResponse.class);
        //response.setStatusCode(mapWebserviceResponse.get("statusCode"));
        System.out.println("response" +response.toString());
        return response;
    }

    public String iLoginWithValidRewardsCredentials() {
        // TODO: run the browser in the background
        // TODO: Get the chromedriver using manage driver
                WebDriver driver = initiateWebdriver();
                driver.get( TestProperties.get("REWARDS_URL"));
                driver.findElement(By.id("emailCardNumber")).sendKeys( TestProperties.get("USER_EMAIL_ID"));
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
        headerList.add(new Header("authcode",rewardsAuthCode ));
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


    public  String getAuthCode(String rewardsUrl) {
        String[] params = rewardsUrl.split("=");
        String[] param = params[1].split("&");
        return param[0];
    }
}

