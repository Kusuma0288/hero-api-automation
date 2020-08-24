package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.homepage.HomepageResponse;
import au.com.woolworths.model.apigee.productgroups.ProductGroupResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class HomepageHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("HomepageHelper.class");

  public HomepageHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public HomepageResponse iRetrieveHomepageWithInStore(Map<String, String> queryParams) throws Throwable {
    String endPoint = URLResources.HERMES_V1_HOMEPAGE;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListApigee);

    String responseStr = mapWebserviceResponse.get("response");

    HomepageResponse response = mapper.readValue(responseStr, HomepageResponse.class);
    return response;
  }

  public ProductGroupResponse iRetrieveProductGroup(String shoppingMode, String dataPath) throws Throwable {
    String endPoint = URLResources.HERMES_V1 + dataPath;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("mode", shoppingMode);
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListApigee);

    String responseStr = mapWebserviceResponse.get("response");

    ProductGroupResponse response = mapper.readValue(responseStr, ProductGroupResponse.class);
    return response;
  }

}
