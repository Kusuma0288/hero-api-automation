package au.com.woolworths.model.apigee.address;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class SearchAddresses {

  private SearchAddressDetails[] addresses;
}
