package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ProductCategoriesSpecial {
  private Aisles[] aisles;
}
