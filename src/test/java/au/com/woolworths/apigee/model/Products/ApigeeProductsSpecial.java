package au.com.woolworths.apigee.model.Products;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) @JsonIgnoreProperties
@Data public class ApigeeProductsSpecial {
  private int page;
  private int count;
  ApigeeProducts[] products;
  ApigeeSortOptions[] sortoptions;
  private String nextpage;
}
