package au.com.woolworths.helpers.iris.graphql;

import java.util.Map;
import java.util.logging.Logger;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

public class GraphqlHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("GraphqlHelper.class");

  public GraphqlHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public String postGraphqlQuery(String query) {
    String endPoint = URLResources.HERMES_V1_GRAPHQL;
    Map<String, String> mapWebserviceResponse;
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    return responseStr;
  }

  public enum ProductListPageSize {
    PRODUCT_LIST_PAGE_SIZE(20),
    PRODUCTS_BY_PRODUCT_GROUP_PAGE_SIZE(200),
    DEFAULT_PRODUCT_LIST_PAGE_SIZE(100);

    private int pagesize;

    ProductListPageSize(int pagesize) {
      this.pagesize = pagesize;
    }

    public int get() {
      return pagesize;
    }
  }

}
