package au.com.woolworths.model.iris.graphql.recipeDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeNutritionTile {
  private String perServeKilojoules;
  private String perServeCalories;
  private Integer dailyIntakePercentage;
  private String dailyIntakeText;
  private String disclaimer;
  private String title;
  private List<RecipeNutritionTileFeature> features;
}