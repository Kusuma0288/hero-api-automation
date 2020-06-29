package au.com.woolworths.apigee.model.Products;

import au.com.woolworths.apigee.model.ApigeeAisles;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ApigeeProductCategoriesSpecial {
  private ApigeeAisles[] aisles;
}
