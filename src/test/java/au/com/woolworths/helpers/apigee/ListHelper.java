package au.com.woolworths.helpers.apigee;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.lists.*;
import au.com.woolworths.model.apigee.products.AddProductsToListRequest;
import au.com.woolworths.model.apigee.products.AddProductsToListResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.utils.Utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ListHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("ListHelper.class");
  RestInvocationUtil invocationUtil;

  public ListHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ListResponse createList(String listName) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ListRequest apigeeListRequest = new ListRequest();

    apigeeListRequest.setTitle(listName);
    apigeeListRequest.setTimestamp(Utilities.convertToEpochTime());

    String endPoint = URLResources.APIGEE_V2_LISTS;
    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListCommon);

    responseStr = mapWebserviceResponse.get("response");
    ListResponse response = mapper.readValue(responseStr, ListResponse.class);
    return response;

  }

  public GetListResponse retrieveList() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    String endPoint = URLResources.APIGEE_V2_LISTS;
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    GetListResponse response = mapper.readValue(responseStr, GetListResponse.class);
    return response;

  }

  public long getListIdForTheUser(String listName) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    long listId = 0L;
    Map<String, String> queryParams = new HashMap<String, String>();

    String endPoint = URLResources.APIGEE_V2_LISTS;

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    GetListResponse response = mapper.readValue(responseStr, GetListResponse.class);

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
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");

    ListDetailsResponse response = mapper.readValue(responseStr, ListDetailsResponse.class);
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

  public SwitchDefaultListResponse switchToDefaultList(String listName) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;

    String listId = Arrays.stream(retrieveList().getLists()).filter(item -> item.getTitle().contains(listName))
        .findFirst().get().getId();

    String endPoint = URLResources.APIGEE_V2_LISTS + "/" + listId + "/default";

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    SwitchDefaultListResponse response = mapper.readValue(responseStr, SwitchDefaultListResponse.class);
    return response;

  }

  public ListResponse addFreeTextItemToTheList(String listId, String freeText) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ListRequest listRequest = new ListRequest();
    ListResponse response;
    listRequest.setText(freeText);
    listRequest.setTimestamp(Utilities.convertToEpochTime());
    listRequest.setLastsynced(Utilities.convertToEpochTime());
    listRequest.setChecked(false);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", listId);

    requestStr = mapper.writeValueAsString(listRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ListResponse.class);
    return response;

  }

  public ListResponse updateFreeTextItemInTheList(long listId, long freeTextId, String freeText, boolean checkItem) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ListRequest listRequest = new ListRequest();
    ListResponse response;
    listRequest.setId(freeTextId);
    listRequest.setText(freeText);
    listRequest.setTimestamp(Utilities.convertToEpochTime());
    listRequest.setLastsynced(Utilities.convertToEpochTime());
    listRequest.setChecked(checkItem);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", Long.toString(listId));

    requestStr = mapper.writeValueAsString(listRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ListResponse.class);
    return response;

  }

  public ListResponse addFreeTextItemToTheList(String listId, String freeText, boolean checkItem) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ListRequest listRequest = new ListRequest();
    listRequest.setText(freeText);
    listRequest.setTimestamp(Utilities.convertToEpochTime());
    listRequest.setLastsynced(Utilities.convertToEpochTime());
    listRequest.setChecked(checkItem);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", listId);

    requestStr = mapper.writeValueAsString(listRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    ListResponse response = mapper.readValue(responseStr, ListResponse.class);
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
    apigeeListRequest.setTimestamp(Utilities.convertToEpochTime());
    apigeeListRequest.setLastsynced(Utilities.convertToEpochTime());
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
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    AddProductsToListResponse response = mapper.readValue(responseStr, AddProductsToListResponse.class);
    return response;

  }

  public ListDetailsResponse getListDetails(String listId, String version) throws Throwable {
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
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");
    ListDetailsResponse response = mapper.readValue(responseStr, ListDetailsResponse.class);
    return response;
  }

  public ListResponse deleteFreeTextFromList(String listId, String freeTextId) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("id", freeTextId);
    queryParams.put("timestamp", String.valueOf(Utilities.convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(Utilities.convertToEpochTime()));

    String endPoint = URLResources.APIGEE_V2_LIST_ITEMS;
    endPoint = endPoint.replace("{list_id}", listId);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");

    ListResponse response = mapper.readValue(responseStr, ListResponse.class);
    return response;
  }

  public ListResponse deleteNewlyCreatedList(String listId, long timestamp) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("timestamp", String.valueOf(Utilities.convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(Utilities.convertToEpochTime()));
    ListResponse response;

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", listId);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ListResponse.class);
    return response;

  }

  public ListResponse deleteTheList(long listId, long timeStamp) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("timestamp", String.valueOf(Utilities.convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(Utilities.convertToEpochTime()));

    ListResponse response;

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", Long.toString(listId));
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, queryParams, headerListCommon);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ListResponse.class);
    return response;
  }
}
