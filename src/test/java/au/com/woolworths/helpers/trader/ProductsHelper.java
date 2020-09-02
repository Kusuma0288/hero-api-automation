package au.com.woolworths.helpers.trader;

import au.com.woolworths.model.trader.product.productGroup.ProductsByProductGroup;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ProductsHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("ProductsHelper.class");
  RestInvocationUtil invocationUtil;

  public ProductsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public WeeklyPicksResponse iGetProductsWeeklyPicks() throws Throwable {
    String endPoint = URLResources.TRADER_V3_PRODUCTS_WEEKLYPICKS;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    // Since Product list has to many properties which we need not to validate so keeping state false for now
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);

    String responseStr = mapWebserviceResponse.get("response");

    WeeklyPicksResponse weeklyPicksResponse = mapper.readValue(responseStr, WeeklyPicksResponse.class);
    weeklyPicksResponse.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return weeklyPicksResponse;
  }

  public ProductDetailsResponse iGetProductDetails(String stockcode) throws Throwable {
    String endPoint = URLResources.TRADER_V2_PRODUCT_DETAIL.replace("{stockcode}", stockcode);
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("isMobile", "true");
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);

    String responseStr = mapWebserviceResponse.get("response");

    ProductDetailsResponse response = mapper.readValue(responseStr, ProductDetailsResponse.class);
    return response;
  }

  public SpecialsGroupResponse getSpecialsGroup() throws Throwable {

    String endPoint = URLResources.TRADER_V2_SPECIALS_GROUP;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");

    SpecialsGroupResponse response = mapper.readValue(responseStr, SpecialsGroupResponse.class);
    return response;
  }

  public SpecialsGroupDetailsResponse iGetSpecialsGroupDetails(String groupId, int page) throws Throwable {

    String endPoint = URLResources.TRADER_V2_SPECIALS_GROUP_DETAILS;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("group", groupId);
    queryParams.put("pageNumber", String.valueOf(page));
    queryParams.put("pageSize", "50");
    queryParams.put("sortBy", "TraderRelevance");
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");

    SpecialsGroupDetailsResponse response = mapper.readValue(responseStr, SpecialsGroupDetailsResponse.class);
    return response;
  }

  public ProductsByProductGroup iGetProductsByProductsGroup(String productGroupId) throws IOException {
    String endPoint = URLResources.TRADER_V3_PRODUCT_GROUP;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("productGroupId", productGroupId);
    queryParams.put("pageSize", "50");

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    sharedData.recentCompleteResponse = mapWebserviceResponse;

    String responseStr = mapWebserviceResponse.get("response");
    ProductsByProductGroup response = mapper.readValue(responseStr, ProductsByProductGroup.class);
    return response;
  }
}








