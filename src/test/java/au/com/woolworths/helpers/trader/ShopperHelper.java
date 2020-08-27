package au.com.woolworths.helpers.trader;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.Utilities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;
import org.testng.Assert;

import java.util.*;
import java.util.logging.Logger;

public class ShopperHelper extends BaseHelper {

  public static final HashMap<String, String> signupValidationMap = initMap();
  public static final HashMap<String, String> invalidLoginValidationMap = validationMap();
  private final static Logger logger = Logger.getLogger("ShopperHelper.class");
  RestInvocationUtil invocationUtil;

  public ShopperHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  private static HashMap<String, String> initMap() {
    HashMap<String, String> map = new HashMap<>();
    map.put("FirstName", "First name is required");
    map.put("LastName", "Last name is required");
    map.put("MobilePhone", "A valid Australian phone number is required.");
    map.put("EmailAddress", "Email address is required");
    map.put("EmailAddress_Format", "Email address is required to be in format of example@example.com");
    map.put("Password", "Password is required");
    return map;
  }

  private static HashMap<String, String> validationMap() {
    HashMap<String, String> map = new HashMap<>();
    map.put("Invalid", "The email address & password combination you have entered is incorrect.  Please try again or click the forgotten password link below to reset your password.");
    map.put("RedirectPasswordRemainder", "The email address & password combination you have entered is incorrect.  Please try again or click the forgotten password link below to reset your password.");
    map.put("LockedOut", "The password you entered was incorrect, and your account has been locked (for security reasons). Please contact our Customer Service Team on 1800 000 610 to unlock your account.");
    map.put("LoginIsLocked", "Your account has been locked. Please contact our Customer Service Team on 1800 000 610 to unlock your account.");
    return map;
  }

