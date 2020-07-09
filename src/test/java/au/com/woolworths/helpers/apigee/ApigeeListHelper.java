package au.com.woolworths.helpers.apigee;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.*;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import java.util.*;
import java.util.logging.Logger;

public class ApigeeListHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeListHelper.class");

  public ApigeeListHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeListResponse createList(String listName) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeListRequest apigeeListRequest = new ApigeeListRequest();

    apigeeListRequest.setTitle(listName);
    apigeeListRequest.setTimestamp(convertToEpochTime());

    String endPoint = URLResources.APIGEE_V2_LISTS;
    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);

    responseStr = mapWebserviceResponse.get("response");
    ApigeeListResponse response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeGetListResponse retrieveList() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    String endPoint = URLResources.APIGEE_V2_LISTS;
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");
    ApigeeGetListResponse response = mapper.readValue(responseStr, ApigeeGetListResponse.class);
    return response;

  }

  public long getListIdForTheUser(String listName) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    long listId = 0L;
    Map<String, String> queryParams = new HashMap<String, String>();

    String endPoint = URLResources.APIGEE_V2_LISTS;

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");
    ApigeeGetListResponse response = mapper.readValue(responseStr, ApigeeGetListResponse.class);

    for (int i = 0; i < response.getLists().length; i++) {
      if (response.getLists()[i].getTitle().equalsIgnoreCase(listName)) {
        listId = Long.parseLong(response.getLists()[i].getId());
        break;
      }
    }
    return listId;
  }

  public long getFreeTextIdForTheList(String freeText, long listId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    long freeTextId = 0L;
    Map<String, String> queryParams = new HashMap<String, String>();
    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", String.valueOf(listId));

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");

    ApigeeListDetailsResponse response = mapper.readValue(responseStr, ApigeeListDetailsResponse.class);
    if (response.getId().equals(String.valueOf(listId))) {
      for (int i = 0; i < response.getFreeTextItems().length; i++) {
        if (response.getFreeTextItems()[i].getText().equalsIgnoreCase(freeText)) {
          freeTextId = response.getFreeTextItems()[i].getId();
          break;
        }
      }
    }
    return freeTextId;
  }

  public ApigeeSwitchDefaultListResponse switchToDefaultList(String listName) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;

    String listId = Arrays.stream(retrieveList().getLists()).filter(item -> item.getTitle().contains(listName))
        .findFirst().get().getId();

    String endPoint = URLResources.APIGEE_V2_LISTS + "/" + listId + "/default";

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerList);
    responseStr = mapWebserviceResponse.get("response");
    ApigeeSwitchDefaultListResponse response = mapper.readValue(responseStr, ApigeeSwitchDefaultListResponse.class);
    return response;

  }

  public ApigeeListResponse addFreeTextItemToTheList(String listId, String freeText) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeListRequest apigeeListRequest = new ApigeeListRequest();
    ApigeeListResponse response;
    apigeeListRequest.setText(freeText);
    apigeeListRequest.setTimestamp(convertToEpochTime());
    apigeeListRequest.setLastsynced(convertToEpochTime());
    apigeeListRequest.setChecked(false);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", listId);

    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeListResponse updateFreeTextItemInTheList(long listId, long freeTextId, String freeText, boolean checkItem) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeListRequest apigeeListRequest = new ApigeeListRequest();
    ApigeeListResponse response;
    apigeeListRequest.setId(freeTextId);
    apigeeListRequest.setText(freeText);
    apigeeListRequest.setTimestamp(convertToEpochTime());
    apigeeListRequest.setLastsynced(convertToEpochTime());
    apigeeListRequest.setChecked(checkItem);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", Long.toString(listId));

    requestStr = mapper.writeValueAsString(apigeeListRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeListResponse addFreeTextItemToTheList(String listId, String freeText, boolean checkItem) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeListRequest apigeeListRequest = new ApigeeListRequest();
    apigeeListRequest.setText(freeText);
    apigeeListRequest.setTimestamp(convertToEpochTime());
    apigeeListRequest.setLastsynced(convertToEpochTime());
    apigeeListRequest.setChecked(checkItem);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", listId);

    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    ApigeeListResponse response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public AddProductsToListResponse addItemsToTheList(String articleId, int quantity, String listId, boolean checkItem, String version) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = "";
    String responseStr = "";

    AddProductsToListRequest apigeeListRequest = new AddProductsToListRequest();
    apigeeListRequest.setArticleId(articleId);
    apigeeListRequest.setQuantity(quantity);
    apigeeListRequest.setChecked(checkItem);
    apigeeListRequest.setTimestamp(convertToEpochTime());
    apigeeListRequest.setLastsynced(convertToEpochTime());
    String endPoint;
    if (version.equals("V2")) {

      endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
      endPoint = endPoint.replace("{list_id}", listId);
    } else {
      endPoint = URLResources.APIGEE_V3_LIST_ITEMS;
      endPoint = endPoint.replace("{list_id}", listId);
    }

    requestStr = mapper.writeValueAsString(apigeeListRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    AddProductsToListResponse response = mapper.readValue(responseStr, AddProductsToListResponse.class);
    return response;

  }

  public ApigeeListDetailsResponse getListDetails(String listId, String version) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    String endPoint;
    if (version.equals("V2")) {

      endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
      endPoint = endPoint.replace("{list_id}", listId);
    } else {
      endPoint = URLResources.APIGEE_V3_LISTS;
      endPoint = endPoint.concat("/" + listId);
    }

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");
    ApigeeListDetailsResponse response = mapper.readValue(responseStr, ApigeeListDetailsResponse.class);
    return response;
  }

  public ApigeeListResponse deleteFreeTextFromList(String listId, String freeTextId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("id", freeTextId);
    queryParams.put("timestamp", String.valueOf(convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(convertToEpochTime()));

    String endPoint = URLResources.APIGEE_V2_LIST_ITEMS;
    endPoint = endPoint.replace("{list_id}", listId);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");

    ApigeeListResponse response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;
  }

  public ApigeeListResponse deleteNewlyCreatedList(String listId, long timestamp) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("timestamp", String.valueOf(convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(convertToEpochTime()));
    ApigeeListResponse response;

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", listId);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeListResponse deleteTheList(long listId, long timeStamp) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("timestamp", String.valueOf(convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(convertToEpochTime()));

    ApigeeListResponse response;

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", Long.toString(listId));
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, queryParams, headerList);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;
  }
}
