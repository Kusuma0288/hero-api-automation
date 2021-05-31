
package au.com.woolworths.model.iris.graphql.recipeSearch;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeSummaryCard {
  private String title;
  private String image;
  private String id;
}