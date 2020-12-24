package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.perks.RewardsPerksResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;

public class PerksHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;

  public PerksHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public RewardsPerksResponse iRetrievePerks(String query) throws IOException {
    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, RewardsPerksResponse.class);
  }

}
