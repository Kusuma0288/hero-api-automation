package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.partnerdetails.PartnerDetailsResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PartnerHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;

  public PartnerHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public PartnerDetailsResponse getPartnerDetails(String partner, String store) throws IOException {
    String endPoint = URLResources.METIS_PARTNER_DETAILS;

    Map <String, String> mapWebserviceResponse = new HashMap <String, String>();
    Map <String, String> queryParams = new HashMap <String, String>();

    endPoint = endPoint + "/" + partner + "/" + store + "/details";

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    String statusCodeStr = mapWebserviceResponse.get("statusCode");

    PartnerDetailsResponse response = mapper.readValue(responseStr, PartnerDetailsResponse.class);
    response.setStatusCode(statusCodeStr);

    return response;
  }

}
