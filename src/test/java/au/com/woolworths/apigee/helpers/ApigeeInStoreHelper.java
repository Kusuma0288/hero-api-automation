package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.TestProperties;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.ApigeeSearchInStore;
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

public class ApigeeInStoreHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeInStoreHelper.class");

  public ApigeeInStoreHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeSearchInStore iSearchForInStore(String postcode, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_IN_STORE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", postcode);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerList.add(new Header("Authorization", "Bearer " + accessToken));
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, accessToken, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeSearchInStore searchForInStore = mapper.readValue(responseStr, ApigeeSearchInStore.class);
    return searchForInStore;
  }
}
