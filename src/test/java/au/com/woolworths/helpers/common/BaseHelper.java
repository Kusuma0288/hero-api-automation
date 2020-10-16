package au.com.woolworths.helpers.common;

import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.utils.SharedData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");
  protected static List<Header> headerListCommon;
  protected static List<Header> headerListTrader;
  protected static List<Header> headerListRewards;
  protected static SharedData sharedData;
  protected ObjectMapper mapper = new ObjectMapper();

  public BaseHelper() {
    this.headerListCommon = new LinkedList<>();
    this.headerListTrader = new LinkedList<>();
    this.headerListRewards = new LinkedList<>();
    this.sharedData = ApplicationContext.getSharedData();
    headerListCommon.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerListCommon.add(new Header("Authorization", "Bearer " + sharedData.accessToken));

    headerListTrader.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerListTrader.add(new Header("wowapi-auth-token", sharedData.authToken));
    headerListTrader.add(new Header("cache-control", "no-cache"));

    headerListRewards.add(new Header("x-api-key", TestProperties.get("rewards-x-api-key")));
    headerListRewards.add(new Header("Authorization", "Bearer " + sharedData.accessToken));

    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }

  public void resetHeaderList() {
    headerListCommon.clear();
    headerListCommon.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerListCommon.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
  }

  public void resetTraderHeaderList() {
    headerListTrader.clear();
    headerListTrader.add(new Header("wowapi-key", TestProperties.get("wowapi-key")));
    headerListTrader.add(new Header("wowapi-auth-token", sharedData.authToken));
    headerListTrader.add(new Header("cache-control", "no-cache"));
  }

}
