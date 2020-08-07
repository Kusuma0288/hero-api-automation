package au.com.woolworths.model.apigee.delivery;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)

@Data public class DeliveryAddressRequest {
  private String address;

}




