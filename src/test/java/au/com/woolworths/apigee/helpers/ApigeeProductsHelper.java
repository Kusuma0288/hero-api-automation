package au.com.woolworths.apigee.helpers;

import au.com.woolworths.Utils.RestInvocationUtil;
import au.com.woolworths.Utils.URLResources;
import au.com.woolworths.apigee.model.ApigeeProductCategoriesSpecial;
import au.com.woolworths.apigee.model.ApigeeProductsSpecial;
import au.com.woolworths.apigee.stepdefinitions.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ApigeeProductsHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeProductsHelper.class");

  public ApigeeProductsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }


  public ApigeeProductCategoriesSpecial iRetreiveProductCategories(Map<String, String> queryParams) throws Throwable {

    String endPoint = URLResources.APIGEE_V3_CATEGORIES;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ApigeeProductCategoriesSpecial productCategoriesSpecial = mapper.readValue(responseStr, ApigeeProductCategoriesSpecial.class);
    return productCategoriesSpecial;
  }

  public ApigeeProductsSpecial iRetreiveProducts(Map<String, String> queryParams) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_PRODUCTS;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ApigeeProductsSpecial productsSpecial = mapper.readValue(responseStr, ApigeeProductsSpecial.class);
    return productsSpecial;
  }

}
