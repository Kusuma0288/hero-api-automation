package au.com.woolworths.model.apigee.delivery;

import lombok.Data;
import au.com.woolworths.model.apigee.address.Address;
import au.com.woolworths.model.apigee.delivery.Window;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data
public class DeliveryDetails {
  private Address Address;
  private Window Window;
}
