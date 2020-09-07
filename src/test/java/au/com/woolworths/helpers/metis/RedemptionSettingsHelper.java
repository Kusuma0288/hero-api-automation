package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.redemptionSettings.RedemptionSettingsResponse;
import au.com.woolworths.stepdefinitions.apigee.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.Map;

public class RedemptionSettingsHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private RedemptionSettingsResponse response;


  public RedemptionSettingsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public RedemptionSettingsResponse iRetrieveMyRedemptionSettings(String query) throws IOException {
    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerList);
    String responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, RedemptionSettingsResponse.class);
    return response;
  }

}
