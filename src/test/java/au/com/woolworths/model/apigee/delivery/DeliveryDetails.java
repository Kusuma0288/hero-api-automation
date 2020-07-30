package au.com.woolworths.model.apigee.delivery;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Data public class DeliveryDetails {
  private au.com.woolworths.model.apigee.address.Address Address;
  private au.com.woolworths.model.apigee.delivery.Window Window;
}
