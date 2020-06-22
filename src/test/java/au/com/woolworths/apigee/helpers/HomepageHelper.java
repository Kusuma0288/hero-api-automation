package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.HomepageResponse;
import au.com.woolworths.apigee.model.ProductGroupResponse;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class HomepageHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("HomepageHelper.class");

  public HomepageHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public HomepageResponse iRetrieveHomepageWithInStore(String store, String accessToken) throws Throwable {
    HomepageResponse response;

    String endPoint = URLResources.HERMES_V1_HOMEPAGE;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", store);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, accessToken, queryParams, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, HomepageResponse.class);
    return response;
  }

  public HomepageResponse iRetrieveHomepageWithOnlinePickup(String shoppingMode, String accessToken) throws Throwable {
    HomepageResponse response;

    String endPoint = URLResources.HERMES_V1_HOMEPAGE;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, accessToken, queryParams, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, HomepageResponse.class);
    return response;
  }

  public ProductGroupResponse iRetrieveProductGroup(String shoppingMode, String dataPath, String accessToken) throws Throwable {
    ProductGroupResponse response;

    String endPoint = URLResources.HERMES_V1 + dataPath;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, accessToken, queryParams, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ProductGroupResponse.class);
    return response;
  }

}
