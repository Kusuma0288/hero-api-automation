package au.com.woolworths.model.apigee.address;

import au.com.woolworths.model.apigee.store.Stores;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class AddressStoresV2 {
  private Stores[] stores;

}
