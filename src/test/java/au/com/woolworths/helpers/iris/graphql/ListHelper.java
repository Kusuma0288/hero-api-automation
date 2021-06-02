package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class ListHelper extends BaseHelper {
  public enum Typename {
    LIST_NAME("title"),
    COLOR("color"),
    REFERENCE_ID("referenceId"),
    FREE_TEXT("text"),
    PRODUCT_ID("productId"),
    PRODUCT_IDS("productIds"),
    STORE_ID("storeId"),
    QUANTITY("quantity"),
    CHECKED("checked"),
    ID("id"),
    LIST_ID("listId"),
    ID_PROD("idProd"),
    ID_FREE("idFree"),
    TIMESTAMP("timestamp"),
    LAST_SYNC("lastSynced"),
    PAGE_NUMBER("pageNumber"),
    PAGE_SIZE("pageSize"),
    STORE("storeId"),
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
