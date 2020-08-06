package au.com.woolworths.model.apigee.products;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class AddProductsToListResponse {
  private AddProdListDetailsResponse update;
  private Object changes;
}