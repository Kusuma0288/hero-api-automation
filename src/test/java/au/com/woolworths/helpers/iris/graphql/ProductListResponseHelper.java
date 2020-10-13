
package au.com.woolworths.helpers.iris.graphql;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.iris.graphql.productList.Product;

public class ProductListResponseHelper extends BaseHelper {

  private final static Logger logger = Logger.getLogger("ProductListResponseHelper.class");

  public static List<Product> getAvailableProducts(List<Product> productList) {
    return productList.stream()
        .filter(product -> product.getIsAvailable()
            && product.getWasPrice() != null
            && product.getName() != null
            && product.getPromotionValue() != null)
        .collect(Collectors.toList());
  }

  public enum ProductIdSource {
    RANDOM,
    STORED
  }
}
