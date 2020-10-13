package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.specials.SpecialsResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import junit.framework.Assert;

import java.io.IOException;
import java.util.Map;

public class SpecialsHelper extends BaseHelper {

  RestInvocationUtil invocationUtil;

  public SpecialsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public SpecialsResponse getSpecialsResponse() throws IOException {
    String endPoint = URLResources.METIS_REWARDS_SPECIALS;
    Map<String, String> mapWebserviceResponse;

    mapWebserviceResponse = invocationUtil.invokeGetWithoutParam(endPoint, headerListRewards);

    String responseStr = mapWebserviceResponse.get("response");
    String statusCodeStr = mapWebserviceResponse.get("statusCode");

    Assert.assertEquals("status code", "200", statusCodeStr);

    SpecialsResponse response = mapper.readValue(responseStr, SpecialsResponse.class);
    response.setStatusCode(statusCodeStr);

    return response;
  }

}
