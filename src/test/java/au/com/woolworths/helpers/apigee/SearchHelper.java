package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.search.SearchResponseV3;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SearchHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("SearchHelper.class");

  public SearchHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SearchResponseV3 getProductItems(String searchProduct, String mode) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", searchProduct);
    queryParams.put("mode", mode);
    queryParams.put("type", "products");

    SearchResponseV3 response;
    String endPoint = URLResources.APIGEE_V3_SEARCH;

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, SearchResponseV3.class);

    return response;

  }
}
