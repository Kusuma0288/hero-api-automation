package au.com.woolworths.model.iris.graphql.recipeSearch;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class RecipeSearch {
  private Integer nextPage;
  private List <RecipeSummaryCard> recipes;
  private Integer totalNumberOfRecipes;
}