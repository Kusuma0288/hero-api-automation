package au.com.woolworths.model.iris.graphql.recipeDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDetails {
  private String recipeId;
  private String recipeUrl;
  private String title;
  private String image;
  private List <String> ingredients;
  private List <String> ingredientProductIds;
  private Byline byline;
  private List <Features> features;
  private RecipeMethod method;
  private RecipeNutritionTile nutritionTile;
  private LatestRecipes latestRecipes;
}