package au.com.woolworths.model.apigee.delivery;

import au.com.woolworths.model.apigee.fulfilment.FulfilmentMethod;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class DeliveryFulfilmentV3Response {
  private Delivery delivery;
  private FulfilmentMethod fulfilment;
}
