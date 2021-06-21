package au.com.woolworths.model.iris.graphql.recipeHome;

import lombok.Data;

import java.util.List;

@Data
public class RecipeHome {
  private List <Items> items;
}