package au.com.woolworths.model.apigee.address;

import au.com.woolworths.model.apigee.address.ApigeeAddress;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class SearchAddresses {
  private ApigeeAddress[] addresses;
}
