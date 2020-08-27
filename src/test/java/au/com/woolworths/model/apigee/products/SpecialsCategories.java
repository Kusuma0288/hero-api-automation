package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class SpecialsCategories {
  private String products_href;
  private String title;
  private int product_count;
}