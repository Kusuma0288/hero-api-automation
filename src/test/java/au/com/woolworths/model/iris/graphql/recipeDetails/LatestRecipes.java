package au.com.woolworths.model.iris.graphql.recipeDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LatestRecipes {
  private String title;
  private String actionTitle;
  private List <Items> items;
  private List <String> actionMetadata;

}