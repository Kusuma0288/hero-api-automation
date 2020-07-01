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

  public SpecialspageResponse iRetrieveSpecialspageWithOnlineMode(Map<String, String> queryParams) throws Throwable {
    String endPoint = URLResources.APIGEE_V2_SPECIALS;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    SpecialspageResponse response = mapper.readValue(responseStr, SpecialspageResponse.class);
    return response;
  }

}
