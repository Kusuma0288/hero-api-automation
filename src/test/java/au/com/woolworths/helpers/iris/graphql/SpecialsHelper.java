package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class SpecialsHelper extends BaseHelper {
  public enum Typename {
    SPECIALS_GROUP("specialsGroup"),
    IS_SPECIAL("isSpecial"),
    CATEGORY_ID("categoryId");

    private final String typename;

    Typename(String typename) {
      this.typename = typename;
    }

    public String get() {
      return typename;
    }
  }
}
