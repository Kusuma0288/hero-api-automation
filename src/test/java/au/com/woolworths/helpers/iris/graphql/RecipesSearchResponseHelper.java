
package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class RecipesSearchResponseHelper extends BaseHelper {

  public enum RecipeSearchArgs {

    SOURCE("source"),
    TAGS("tags");

    private String arg;

    RecipeSearchArgs(String arg) {
      this.arg = arg;
    }

    public String get() {
      return arg;
    }
  }
}
