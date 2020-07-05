package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.*;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;

import java.util.*;
import java.util.logging.Logger;

public class TrolleyHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("TrolleyHelper.class");

  public TrolleyHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public TrolleyV3Response addStockCodesToTheV3Trolley(List<String> stockCodes, int quantity, boolean replaceTrolley) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;
    TrolleyItems trolleyItemRequest = new TrolleyItems();
    List<Item> productItems = new ArrayList<Item>();
    for (int i = 0; i < stockCodes.size(); i++) {
      Item trolleyItem = new Item();
      trolleyItem.setArticle(Long.parseLong(stockCodes.get(i)));
      trolleyItem.setItemquantityintrolley(quantity);
      trolleyItem.setAllowsubstitution("true");
      trolleyItem.setComment("");
      productItems.add(trolleyItem);
    }
    trolleyItemRequest.setItems(productItems);
    trolleyItemRequest.setReplaceTrolley(replaceTrolley);

    String endPoint = URLResources.APIGEE_V3_TROLLEY;
    requestStr = mapper.writeValueAsString(trolleyItemRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    TrolleyV3Response trolleyV3Response = mapper.readValue(responseStr, TrolleyV3Response.class);

    return trolleyV3Response;
  }

  public TrolleyV2Response addStockCodesToTheV2Trolley(List<String> stockCodes, int quantity, boolean replaceTrolley) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;
    TrolleyItems trolleyItemRequest = new TrolleyItems();
    List<Item> productItems = new ArrayList<Item>();
    for (int i = 0; i < stockCodes.size(); i++) {
      Item trolleyItem = new Item();
      trolleyItem.setArticle(stockCodes.get(i));
      trolleyItem.setItemquantityintrolley(quantity);
      trolleyItem.setAllowsubstitution("true");
      trolleyItem.setComment("");
      productItems.add(trolleyItem);
    }
    trolleyItemRequest.setItems(productItems);
    trolleyItemRequest.setReplaceTrolley(replaceTrolley);

    String endPoint = URLResources.APIGEE_V2_TROLLEY;
    requestStr = mapper.writeValueAsString(trolleyItemRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");

    TrolleyV2Response trolleyV2Response = mapper.readValue(responseStr, TrolleyV2Response.class);
    return trolleyV2Response;
  }

  public TrolleyV2Response clearTrolley() throws Throwable {
    String endPoint = URLResources.APIGEE_V2_TROLLEY_CLEAR;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    TrolleyV2Response trolleyResponse = mapper.readValue(responseStr, TrolleyV2Response.class);
    return trolleyResponse;
  }

  public TrolleyV3Response retriveV3Trolley() throws Throwable {
    String endPoint = URLResources.APIGEE_V3_RETRIEVE_TROLLEY;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    TrolleyV3Response trolleyResponse = mapper.readValue(responseStr, TrolleyV3Response.class);
    return trolleyResponse;
  }

  public TrolleyV2Response retriveV2Trolley() throws Throwable {
    String endPoint = URLResources.APIGEE_V2_RETRIEVE_TROLLEY;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    TrolleyV2Response trolleyResponse = mapper.readValue(responseStr, TrolleyV2Response.class);
    return trolleyResponse;
  }

  public TrolleyV3Response delStockCodesFromV3Trolley(String stockCode) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    String endPoint = URLResources.APIGEE_V3_TROLLEY + stockCode + "/clear";
    // invoke the service with the framed request

    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerList);
    responseStr = mapWebserviceResponse.get("response");

    TrolleyV3Response trolleyV3Response = mapper.readValue(responseStr, TrolleyV3Response.class);
    return trolleyV3Response;
  }

  public TrolleyV2Response delStockCodesFromV2Trolley(String stockCode) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    String endPoint = URLResources.APIGEE_V2_TROLLEY + stockCode + "/clear";
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerList);
    responseStr = mapWebserviceResponse.get("response");

    TrolleyV2Response trolleyV2Response = mapper.readValue(responseStr, TrolleyV2Response.class);
    return trolleyV2Response;
  }
}
