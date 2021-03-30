package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class SpecialsCategories {
  private String products_href;
  private String title;
  private int product_count;
}