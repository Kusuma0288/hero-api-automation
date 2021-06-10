package au.com.woolworths.model.iris.graphql.recipeDetails;

import com.fasterxml.jackson.annotation.JsonInclude;

@lombok.Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDetailsResponse {
  private Data data;
}