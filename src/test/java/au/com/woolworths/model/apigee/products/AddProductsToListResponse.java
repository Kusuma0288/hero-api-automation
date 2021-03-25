package au.com.woolworths.model.apigee.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class AddProductsToListResponse {
  private AddProdListDetailsResponse update;
  private Object changes;
}
