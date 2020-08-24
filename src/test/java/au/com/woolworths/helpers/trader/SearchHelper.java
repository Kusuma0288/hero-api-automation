package au.com.woolworths.helpers.trader;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class SearchHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("SearchHelper.class");
  RestInvocationUtil invocationUtil;

  public SearchHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public GetProductItems getProductItems(String searchProduct) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("query", searchProduct);
    queryParams.put("sortBy", "TraderRelevance");

    GetProductItems response;
    String endPoint = URLResources.V2_PRODUCTS_SEARCH;


    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    //logger.info("The Response is::"+responseStr);
    response = mapper.readValue(responseStr, GetProductItems.class);

    return response;

  }

  public Suburbs getServicableSuburbs(String suburName) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("search", suburName);

    Suburbs response;
    String endPoint = URLResources.V2_PICKUP_SUBURB;


    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, Suburbs.class);

    return response;
  }

  public PickupResponse[] getServicablePickupStoresFromPostCode(int postCode) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("postcode", String.valueOf(postCode));

    PickupResponse[] response;
    String endPoint = URLResources.V2_PICKUP_SEARCH;

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, PickupResponse[].class);

    return response;
  }

}
