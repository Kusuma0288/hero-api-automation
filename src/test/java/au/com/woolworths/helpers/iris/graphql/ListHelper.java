package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class ListHelper extends BaseHelper {
  public enum Typename {
    LIST_NAME("title"),
    COLOR("color"),
    REFERENCE_ID("referenceId"),
    FREE_TEXT("text"),
    PRODUCT_ID("productId"),
    QUANTITY("quantity"),
    CHECKED("checked"),
    ID("id"),
    TIMESTAMP("timestamp"),
    LAST_SYNC("lastSynced"),
    COUNT("count");

    private String typename;
    Typename(String typename) {
      this.typename = typename;
    }
    public String get() {
      return typename;
    }
  }
}
