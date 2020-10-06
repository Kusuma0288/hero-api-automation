package au.com.woolworths.helpers.iris.graphql;

import java.util.Map;
import java.util.logging.Logger;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;

public class GraphqlQueryHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("GraphqlQueryHelper.class");

  public GraphqlQueryHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public String postGraphqlQuery(String query) {
    String endPoint = URLResources.HERMES_V1_GRAPHQL;
    Map<String, String> mapWebserviceResponse;
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListCommon);
    String responseStr = mapWebserviceResponse.get("response");
    logger.info(responseStr);
    return responseStr;
  }

  /**
   * Mode is getting deprecated soon from all woolies graphql queries
   * Todo: Remove this enum and its uses when it is deprecated
   */
  public enum Mode {
    ONLINE,
    PICKUP,
    INSTORE
  }

  public int productListPageSize = 20;
  public int productsByProductGroupListPageSize = 200;
  public int defaultProductListPageSize = 100;


}