  public ShopperLoginResponseV2 apigeeToTraderPublicAPIEndpointwithLoginAndPassword(String userName, String password, String deviceId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ShopperLoginRequestV2 shopperLoginRequest = new ShopperLoginRequestV2(userName, password, deviceId);
    ShopperLoginResponseV2 response;
    String endPoint = URLResources.TRADER_V2_AUTH_SHOPPER;

    requestStr = mapper.writeValueAsString(shopperLoginRequest);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerList.add(new Header("cache-control", "no-cache"));
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerList);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ShopperLoginResponseV2.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }

  public ShopperLoginResponseV2 apigeeToTraderPublicAPIEndpointwithLoginAndPasswordUsingAPIkey(String userName, String password, String deviceId, String apiKey) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    ShopperLoginRequestV2 shopperLoginRequest = new ShopperLoginRequestV2(userName, password, deviceId);
    ShopperLoginResponseV2 response;
    String endPoint = URLResources.TRADER_V2_AUTH_SHOPPER;

    requestStr = mapper.writeValueAsString(shopperLoginRequest);

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeWithAPIKey(endPoint, requestStr, apiKey);
    responseStr = mapWebserviceResponse.get("response");
    //logger.info("Response: " + responseStr);
    response = mapper.readValue(responseStr, ShopperLoginResponseV2.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;

  }


  public GuestLoginResponse apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptions(int storeOrAddressId, boolean isStoreAddressId, boolean isFulfilmentStoreId, String deviceId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    GuestLoginRequest guestLoginRequest = new GuestLoginRequest();
    GuestLoginResponse response;

    guestLoginRequest.setDeviceAuthToken(deviceId);
    if (isStoreAddressId) {
      guestLoginRequest.setStoreAddressId(storeOrAddressId);
    }
    if (isFulfilmentStoreId) {
      guestLoginRequest.setFulfilmentStoreID(storeOrAddressId);
    }

    String endPoint = URLResources.TRADER_V3_GUEST;

    requestStr = mapper.writeValueAsString(guestLoginRequest);
    //logger.info("Guest Login Request Body is: " + requestStr);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerList.add(new Header("cache-control", "no-cache"));
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr,headerList);
    responseStr = mapWebserviceResponse.get("response");
    // logger.info("Response: " + responseStr);
    response = mapper.readValue(responseStr, GuestLoginResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

  public GuestLoginResponse apigeeConnectToTraderPublicAPIEndpointAsGuestWithPossibleOptionsV2(int fulfilmentIdOrPostCode, boolean isFulfilmentStoreId, boolean isPostcode, String deviceId) throws Throwable {

    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;
    GuestLoginRequest guestLoginRequest = new GuestLoginRequest();
    GuestLoginResponse response;

    guestLoginRequest.setDeviceAuthToken(deviceId);
    if (isFulfilmentStoreId) {
      guestLoginRequest.setFulfilmentStoreID(fulfilmentIdOrPostCode);
    }
    if (isPostcode) {
      guestLoginRequest.setPostcode(fulfilmentIdOrPostCode);
    }

    String endPoint = URLResources.TRADER_V2_GUEST;


    requestStr = mapper.writeValueAsString(guestLoginRequest);
    //logger.info("Guest Login Request Body is: " + requestStr);
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerList.add(new Header("cache-control", "no-cache"));
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr,headerList);
    responseStr = mapWebserviceResponse.get("response");
    // logger.info("Response: " + responseStr);
    response = mapper.readValue(responseStr, GuestLoginResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

  public ShopperLoginResponseV2 signUpAsAShopperWithFollowingDetails(List<UserDetail> userDetails, String deviceId, boolean exactEmail) throws Throwable {
    Assert.assertNotNull(userDetails);
    Map<String, String> mapWebserviceResponse;
    String requestStr = null;
    String responseStr = null;

    Register registerRequest = new Register();
    ShopperLoginResponseV2 signupResponse;

    for (UserDetail userDetail : userDetails) {
      registerRequest.setFirstName(userDetail.getFirstName());
      registerRequest.setLastName(userDetail.getLastName());
      if (exactEmail) {
        registerRequest.setEmailAddress(userDetail.getEmailAddress());
      } else {
        registerRequest.setEmailAddress(userDetail.getEmailAddress().replace("@", Utilities.getSaltString() + "@"));
      }
      registerRequest.setPassword(userDetail.getPassword());
      registerRequest.setMobilePhone(userDetail.getMobilePhone());
      registerRequest.setDateOfBirth(userDetail.getDateOfBirth());
      registerRequest.setIsBusinessShopper(userDetail.isBusinessShopper());
      registerRequest.setEmailProductsAndServices(userDetail.isEmailProductsAndServices());
      registerRequest.setSmsProductsServicesAndPromotions(userDetail.isSmsProductsServicesAndPromotions());
      registerRequest.setCampaignName(userDetail.getCampaignName());
      registerRequest.setGuestTrolleyToken(deviceId);
      registerRequest.setAgreetotsandcs(userDetail.isAgreeToTsAndCs());
      
    }


    requestStr = mapper.writeValueAsString(registerRequest);
    //logger.info("Shopper Login Request Body is: " + requestStr);
    String endPoint = URLResources.TRADER_V2_SHOPPER_SIGNUP;
    List<Header> headerList = new LinkedList<>();
    headerList.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerList.add(new Header("cache-control", "no-cache"));
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr,headerList);
    responseStr = mapWebserviceResponse.get("response");
    //logger.info("Response: " + responseStr);

    signupResponse = mapper.readValue(responseStr, ShopperLoginResponseV2.class);
    signupResponse.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return signupResponse;
  }

  public ShopperLoginResponseV2 iUseTheExactDetailsForSigningUpAsANewUser(List<UserDetail> userDetails, String deviceId) throws Throwable {
    return signUpAsAShopperWithFollowingDetails(userDetails, deviceId, true);
  }

  public ShopperLoginResponseV2 iUseTheFollowingDetailsForSigningUpAsANewUser(List<UserDetail> userDetails, String deviceId) throws Throwable {
    return signUpAsAShopperWithFollowingDetails(userDetails, deviceId, false);
  }

  public CheckoutAddressResponse iSetTheFulfilmentStoreId(int fulfilmentStoreId) throws Throwable {

    String endPoint = URLResources.TRADER_V3_CHECKOUT_ADDRESS;
    StoreIdRequest storeIdRequest = new StoreIdRequest();
    storeIdRequest.setFulfilmentStoreId(fulfilmentStoreId);

    ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    String storeRequestStr = mapper.writeValueAsString(storeIdRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, storeRequestStr, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");

    CheckoutAddressResponse checkoutAddressResponse = mapper.readValue(responseStr, CheckoutAddressResponse.class);
    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "There is some issue with the save fulfilment store id: " + checkoutAddressResponse.toString());
    return checkoutAddressResponse;
  }

  public CheckoutAddressResponse iGETTheFulfilmentStoreId() throws Throwable {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<String, String>();

    CheckoutAddressResponse checkoutAddressResponse;
    String endPoint = URLResources.TRADER_V3_CHECKOUT_ADDRESS;


    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    checkoutAddressResponse = mapper.readValue(responseStr, CheckoutAddressResponse.class);
    return checkoutAddressResponse;

  }

  public TrolleyResponse clearTheTrolleyForTheShopper() throws Throwable {

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    String responseStr = null;

    String endPoint = URLResources.TRADER_V2_CLEAR_TROLLEY;

    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "Issues with clearing the trolley:" + responseStr);

    return mapper.readValue(responseStr, TrolleyResponse.class);

  }

  public TrolleyResponse iRetrieveTheShopperTrolley() throws Throwable {

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    String responseStr = null;

    String endPoint = URLResources.TRADER_V2_GET_TROLLEY;
    Map<String, String> queryParams = new HashMap<String, String>();
    // invoke the service with the framed request
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    responseStr = mapWebserviceResponse.get("response");
    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "Issues with clearing the trolley:" + responseStr);

    return mapper.readValue(responseStr, TrolleyResponse.class);

  }

  public TrolleyResponse iAddProductsToTrolleyWithNotes(int quantity, String stockCode, boolean isUpdate, String note) throws Throwable {

    TrolleyItem trolleyItem = new TrolleyItem();
    List<ProductItem> productItems = new ArrayList<ProductItem>();
    ProductItem item = new ProductItem();
    item.setStockCode(stockCode);
    if (note != null) {
      item.setNote(note);
    }
    item.setQuantity(quantity);

    productItems.add(item);
    trolleyItem.setItems(productItems);
    trolleyItem.setReplaceTrolley(isUpdate);
    trolleyItem.setSource("apiAuto");

    String requestStr = mapper.writeValueAsString(trolleyItem);

    String endPoint = URLResources.TRADER_V2_ADD_UPDATE_TROLLEY;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListTrader);
    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "Issue with the API endpoint:: " + endPoint);
    String responseStr = mapWebserviceResponse.get("response");
    //logger.info("The Response String is::"+responseStr);
    TrolleyResponse trolleyResponse = new TrolleyResponse();
    trolleyResponse = mapper.readValue(responseStr, TrolleyResponse.class);
    trolleyResponse.setResponseStatus(mapWebserviceResponse.get("statusCode"));
    return trolleyResponse;
  }

  public TrolleyResponse i_add_the_following_products_to_the_trolley(int quantity, List<String> stockCodes, boolean isUpdate) throws Throwable {

    TrolleyItem trolleyItem = new TrolleyItem();
    List<ProductItem> productItems = new ArrayList<ProductItem>();
    for (String stockCode : stockCodes) {
      ProductItem item = new ProductItem();
      item.setStockCode(stockCode);
      item.setQuantity(quantity);
      productItems.add(item);
    }
    trolleyItem.setItems(productItems);
    trolleyItem.setReplaceTrolley(isUpdate);
    trolleyItem.setSource("apiAuto");

    String requestStr = mapper.writeValueAsString(trolleyItem);

    String endPoint = URLResources.TRADER_V2_ADD_UPDATE_TROLLEY;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, requestStr, headerListTrader);
    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "Issue with the API endpoint:: " + endPoint);
    String responseStr = mapWebserviceResponse.get("response");
    //logger.info("The Response String is::"+responseStr);
    TrolleyResponse trolleyResponse = new TrolleyResponse();
    trolleyResponse = mapper.readValue(responseStr, TrolleyResponse.class);
    trolleyResponse.setResponseStatus(mapWebserviceResponse.get("statusCode"));
    return trolleyResponse;
  }

  public TrolleyResponse i_add_or_update_the_following_products_to_the_trolley(int quantity, String stockCode, boolean isUpdate) throws Throwable {

    return iAddProductsToTrolleyWithNotes(quantity, stockCode, isUpdate, null);
  }


  public TrolleyResponse iDeleteProductFromTrolley(String stockCode) throws Throwable {


    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    String endPoint = URLResources.TRADER_V2_ADD_UPDATE_TROLLEY + "/" + stockCode + "/clear";
    mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerListTrader);
    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "Issue with the API endpoint:: " + endPoint);
    String responseStr = mapWebserviceResponse.get("response");
    //logger.info("The Response String is::"+responseStr);
    TrolleyResponse trolleyResponse = new TrolleyResponse();
    trolleyResponse = mapper.readValue(responseStr, TrolleyResponse.class);
    trolleyResponse.setResponseStatus(mapWebserviceResponse.get("statusCode"));
    return trolleyResponse;
  }

  public ShoppingAislesResponse iGetShoppingAisles() throws Throwable {
    ShoppingAislesResponse response;
    String endPoint = URLResources.TRADER_V2_SHOPPING_AISLES;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, ShoppingAislesResponse.class);
    return response;
  }

  public List<String> getAvailableRestrictedStockCodesFromSearchProductItemsResult(GetProductItems searchProductItemsResult, int maxRestrictedItemInList) {

    List<String> availableStockCodes = new ArrayList<>();
    for (int i = 0; i <= searchProductItemsResult.getItems().length; i++) {
      if (availableStockCodes.size() > maxRestrictedItemInList) {
        break;
      }
      SearchProductItem productItem = searchProductItemsResult.getItems()[i];
      if (productItem.getProductCount() >= 1) {
        for (Products product : productItem.getProducts()) {
          if (product.isAgeRestricted() && product.isIsAvailable()) {
            availableStockCodes.add(product.getStockcode());
          }
        }
      }

    }
    return availableStockCodes;
  }

  public List<String> getAvailableStockCodesFromSearchProductItemsResult(GetProductItems searchProductItemsResult, int maxRestrictedItemInList) {

    List<String> availableStockCodes = new ArrayList<>();
    for (int i = 0; i <= searchProductItemsResult.getItems().length; i++) {
      if (availableStockCodes.size() > maxRestrictedItemInList) {
        break;
      }
      SearchProductItem productItem = searchProductItemsResult.getItems()[i];
      if (productItem.getProductCount() >= 0) {
        for (Products product : productItem.getProducts()) {
          if (product.isIsAvailable()) {
            availableStockCodes.add(product.getStockcode());
          }
        }
      }

    }
    return availableStockCodes;
  }
}
