package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.SpecialspageResponse;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SpecialspageHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("SpeicalspageHelper.class");

  public SpecialspageHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SpecialspageResponse iRetrieveSpecialspageWithOnlineMode(String shoppingMode, String accessToken) throws Throwable {
    SpecialspageResponse response;

    String endPoint = URLResources.APIGEE_V2_SPECIALS;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, SpecialspageResponse.class);
    return response;
  }

  public SpecialspageResponse iRetrieveSpecialspageWithInStoreMode(String storeNo, String accessToken) throws Throwable {
    SpecialspageResponse response;

    String endPoint = URLResources.APIGEE_V2_SPECIALS;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", storeNo);
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);

    String responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, SpecialspageResponse.class);
    return response;
  }

}
