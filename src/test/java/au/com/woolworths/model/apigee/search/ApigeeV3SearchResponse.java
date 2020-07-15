package au.com.woolworths.model.apigee.search;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeV3SearchResponse {

  Apigeev3SearchProducts[] products;
  Apigeev3SearchProducts[] fault;
  private int product_count;
  private String nextPage;
  private Object sortOptions;
  private String faultString;
}