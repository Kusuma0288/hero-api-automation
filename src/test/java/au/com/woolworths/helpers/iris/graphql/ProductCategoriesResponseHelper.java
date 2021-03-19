
package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class ProductCategoriesResponseHelper extends BaseHelper {

  public enum ProductsByCategoriesArgs {

    CATEGORY_ID("categoryId"),
    STORE_ID("storeId"),
    CATEGORIES_TYPE("categoriesType");

    private String arg;

    ProductsByCategoriesArgs(String arg) {
      this.arg = arg;
    }

    public String get() {
      return arg;
    }
  }
}
