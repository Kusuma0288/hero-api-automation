package au.com.woolworths.apigee.model;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class ApigeeV3SearchResponse {

  Apigeev3SearchProducts[] products;
  Apigeev3SearchProducts[] fault;
  private int product_count;
  private String nextPage;
  private Object sortOptions;
  private String faultString;
}