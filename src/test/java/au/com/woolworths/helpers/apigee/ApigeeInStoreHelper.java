package au.com.woolworths.helpers.apigee;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.ApigeeSearchInStore;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeInStoreHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeInStoreHelper.class");

  public ApigeeInStoreHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeSearchInStore iSearchForInStore(String postcode) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_IN_STORE;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("q", postcode);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    ApigeeSearchInStore searchForInStore = mapper.readValue(responseStr, ApigeeSearchInStore.class);
    return searchForInStore;
  }
}
