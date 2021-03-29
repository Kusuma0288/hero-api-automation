package au.com.woolworths.model.apigee.fulfilment;

import au.com.woolworths.model.apigee.pickup.Pickup;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class FulFilmentResponse {
  private Pickup Pickup;
  private FulfilmentMethod Fulfilment;
  private FulFilmentResults Results;
}
