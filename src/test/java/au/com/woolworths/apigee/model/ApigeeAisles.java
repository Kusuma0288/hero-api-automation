package au.com.woolworths.apigee.model;

import au.com.woolworths.apigee.model.Products.ApigeeProductCategories;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeAisles {
  private String title;
  private ApigeeProductCategories[] categories;
  private float number;
  private Object images;
  private Object color;
}
