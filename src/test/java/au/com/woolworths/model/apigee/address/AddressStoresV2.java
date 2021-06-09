package au.com.woolworths.model.apigee.address;

import au.com.woolworths.model.apigee.store.Stores;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AddressStoresV2 {
  private Stores[] stores;
}
