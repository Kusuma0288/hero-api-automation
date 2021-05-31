package au.com.woolworths.model.iris.graphql.recipeSearch;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeSearchResponse {
  private Data data;
}