package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Aisles {
  private String title;
  private ProductCategories[] categories;
  private float number;
  private Object images;
  private Object color;
}
