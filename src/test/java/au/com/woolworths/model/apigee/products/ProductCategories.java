package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductCategories {
  private String title;
  private Object images;
  private int category_order;
  private Object products_href;
  private int product_count;
}
