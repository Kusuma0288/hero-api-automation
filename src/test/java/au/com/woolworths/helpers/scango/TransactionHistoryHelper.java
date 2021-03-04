package au.com.woolworths.helpers.scango;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.scango.menu.TransactionHistoryResponse;
import au.com.woolworths.stepdefinitions.common.ServiceHooks;
import au.com.woolworths.utils.RestInvocationUtil;
import au.com.woolworths.utils.URLResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class TransactionHistoryHelper extends BaseHelper {
  private final static Logger logger = Logger.getLogger("TransactionHistoryHelper.class");
  RestInvocationUtil invocationUtil;

  public TransactionHistoryHelper() {
    this.invocationUtil = ServiceHooks.restInvocationUtil;

  }

  public TransactionHistoryResponse iCallTransactionHistory() throws IOException {
    Map<String, String> mapWebserviceResponse;
    String responseStr = null;
    Map<String, String> queryParams = new HashMap<>();

    TransactionHistoryResponse response;

    String endPoint = URLResources.SCANGO_TRANSACTION_HISTORY;

    mapWebserviceResponse = invocationUtil.invokeGetWithHeaders(endPoint, queryParams, headerListScanGo);
    responseStr = mapWebserviceResponse.get("response");
    response = mapper.readValue(responseStr, TransactionHistoryResponse.class);
    response.setStatusCode(mapWebserviceResponse.get("statusCode"));
    return response;
  }
}
