package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.firstore.ReadFirestoreDocRefreshTokenResponse;
import au.com.woolworths.model.scango.firstore.UpsertFirestoreDocRefreshResponse;
import au.com.woolworths.model.scango.firstore.UpsertFirestoreDocRefreshTokenRequest;
import au.com.woolworths.model.scango.login.ScanGoRefreshTokenResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class RefreshTokenHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("RefreshTokenHelper.class");
  RestInvocationUtil invocationUtil;

  public RefreshTokenHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public ScanGoRefreshTokenResponse refreshTokenAPI() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String requestStr = "";
    String responseStr = null;

    ScanGoRefreshTokenResponse response;

    String endPoint = URLResources.SCANGO_REFRESH_TOKEN + sharedData.refreshToken;
    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ScanGoRefreshTokenResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

  public ReadFirestoreDocRefreshTokenResponse readRefreshTokenFromFireStore() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<>();

    ReadFirestoreDocRefreshTokenResponse response;

    String endPoint = URLResources.SCANGO_READ_FIRESTORE;

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListFirestoreScanGoToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ReadFirestoreDocRefreshTokenResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

  public UpsertFirestoreDocRefreshResponse upsertRefreshTokenToFireStore() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    UpsertFirestoreDocRefreshTokenRequest upsertFirestoreDocRefreshTokenRequest = new UpsertFirestoreDocRefreshTokenRequest();
    upsertFirestoreDocRefreshTokenRequest.setDocumentId("kvm");
    upsertFirestoreDocRefreshTokenRequest.setUat_refresh_token(sharedData.refreshToken);

    UpsertFirestoreDocRefreshResponse response;

    String endPoint = URLResources.SCANGO_UPSERT_FIRESTORE;
    requestStr = mapper.writeValueAsString(upsertFirestoreDocRefreshTokenRequest);

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListFirestoreScanGoToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, UpsertFirestoreDocRefreshResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}