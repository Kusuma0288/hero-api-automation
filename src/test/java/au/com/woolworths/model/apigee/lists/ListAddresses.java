package au.com.woolworths.model.apigee.lists;

import au.com.woolworths.model.apigee.address.AddressDetails;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ListAddresses {
  private AddressDetails[] Addresses;
}
