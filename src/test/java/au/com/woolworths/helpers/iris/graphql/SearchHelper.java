package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class SearchHelper extends BaseHelper {
  public enum Typename {
    SEARCH_TERM("searchTerm"),
    SORT_OPTION("sortOption"),
    PRODUCTS_FEED("productsFeed"),
    FILTER_OPTIONS_TYPE("type"),
    FILTER_OPTIONS_VALUES("values");

    private final String typename;

    Typename(String typename) {
      this.typename = typename;
    }

    public String get() {
      return typename;
    }
  }
}
