package au.com.woolworths.model.apigee.store;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
public class StoreAddressRequest {
  private String storeAddressId;
}
