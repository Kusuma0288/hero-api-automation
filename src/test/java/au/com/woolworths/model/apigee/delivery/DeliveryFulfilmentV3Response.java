package au.com.woolworths.model.apigee.delivery;

import au.com.woolworths.model.apigee.fulfilment.FulfilmentMethod;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class DeliveryFulfilmentV3Response {
  private Delivery delivery;
  private FulfilmentMethod fulfilment;
}
