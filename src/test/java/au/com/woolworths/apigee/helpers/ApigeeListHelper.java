package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.*;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeListHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeListHelper.class");

  public ApigeeListHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeListResponse createList(String listName, String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeListRequest apigeeListRequest = new ApigeeListRequest();
    ApigeeListResponse response;

    apigeeListRequest.setTitle(listName);
    apigeeListRequest.setTimestamp(convertToEpochTime());

    String endPoint = URLResources.APIGEE_V2_LISTS;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, requestStr, accessToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeGetListResponse retrieveList(String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    ApigeeGetListResponse response;

    String endPoint = URLResources.APIGEE_V2_LISTS;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeGetListResponse.class);
    return response;

  }

  public ApigeeGetListResponse getAllListForTheUser(String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    ApigeeGetListResponse response;

    String endPoint = URLResources.APIGEE_V2_LISTS;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeGetListResponse.class);
    return response;

  }

  public long getListIdForTheUser(String listName, String accessToken) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    long listId = 0L;
    Map<String, String> queryParams = new HashMap<String, String>();

    String endPoint = URLResources.APIGEE_V2_LISTS;
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
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

  public long getFreeTextIdForTheList(String freeText, long listId, String accessToken) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    long freeTextId = 0L;
    Map<String, String> queryParams = new HashMap<String, String>();

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", String.valueOf(listId));
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
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

  public ApigeeSwitchDefaultListResponse switchToDefaultList(String listName, String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    String listId = Arrays.stream(retrieveList(accessToken).getLists()).filter(item -> item.getTitle().contains(listName))
        .findFirst().get().getId();
    ApigeeSwitchDefaultListResponse response;

    String endPoint = URLResources.APIGEE_V2_LISTS + "/" + listId + "/default";

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, accessToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeSwitchDefaultListResponse.class);
    return response;

  }

  public ApigeeListResponse addFreeTextItemToTheList(String listId, String freeText, String accessToken) throws Throwable {

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

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, requestStr, accessToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeListResponse updateFreeTextItemInTheList(long listId, long freeTextId, String freeText, boolean checkItem, String accessToken) throws Throwable {
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

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    requestStr = mapper.writeValueAsString(apigeeListRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, accessToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeListResponse addFreeTextItemToTheList(String listId, String freeText, boolean checkItem, String accessToken) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ApigeeListRequest apigeeListRequest = new ApigeeListRequest();
    ApigeeListResponse response;
    apigeeListRequest.setText(freeText);
    apigeeListRequest.setTimestamp(convertToEpochTime());
    apigeeListRequest.setLastsynced(convertToEpochTime());
    apigeeListRequest.setChecked(checkItem);

    String endPoint = URLResources.APIGEE_V2_UPDATE_LIST_FREETEXT;
    endPoint = endPoint.replace("{list_id}", listId);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    requestStr = mapper.writeValueAsString(apigeeListRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, requestStr, accessToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public AddProductsToListResponse addItemsToTheList(String articleId, int quantity, String listId, boolean checkItem, String accessToken, String version) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String requestStr = "";
    String responseStr = "";

    AddProductsToListRequest apigeeListRequest = new AddProductsToListRequest();
    AddProductsToListResponse response;
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

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    requestStr = mapper.writeValueAsString(apigeeListRequest);
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, requestStr, accessToken);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, AddProductsToListResponse.class);
    return response;

  }

  public ApigeeListDetailsResponse getListDetails(String listId, String accessToken, String version) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    ApigeeListDetailsResponse response;
    String endPoint;
    if (version.equals("V2")) {

      endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
      endPoint = endPoint.replace("{list_id}", listId);
    } else {
      endPoint = URLResources.APIGEE_V3_LISTS;
      endPoint = endPoint.concat("/" + listId);
    }

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ApigeeListDetailsResponse.class);
    return response;
  }

  public ApigeeListResponse deleteFreeTextFromList(String listId, String freeTextId, String accessToken) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("id", freeTextId);
    queryParams.put("timestamp", String.valueOf(convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(convertToEpochTime()));

    ApigeeListResponse response;

    String endPoint = URLResources.APIGEE_V2_LIST_ITEMS;
    endPoint = endPoint.replace("{list_id}", listId);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;
  }

  public ApigeeListResponse deleteNewlyCreatedList(String listId, long timestamp, String accessToken) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("timestamp", String.valueOf(convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(convertToEpochTime()));
    ApigeeListResponse response;

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", listId);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;

  }

  public ApigeeListResponse deleteTheList(long listId, long timeStamp, String accessToken) throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("timestamp", String.valueOf(convertToEpochTime()));
    queryParams.put("lastsynced", String.valueOf(convertToEpochTime()));

    ApigeeListResponse response;

    String endPoint = URLResources.APIGEE_V2_GET_LIST_BY_ID;
    endPoint = endPoint.replace("{list_id}", Long.toString(listId));
    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapWebserviceResponse = invocationUtil.invokeDelete(endPoint, accessToken, queryParams);
    responseStr = mapWebserviceResponse.get("response");

    response = mapper.readValue(responseStr, ApigeeListResponse.class);
    return response;
  }
}
