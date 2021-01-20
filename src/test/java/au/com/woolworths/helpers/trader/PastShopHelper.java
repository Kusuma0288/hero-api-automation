package au.com.woolworths.helpers.trader;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Logger;

public class PastShopHelper extends BaseHelper {


  private final static Logger logger = Logger.getLogger("PastShopHelper.class");
  RestInvocationUtil invocationUtil;

  public PastShopHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public PastShoppingListResponse iGetShoppingAisles() throws Throwable {
    String endPoint = URLResources.TRADER_V2_PAST_SHOPPING_LIST;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("page", "1");
    queryParams.put("pagesize", "25");
    Map<String, String> mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    PastShoppingListResponse response = mapper.readValue(responseStr, PastShoppingListResponse.class);
    return response;
  }


}
