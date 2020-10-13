package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.redemptionsettings.RewardsRedemptionResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;

public class RedemptionSettingsHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;

  public RedemptionSettingsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public RewardsRedemptionResponse iRetrieveMyRedemptionSettings(String query) throws IOException {
    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, RewardsRedemptionResponse.class);
  }

}
