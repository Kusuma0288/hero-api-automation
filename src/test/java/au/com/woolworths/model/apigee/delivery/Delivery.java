package au.com.woolworths.model.apigee.delivery;

import au.com.woolworths.model.apigee.address.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class Delivery {

  private Address Address;

}
