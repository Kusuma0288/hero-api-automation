package au.com.woolworths.model.apigee.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class SearchResponseV3 {

  SearchProductsV3[] products;
  SearchProductsV3[] fault;
  private int product_count;
  private String nextPage;
  private Object sortOptions;
  private String faultString;
}