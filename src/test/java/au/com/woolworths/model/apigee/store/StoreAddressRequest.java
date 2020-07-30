package au.com.woolworths.model.apigee.store;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data public class StoreAddressRequest {
  private String storeAddressId;
}
