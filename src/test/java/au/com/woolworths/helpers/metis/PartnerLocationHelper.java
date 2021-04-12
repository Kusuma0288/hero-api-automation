package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.partnerlocation.PartnerLocationDetailsResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PartnerLocationHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;

  public PartnerLocationHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public PartnerLocationDetailsResponse getPartnerLocationDetails(String partner, Float latitude, Float longitude) throws IOException {
    String endPoint = URLResources.METIS_PARTNER_LOCATION;

    Map<String, String> mapWebserviceResponse = new HashMap<String, String>();
    Map<String, String> queryParams = new HashMap<String, String>();

    endPoint = endPoint + "/" + partner + "?latitude=" + latitude + "&longitude=" + longitude;
    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    String statusCodeStr = mapWebserviceResponse.get("statusCode");


    PartnerLocationDetailsResponse response = mapper.readValue(responseStr, PartnerLocationDetailsResponse.class);
    response.setStatusCode(statusCodeStr);

    return response;
  }

}
