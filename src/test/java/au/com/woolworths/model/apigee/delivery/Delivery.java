package au.com.woolworths.model.apigee.delivery;

import au.com.woolworths.model.apigee.address.Address;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class Delivery {

  private Address Address;

}
