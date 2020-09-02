package au.com.woolworths.helpers.trader;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.trader.*;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class AddressHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("AddressHelper.class");
  RestInvocationUtil invocationUtil;

  public AddressHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SearchAddressResponse searchForTheAddress(String lookupAddress) throws Throwable {

    String endPoint = URLResources.TRADER_V2_SEARCH_ADDRESS;
    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("address", lookupAddress);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    //logger.info("Looking for the address: "+lookupAddress);
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");
    // logger.info("Response: " + responseStr);
    return mapper.readValue(responseStr, SearchAddressResponse.class);
  }

  public AddressDetails iGetTheAddressIdFromAmasId(String amasId) throws Throwable {

    AddressRequest addressRequest = new AddressRequest();
    String endPoint = URLResources.TRADER_V3_ADDRESS;
    addressRequest.setAmasId(amasId);
    addressRequest.setIsForBilling(true);


    String addressRequestStr = mapper.writeValueAsString(addressRequest);

    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, addressRequestStr, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");

    AddressDetails addressDetailResponse = mapper.readValue(responseStr, AddressDetails.class);
    //Assert.assertTrue(mapWebserviceResponse.get("statusCode").equals("200"), "There is some issue with the Amas Id: " + addressDetailResponse.toString());
    addressDetailResponse.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return addressDetailResponse;
  }

  public CheckoutAddressResponse iSetTheAddressId(int addressId) throws Throwable {

    String endPoint = URLResources.TRADER_V3_CHECKOUT_ADDRESS;
    CheckoutAddress checkoutAddressRequest = new CheckoutAddress();
    checkoutAddressRequest.setAddressId(addressId);
    String checkoutAddressRequestStr = mapper.writeValueAsString(checkoutAddressRequest);
    // invoke the service with the framed request
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, checkoutAddressRequestStr, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");

    CheckoutAddressResponse checkoutAddressResponse = mapper.readValue(responseStr, CheckoutAddressResponse.class);

    Assert.assertEquals(mapWebserviceResponse.get("statusCode"), "200", "There is some issue with the save checkout: " + checkoutAddressResponse.toString());
    return checkoutAddressResponse;
  }

  public MyAddresses iLoginToMyAccountToSeeTheAddresses() throws Throwable {

    String endPoint = URLResources.TRADER_V2_MYACCOUNT_ADDRESS;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");

    return mapper.readValue(responseStr, MyAddresses.class);

  }

  public Address[] searchTheAddressForTheGuestMode() throws Throwable {

    String endPoint = URLResources.TRADER_V3_ADDRESS_SEARCH;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, new HashMap<String, String>(), headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");


    return mapper.readValue(responseStr, Address[].class);

  }

  public void iDeleteThePreviousAddresses() throws Throwable {

    MyAddresses myAddressResponse = iLoginToMyAccountToSeeTheAddresses();

    int counter = 1;
    String endPoint;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    while (myAddressResponse.getAddresses().length > counter) {

      endPoint = URLResources.TRADER_V2_DELETE_ADDRESS + myAddressResponse.getAddresses()[counter].getAddressId() + "/delete";
      mapWebserviceResponse = invocationUtil.invokePostWithoutBody(endPoint, headerListTrader);
      String responseCode = mapWebserviceResponse.get("statusCode");
      Assert.assertEquals(responseCode, "200", "Unable to delete the delivery address");

      counter++;
    }

  }

  public PickupResponse[] iGetThePickupStore(int StoreAddressId) throws Throwable {
    String endPoint = URLResources.TRADER_V2_PICKUP_STORES;
    Map<String, Integer> queryParams = new HashMap<String, Integer>();
    queryParams.put("StoreAddressId", StoreAddressId);

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListTrader);
    String responseStr = mapWebserviceResponse.get("response");


    return mapper.readValue(responseStr, PickupResponse[].class);

  }
}
