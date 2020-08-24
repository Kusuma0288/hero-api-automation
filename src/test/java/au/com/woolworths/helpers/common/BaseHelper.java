package au.com.woolworths.helpers.common;

import au.com.woolworths.model.trader.GetProductItems;
import au.com.woolworths.model.trader.Products;
import au.com.woolworths.model.trader.SearchProductItem;
import au.com.woolworths.utils.TestProperties;
import au.com.woolworths.context.ApplicationContext;
import au.com.woolworths.utils.SharedData;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class BaseHelper {

  private final static Logger logger = Logger.getLogger("BaseHelper.class");
  protected SharedData sharedData;
  protected static List<Header> headerListApigee;
  protected static List<Header> headerListTrader;
  protected ObjectMapper mapper = new ObjectMapper();

  public BaseHelper() {
    this.headerListApigee = new LinkedList<>();
    this.headerListTrader = new LinkedList<>();
    this.sharedData = ApplicationContext.getSharedData();
    headerListApigee.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerListApigee.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
    headerListTrader.add(new Header("wowapi-key",TestProperties.get("wowapi-key")));
    headerListTrader.add(new Header("wowapi-auth-token", sharedData.authToken));
    headerListTrader.add(new Header("cache-control", "no-cache"));
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
  }

  public void resetHeaderList() {
    headerListApigee.clear();
    headerListApigee.add(new Header("x-api-key", TestProperties.get("x-api-key")));
    headerListApigee.add(new Header("Authorization", "Bearer " + sharedData.accessToken));
  }
  public List<String> getAvailableRestrictedStockCodesFromSearchProductItemsResult(GetProductItems searchProductItemsResult, int maxRestrictedItemInList) {

    List<String> availableStockCodes = new ArrayList<>();
    for (int i = 0; i <= searchProductItemsResult.getItems().length; i++) {
      if (availableStockCodes.size() > maxRestrictedItemInList) {
        break;
      }
      SearchProductItem productItem = searchProductItemsResult.getItems()[i];
      if (productItem.getProductCount() >= 1) {
        for (Products product : productItem.getProducts()) {
          if (product.isAgeRestricted() && product.isIsAvailable()) {
            availableStockCodes.add(product.getStockcode());
          }
        }
      }

    }
    return availableStockCodes;
  }

  public List<String> getAvailableStockCodesFromSearchProductItemsResult(GetProductItems searchProductItemsResult, int maxRestrictedItemInList) {

    List<String> availableStockCodes = new ArrayList<>();
    for (int i = 0; i <= searchProductItemsResult.getItems().length; i++) {
      if (availableStockCodes.size() > maxRestrictedItemInList) {
        break;
      }
      SearchProductItem productItem = searchProductItemsResult.getItems()[i];
      if (productItem.getProductCount() >= 0) {
        for (Products product : productItem.getProducts()) {
          if (product.isIsAvailable()) {
            availableStockCodes.add(product.getStockcode());
          }
        }
      }

    }
    return availableStockCodes;
  }

}
