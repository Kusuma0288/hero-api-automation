package au.com.woolworths.model.apigee.delivery;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Data
public class DeliveryAddressRequest {
  private String address;

}




