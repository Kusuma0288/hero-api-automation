package au.com.woolworths.model.iris.graphql.recipeDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Items {
  private String image;
  private String __typename;
  private String id;
  private String title;
}