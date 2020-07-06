package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.SearchProducts.ApigeeV3SearchResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SearchHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("SearchHelper.class");

  public SearchHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeV3SearchResponse getProductItems(String searchProduct, String mode) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", searchProduct);
    queryParams.put("mode", mode);
    queryParams.put("type", "products");

    ApigeeV3SearchResponse response;
    String endPoint = URLResources.APIGEE_V3_SEARCH;

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ApigeeV3SearchResponse.class);

    return response;

  }
}
