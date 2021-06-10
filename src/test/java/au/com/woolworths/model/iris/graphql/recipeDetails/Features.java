package au.com.woolworths.model.iris.graphql.recipeDetails;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Features {
  private String altText;
  private String unit;
  private Integer maxValue;
  private String __typename;
  private String title;
  private Integer value;
  private Integer amount;
}