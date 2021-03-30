package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProductsSpecial {
  Products[] products;
  SortOptions[] sortoptions;
  private int page;
  private int count;
  private String nextpage;
}
