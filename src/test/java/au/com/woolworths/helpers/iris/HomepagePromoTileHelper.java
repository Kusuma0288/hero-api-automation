package au.com.woolworths.helpers.iris;

import java.util.Map;
import java.util.logging.Logger;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import io.restassured.response.Response;

public class HomepagePromoTileHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("HomepageHelper.class");
  public Response response;

  public HomepagePromoTileHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public void getProductsByProductGroup(String query) throws Throwable {
    String endPoint = URLResources.V1_GRAPHQL;
    Map<String, String> mapWebserviceResponse;
    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    /**
     * TODO: Add pojo for response while automating actual graphql tests.
     * For now only printing the response to demo graphql parser. Print statement to be removed.
     *
     */

    System.out.println(responseStr);

    /** Also use mapper from BaseHelper when writing actual test
     * e.g. "response = mapper.readValue(responseStr, ProductsByProductGroupResponse.class);"
     */
  }

}
