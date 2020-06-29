package au.com.woolworths.apigee.model.Products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeProductCategories {
  private String title;
  private Object images;
  private int category_order;
  private Object products_href;
  private int product_count;
}
