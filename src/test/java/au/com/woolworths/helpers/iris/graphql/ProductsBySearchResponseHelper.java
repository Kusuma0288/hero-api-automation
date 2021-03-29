package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;
import au.com.woolworths.model.iris.graphql.productList.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsBySearchResponseHelper extends BaseHelper {

  public static List<Product> getAvailableProducts(List<Product> productList) {
    return productList.stream()
        .filter(product -> product.getIsAvailable()
            && product.getName() != null)
        .collect(Collectors.toList());
  }

  public enum ProductsBySearchArgs {

    SEARCH_TERM("searchTerm"),
    STORE_ID("storeId"),
    PAGE_SIZE("pageSize"),
    PAGE_NUMBER("pageNumber"),
    SORT_OPTION("sortOption");

    private String arg;

    ProductsBySearchArgs(String arg) {
      this.arg = arg;
    }

    public String get() {
      return arg;
    }
  }

  public enum ProductIdSource {
    RANDOM,
    STORED
  }
}
