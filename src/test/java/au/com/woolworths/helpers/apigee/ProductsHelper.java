package au.com.woolworths.helpers.apigee;

import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.apigee.products.ProductCategoriesSpecial;
import au.com.woolworths.model.apigee.products.ProductsSpecial;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class ProductsHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ApigeeProductsHelper.class");

  public ProductsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }


  public ProductCategoriesSpecial iRetreiveProductCategories(Map<String, String> queryParams) throws Throwable {

    String endPoint = URLResources.APIGEE_V3_CATEGORIES;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ProductCategoriesSpecial productCategoriesSpecial = mapper.readValue(responseStr, ProductCategoriesSpecial.class);
    return productCategoriesSpecial;
  }

  public ProductsSpecial iRetreiveProducts(Map<String, String> queryParams) throws Throwable {

    String endPoint = URLResources.APIGEE_V2_PRODUCTS;
    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerList);
    String responseStr = mapWebserviceResponse.get("response");

    ProductsSpecial productsSpecial = mapper.readValue(responseStr, ProductsSpecial.class);
    return productsSpecial;
  }

}