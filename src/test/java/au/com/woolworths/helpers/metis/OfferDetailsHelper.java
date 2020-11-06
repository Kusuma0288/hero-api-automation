package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.ObjectMapper;
import au.com.woolworths.model.metis.offerdetails.OfferDetailsResponse;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class OfferDetailsHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("OfferDetailsHelper.class");
  RestInvocationUtil invocationUtil;

  public OfferDetailsHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public OfferDetailsResponse iRetrieveMyOfferDetails(String query) throws IOException {


    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> mapWebserviceResponse;

    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, OfferDetailsResponse.class);
  }

}
