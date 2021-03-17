package au.com.woolworths.model.iris.graphql.productCategories;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Categories {
  private Boolean isFinalLevel;
  private String iconUrl;
  private String title;
  private String categoryId;
}