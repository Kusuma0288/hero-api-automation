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
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("query", searchProduct);
    queryParams.put("sortBy", "TraderRelevance");
    String endPoint = URLResources.TRADER_V2_PRODUCTS_SEARCH;

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    //logger.info("The Response is::"+responseStr);
    GetProductItems response = mapper.readValue(responseStr, GetProductItems.class);
    return response;
  }

  public Suburbs getServicableSuburbs(String suburName) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("search", suburName);
    String endPoint = URLResources.TRADER_V2_PICKUP_SUBURB;
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    Suburbs response = mapper.readValue(responseStr, Suburbs.class);
    return response;
  }

  public PickupResponse[] getServicablePickupStoresFromPostCode(int postCode) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("postcode", String.valueOf(postCode));
    String endPoint = URLResources.TRADER_V2_PICKUP_SEARCH;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    PickupResponse[] response = mapper.readValue(responseStr, PickupResponse[].class);
    return response;
  }

  public SearchResponse searchProducts(String authToken, String term, int pageNumber, int pageSize, String sortOption) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr;
    String responseStr;

    SearchRequest searchRequest = new SearchRequest();
    SearchResponse searchResponse;

    searchRequest.setTerm(term);
    searchRequest.setPageNumber(pageNumber);
    searchRequest.setPageSize(pageSize);
    searchRequest.setSortOption(sortOption);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

    requestStr = mapper.writeValueAsString(searchRequest);
    String endPoint = URLResources.V3_SEARCH;
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");

    searchResponse = mapper.readValue(responseStr, SearchResponse.class);
    return searchResponse;

  }

}
