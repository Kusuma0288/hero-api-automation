package au.com.woolworths.helpers.metis;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.metis.activity.ActivityResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.MapperFeature;
import org.codehaus.jackson.map.DeserializationConfig;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class ActivityHelper extends BaseHelper {
  RestInvocationUtil invocationUtil;
  private final static Logger logger = Logger.getLogger("ActivityHelper.class");

  public ActivityHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;
  }

  public ActivityResponse iRetrieveMyActivity(String query) throws IOException {


    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> mapWebserviceResponse;

    /*
    The string returned by Message icon is an emoji which is utf8
    variable-width character encoding and considered as null object
     */
    mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    String endPoint = URLResources.METIS_REWARDS_GRAPHQL;

    mapWebserviceResponse = invocationUtil.invokePostWithHeaders(endPoint, query, headerListRewards);
    String responseStr = mapWebserviceResponse.get("response");
    return mapper.readValue(responseStr, ActivityResponse.class);
  }

}
