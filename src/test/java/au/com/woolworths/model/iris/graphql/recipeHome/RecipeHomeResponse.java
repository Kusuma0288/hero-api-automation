package au.com.woolworths.model.iris.graphql.recipeHome;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeHomeResponse {
  private Data data;
}