package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.search.ApigeeV3SearchResponse;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;

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
