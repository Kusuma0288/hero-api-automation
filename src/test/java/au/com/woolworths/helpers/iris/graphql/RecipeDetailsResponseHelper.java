package au.com.woolworths.helpers.iris.graphql;

import au.com.woolworths.helpers.common.BaseHelper;

public class RecipeDetailsResponseHelper extends BaseHelper {

  public enum RecipeDetailsArgs {

      RECIPE_ID("recipeId");

    private String arg;

    RecipeDetailsArgs(String arg) {
      this.arg = arg;
    }

    public String get() {
      return arg;
    }
  }
}
