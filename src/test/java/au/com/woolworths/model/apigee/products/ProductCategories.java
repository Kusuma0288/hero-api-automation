package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductCategories {
  private String title;
  private Object images;
  private int category_order;
  private Object products_href;
  private int product_count;
}
