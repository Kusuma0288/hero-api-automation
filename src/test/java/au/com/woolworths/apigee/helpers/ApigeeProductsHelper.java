package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.ApigeeProductCategoriesSpecial;
import au.com.woolworths.apigee.model.ApigeeProductsSpecial;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeProductsHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeProductsHelper.class");

  public ApigeeProductsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ApigeeProductCategoriesSpecial iRetreiveProductCategoriesWithSpecial(String inStoreId, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V3_CATEGORIES;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", inStoreId);
    queryParams.put("type", "specials");

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeProductCategoriesSpecial productCategoriesSpecial = mapper.readValue(responseStr, ApigeeProductCategoriesSpecial.class);
    return productCategoriesSpecial;
  }

  public ApigeeProductsSpecial iRetreiveProductsWithSpecial(String inStoreId, String aisle, String category, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_PRODUCTS;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", inStoreId);
    queryParams.put("aisle", aisle);
    queryParams.put("category", category);
    queryParams.put("type", "specials");

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeProductsSpecial productsSpecial = mapper.readValue(responseStr, ApigeeProductsSpecial.class);
    return productsSpecial;
  }

  public ApigeeProductCategoriesSpecial iRetreiveProductCategories(String inStoreId, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V3_CATEGORIES;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", inStoreId);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeProductCategoriesSpecial productCategoriesSpecial = mapper.readValue(responseStr, ApigeeProductCategoriesSpecial.class);
    return productCategoriesSpecial;
  }

  public ApigeeProductsSpecial iRetreiveProducts(String inStoreId, String aisle, String category, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_PRODUCTS;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("store", inStoreId);
    queryParams.put("aisle", aisle);
    queryParams.put("category", category);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeProductsSpecial productsSpecial = mapper.readValue(responseStr, ApigeeProductsSpecial.class);
    return productsSpecial;
  }

  public ApigeeProductsSpecial iRetreiveSpecialsProductsInStore(String specialsGroup, String store, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_PRODUCTS;
    Map<String, String> queryParams = new HashMap<String, String>();

    queryParams.put("filter", specialsGroup);
    queryParams.put("type", "specials");
    queryParams.put("store", store);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeProductsSpecial productsSpecial = mapper.readValue(responseStr, ApigeeProductsSpecial.class);
    return productsSpecial;
  }

  public ApigeeProductsSpecial iRetreiveSpecialsProductsOnlinePickup(String specialsGroup, String mode, String accessToken) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_PRODUCTS;
    Map<String, String> queryParams = new HashMap<String, String>();

    queryParams.put("filter", specialsGroup);
    queryParams.put("type", "specials");
    queryParams.put("mode", mode);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invoke(endPoint, accessToken, queryParams);
    String responseStr = mapWebserviceResponse.get("response");

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    ApigeeProductsSpecial productsSpecial = mapper.readValue(responseStr, ApigeeProductsSpecial.class);
    return productsSpecial;
  }

}
