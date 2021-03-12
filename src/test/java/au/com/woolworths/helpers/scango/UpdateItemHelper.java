package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.scanitems.UpdateItemResponse;
import au.com.woolworths.model.scango.scanitems.UpdateWeightRequest;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class UpdateItemHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("UpdateItemHelper.class");
  RestInvocationUtil invocationUtil;

  public UpdateItemHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public UpdateItemResponse iCallUpdateWeightAPI() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    String requestStr = null;
    Map<String, String> queryParams = new HashMap<>();

    UpdateWeightRequest updateWeightRequest = new UpdateWeightRequest();
    updateWeightRequest.setWeights(Double.parseDouble(TestProperties.get("WEIGHT")));
    UpdateItemResponse response;

    String endPoint = URLResources.SCANGO_UPDATE_ITEM + sharedData.lineNumber;

    requestStr = mapper.writeValueAsString(updateWeightRequest);
    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, UpdateItemResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

  public UpdateItemResponse iCallUpdateQuantityAPI() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    String requestStr = "";
    Map<String, String> queryParams = new HashMap<>();

    UpdateItemResponse response;

    String endPoint = URLResources.SCANGO_UPDATE_ITEM + sharedData.lineNumber;

    mapWebserviceResponse = invocationUtil.invokePut(endPoint, requestStr, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, UpdateItemResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }

}
