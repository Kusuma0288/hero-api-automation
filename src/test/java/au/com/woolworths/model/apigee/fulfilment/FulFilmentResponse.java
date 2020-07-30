package au.com.woolworths.model.apigee.fulfilment;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data public class FulFilmentResponse {
  private au.com.woolworths.model.apigee.pickup.Pickup Pickup;
  private FulfilmentMethod Fulfilment;
  private FulFilmentResults Results;
}
