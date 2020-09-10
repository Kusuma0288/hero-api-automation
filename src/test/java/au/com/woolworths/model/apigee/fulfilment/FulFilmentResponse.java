package au.com.woolworths.model.apigee.fulfilment;

import lombok.Data;
import au.com.woolworths.model.apigee.pickup.Pickup;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FulFilmentResponse {
  private Pickup Pickup;
  private FulfilmentMethod Fulfilment;
  private FulFilmentResults Results;
}
