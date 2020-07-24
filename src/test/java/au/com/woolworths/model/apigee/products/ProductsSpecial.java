package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class ProductsSpecial {
  private int page;
  private int count;
  Products[] products;
  SortOptions[] sortoptions;
  private String nextpage;
}
