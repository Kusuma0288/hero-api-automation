package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.search.SearchInStore;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class InStoreHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("InStoreHelper.class");

  public InStoreHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SearchInStore iSearchForInStore(String postcode) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_IN_STORE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", postcode);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListApigee);
    String responseStr = mapWebserviceResponse.get("response");
    SearchInStore searchForInStore = mapper.readValue(responseStr, SearchInStore.class);
    return searchForInStore;
  }
}
