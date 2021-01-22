package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.discover.RewardsDiscoverResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;

public class DiscoverHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;

  public DiscoverHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public RewardsDiscoverResponse iRetrieveDiscoverFeed(String query) throws IOException {
    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, RewardsDiscoverResponse.class);
  }

}
