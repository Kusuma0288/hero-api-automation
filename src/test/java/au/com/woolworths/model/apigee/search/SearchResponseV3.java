package au.com.woolworths.model.apigee.search;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

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